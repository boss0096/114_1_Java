import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingFloor> floors;

    public ParkingLot(int floorCount, int spotsPerFloor) {
        floors = new ArrayList<>();
        for (int i = 1; i <= floorCount; i++) floors.add(new ParkingFloor(i, spotsPerFloor));
        System.out.println("系統初始化完成：共 " + floorCount + " 層樓。");
    }

    public ParkingTicket handleEntry(Vehicle vehicle, LocalDateTime entryTime) {
        System.out.println(">> 車輛抵達入口: " + vehicle.getLicensePlate() + " (" + vehicle.getType() + ")");
        List<ParkingSpot> allocated = new ArrayList<>();
        if (vehicle.getType() == VehicleType.TRUCK) {
            for (ParkingFloor f : floors) {
                List<ParkingSpot> spots = f.findConsecutiveRegularSpots(2);
                if (!spots.isEmpty()) { allocated.addAll(spots); break; }
            }
            if (allocated.isEmpty()) { System.out.println("!! [拒絕] 貨車：找不到連續車位。"); return null; }
        } else {
            for (ParkingFloor f : floors) {
                ParkingSpot s = f.findSpotFor(vehicle);
                if (s != null) { allocated.add(s); break; }
            }
            if (allocated.isEmpty()) { System.out.println("!! [拒絕] 車位已滿或不適合。"); return null; }
        }
        for (ParkingSpot s : allocated) s.park(vehicle);
        System.out.print("   [分配] 鎖定車位: ");
        for (ParkingSpot s : allocated) System.out.print("[F"+s.getFloor()+"-"+s.getId()+"] ");
        System.out.println();

        PricingStrategy strategy = determineStrategy(vehicle, entryTime);
        System.out.println("   [策略] 套用費率策略: " + strategy.getDescription());
        if (vehicle.isMember() && vehicle.isPassValid()) System.out.println("   [會員] 月票有效");

        ParkingTicket ticket = new ParkingTicket(vehicle, allocated, entryTime, strategy);
        System.out.println("   [完成] 閘門開啟，車輛停入。");
        return ticket;
    }

    public void handleExit(ParkingTicket ticket, boolean hasCharged, java.time.Duration chargingDuration) {
        if (ticket == null) return;
        System.out.println(">> 車輛準備離場: " + ticket.getVehicle().getLicensePlate());
        double parkingFee = ticket.getStrategy().calculateFee(ticket.getDuration(), ticket.getVehicle());
        System.out.println("   [計費] 停車費 (" + ticket.getDuration().toHours() + "小時): $" + parkingFee);
        double chargingFee = 0.0;
        if (hasCharged && chargingDuration != null) {
            chargingFee = ChargingService.calculateFee(chargingDuration);
            System.out.println("   [計費] 充電費 (" + chargingDuration.toHours() + "小時): $" + chargingFee);
        }
        double total = parkingFee + chargingFee;
        System.out.println("   [總結] 應繳總金額: $" + total);
        System.out.println("   [完成] 繳費完成，釋放車位，閘門開啟。");
        for (ParkingSpot s : ticket.getAllocatedSpots()) s.leave();
    }

    private PricingStrategy determineStrategy(Vehicle v, LocalDateTime time) {
        if (v.isMember() && v.isPassValid()) return new MemberPricingStrategy();
        int hour = time.getHour();
        boolean isNight = hour >= 22 || hour < 6;
        if (isNight) return new PeakNightPricingStrategy();
        return new RegularDayPricingStrategy();
    }
}

