import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// ==========================================
// 1. 策略模式 (Pricing Strategy)
// ==========================================

// 介面
interface PricingStrategy {
    double calculate(Duration duration);
}

// 實作：標準費率 ($40/hr)
class StandardPricingStrategy implements PricingStrategy {
    @Override
    public double calculate(Duration duration) {
        long minutes = duration.toMinutes();
        return (minutes / 60.0) * 40.0;
    }
}

// 實作：會員費率 (免費) [依據 v4.0 規格]
class MemberPricingStrategy implements PricingStrategy {
    @Override
    public double calculate(Duration duration) {
        return 0.0; // 會員免費
    }
}

// ==========================================
// 2. 車輛類別 (Vehicles)
// ==========================================

abstract class Vehicle {
    private String licensePlate;
    private boolean isMember;

    public Vehicle(String licensePlate) {
        if (licensePlate == null || licensePlate.isEmpty()) {
            throw new IllegalArgumentException("License plate cannot be null or empty");
        }
        this.licensePlate = licensePlate;
        this.isMember = false; // 預設 false
    }

    public String getLicensePlate() { return licensePlate; }
    public boolean isMember() { return isMember; }
    public void setMember(boolean member) { isMember = member; }

    // 抽象方法
    public abstract int getRequiredSpotCount();
    public abstract boolean needsCharging();
    public abstract String getTypeName();
}

// 具體實作：貨車
class Truck extends Vehicle {
    public Truck(String plate) { super(plate); }
    @Override public int getRequiredSpotCount() { return 2; }
    @Override public boolean needsCharging() { return false; }
    @Override public String getTypeName() { return "Truck"; }
}

// 具體實作：轎車
class Sedan extends Vehicle {
    public Sedan(String plate) { super(plate); }
    @Override public int getRequiredSpotCount() { return 1; }
    @Override public boolean needsCharging() { return false; }
    @Override public String getTypeName() { return "Sedan"; }
}

// 具體實作：機車
class Motorcycle extends Vehicle {
    public Motorcycle(String plate) { super(plate); }
    @Override public int getRequiredSpotCount() { return 1; }
    @Override public boolean needsCharging() { return false; }
    @Override public String getTypeName() { return "Motorcycle"; }
}

// 具體實作：電動車
class ElectricVehicle extends Vehicle {
    private boolean wantsToCharge;

    public ElectricVehicle(String plate, boolean wantsToCharge) {
        super(plate);
        this.wantsToCharge = wantsToCharge;
    }
    @Override public int getRequiredSpotCount() { return 1; }
    @Override public boolean needsCharging() { return this.wantsToCharge; }
    @Override public String getTypeName() { return "Electric Vehicle"; }
}

// ==========================================
// 3. 車位類別 (Spots)
// ==========================================

abstract class ParkingSpot {
    private int id;
    private int floor;
    private boolean occupied;

    public ParkingSpot(int id, int floor) {
        this.id = id;
        this.floor = floor;
        this.occupied = false;
    }

    public int getId() { return id; }
    public int getFloor() { return floor; }
    public boolean isOccupied() { return occupied; }

    public void occupy() { this.occupied = true; }
    public void release() { this.occupied = false; }

    // 抽象相容性檢查
    public abstract boolean canAccommodate(Vehicle v);
}

// 具體實作：標準車位 (拒絕機車與充電EV)
class RegularSpot extends ParkingSpot {
    public RegularSpot(int id, int floor) { super(id, floor); }
    @Override
    public boolean canAccommodate(Vehicle v) {
        if (v instanceof Motorcycle) return false;
        if (v.needsCharging()) return false;
        return true;
    }
}

// 具體實作：小型車位 (僅接受機車)
class SmallSpot extends ParkingSpot {
    public SmallSpot(int id, int floor) { super(id, floor); }
    @Override
    public boolean canAccommodate(Vehicle v) {
        return v instanceof Motorcycle;
    }
}

// 具體實作：充電車位 (僅接受需充電EV)
class ElectricSpot extends ParkingSpot {
    public ElectricSpot(int id, int floor) { super(id, floor); }
    @Override
    public boolean canAccommodate(Vehicle v) {
        return v.needsCharging();
    }
}

// ==========================================
// 4. 票券與樓層管理 (System Core)
// ==========================================

class ParkingTicket {
    private String ticketId;
    private Vehicle vehicle;
    private List<ParkingSpot> allocatedSpots;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private PricingStrategy strategy;

    public ParkingTicket(Vehicle vehicle, List<ParkingSpot> allocatedSpots, LocalDateTime entryTime, PricingStrategy strategy) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.allocatedSpots = allocatedSpots;
        this.entryTime = entryTime;
        this.strategy = strategy;
        this.exitTime = null;
    }

    public PricingStrategy getStrategy() { return strategy; }
    public List<ParkingSpot> getAllocatedSpots() { return allocatedSpots; }
    public Vehicle getVehicle() { return vehicle; }
    public void setExitTime(LocalDateTime time) { this.exitTime = time; }
}

class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber, int spotCount) {
        this.floorNumber = floorNumber;
        this.spots = new ArrayList<>();

        // 依據 v4.0 規格配置：
        // Index 0 -> SmallSpot
        // Index 1 -> ElectricSpot
        // Index 2+ -> RegularSpot
        for (int i = 0; i < spotCount; i++) {
            if (i == 0) {
                spots.add(new SmallSpot(i, floorNumber));
            } else if (i == 1) {
                spots.add(new ElectricSpot(i, floorNumber));
            } else {
                spots.add(new RegularSpot(i, floorNumber));
            }
        }
    }

    // 尋找單一車位
    public ParkingSpot findSingleSpot(Vehicle v) {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied() && spot.canAccommodate(v)) {
                return spot;
            }
        }
        return null;
    }

    // 尋找連續車位 (滑動視窗)
    public List<ParkingSpot> findConsecutiveSpots(Vehicle v, int count) {
        for (int i = 0; i <= spots.size() - count; i++) {
            boolean found = true;
            List<ParkingSpot> potentialSpots = new ArrayList<>();

            for (int j = 0; j < count; j++) {
                ParkingSpot current = spots.get(i + j);
                if (current.isOccupied() || !current.canAccommodate(v)) {
                    found = false;
                    break;
                }
                potentialSpots.add(current);
            }

            if (found) {
                return potentialSpots;
            }
        }
        return null;
    }
}

class ParkingLot {
    private List<ParkingFloor> floors;

    public ParkingLot() {
        this.floors = new ArrayList<>();
    }

    public void addFloor(ParkingFloor floor) {
        this.floors.add(floor);
    }

    // 處理進場
    public ParkingTicket handleEntry(Vehicle vehicle) {
        int required = vehicle.getRequiredSpotCount();
        List<ParkingSpot> foundSpots = null;

        for (ParkingFloor floor : floors) {
            if (required == 1) {
                ParkingSpot s = floor.findSingleSpot(vehicle);
                if (s != null) {
                    foundSpots = new ArrayList<>();
                    foundSpots.add(s);
                    break;
                }
            } else {
                foundSpots = floor.findConsecutiveSpots(vehicle, required);
                if (foundSpots != null) break;
            }
        }

        if (foundSpots == null) {
            System.out.println("進場失敗：無合適車位 (" + vehicle.getTypeName() + ")");
            return null;
        }

        // 鎖定車位
        for (ParkingSpot s : foundSpots) s.occupy();

        // 注入策略 (依據 v4.0 規格)
        PricingStrategy strategy = vehicle.isMember() ? new MemberPricingStrategy() : new StandardPricingStrategy();

        return new ParkingTicket(vehicle, foundSpots, LocalDateTime.now(), strategy);
    }

    // 處理離場
    public void handleExit(ParkingTicket ticket, boolean hasCharged, Duration chargeDuration) {
        if (ticket == null) throw new IllegalArgumentException("Ticket cannot be null");

        // 模擬設定離場時間 (因為程式執行太快，這裡模擬停了 2 小時)
        ticket.setExitTime(LocalDateTime.now().plusHours(2));
        Duration parkingDuration = Duration.ofHours(2); // 為了計算方便直接給定

        // 1. 計算停車費
        double parkingFee = ticket.getStrategy().calculate(parkingDuration);

        // 2. 計算充電費 (依據 v4.0: 電動車且有充電，每小時 50 元)
        double chargingFee = 0.0;
        if (hasCharged && ticket.getVehicle() instanceof ElectricVehicle) {
            chargingFee = (chargeDuration.toMinutes() / 60.0) * 50.0;
        }

        double total = parkingFee + chargingFee;

        // 顯示明細
        System.out.println("=== 離場結算 ===");
        System.out.println("車牌：" + ticket.getVehicle().getLicensePlate());
        System.out.println("車種：" + ticket.getVehicle().getTypeName());
        System.out.println("會員：" + ticket.getVehicle().isMember());
        System.out.println("停車費：" + parkingFee);
        System.out.println("充電費：" + chargingFee);
        System.out.println("總金額：" + total);

        // 3. 釋放車位
        for (ParkingSpot s : ticket.getAllocatedSpots()) {
            s.release();
        }
    }
}

// ==========================================
// 5. 主程式 (測試用)
// ==========================================

public class Main {
    public static void main(String[] args) {
        // 初始化停車場 (1層樓，10個車位)
        ParkingLot lot = new ParkingLot();
        // 根據 v4.0 規格：
        // Index 0=Small, Index 1=Electric, Index 2~9=Regular
        lot.addFloor(new ParkingFloor(1, 10));

        System.out.println("--- 測試 1: 機車進場 (應該停 Index 0) ---");
        Vehicle moto = new Motorcycle("MOTO-01");
        ParkingTicket t1 = lot.handleEntry(moto); // 應該成功

        System.out.println("\n--- 測試 2: 電動車充電 (應該停 Index 1) ---");
        Vehicle evCharge = new ElectricVehicle("EV-CHG", true);
        evCharge.setMember(true); // 設為會員 (停車費應為0)
        ParkingTicket t2 = lot.handleEntry(evCharge);

        System.out.println("\n--- 測試 3: 貨車進場 (需要 2 個連續 Regular) ---");
        // Index 0, 1 已滿。Index 2,3 是空的 Regular。
        Vehicle truck = new Truck("BIG-TRUCK");
        ParkingTicket t3 = lot.handleEntry(truck);
        if(t3 != null) {
            System.out.println("貨車停在：" + t3.getAllocatedSpots().get(0).getId() + " 和 " + t3.getAllocatedSpots().get(1).getId());
        }

        System.out.println("\n--- 測試 4: 離場結算 ---");
        // 機車離場 (一般費率)
        if(t1 != null) lot.handleExit(t1, false, Duration.ZERO);

        // 電動車離場 (會員免費 + 充電費 2小時)
        if(t2 != null) lot.handleExit(t2, true, Duration.ofHours(2));
    }
}