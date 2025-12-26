package system;

import spots.ParkingFloor;
import spots.ParkingSpot;
import strategies.MemberPricingStrategy;
import strategies.PricingStrategy;
import strategies.StandardPricingStrategy;
import vehicles.ElectricVehicle;
import vehicles.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingFloor> floors;

    public ParkingLot() {
        this.floors = new ArrayList<>();
    }

    public void addFloor(ParkingFloor floor) {
        this.floors.add(floor);
    }

    // 處理車輛進場 [cite: 94]
    public ParkingTicket handleEntry(Vehicle vehicle) {
        // 步驟1: 取得需求 N [cite: 95]
        int requiredSpots = vehicle.getRequiredSpotCount();
        List<ParkingSpot> foundSpots = null;

        // 步驟2: 遍歷樓層 [cite: 96]
        for (ParkingFloor floor : floors) {
            if (requiredSpots == 1) {
                // 若 N=1: 呼叫 findSingleSpot [cite: 97]
                ParkingSpot spot = floor.findSingleSpot(vehicle);
                if (spot != null) {
                    foundSpots = new ArrayList<>();
                    foundSpots.add(spot);
                    break;
                }
            } else if (requiredSpots > 1) {
                // 若 N>1: 呼叫 findConsecutiveSpots [cite: 98]
                foundSpots = floor.findConsecutiveSpots(vehicle, requiredSpots);
                if (foundSpots != null) {
                    break;
                }
            }
        }

        // 失敗時拋出異常 [cite: 104, 105, 106]
        if (foundSpots == null || foundSpots.isEmpty()) {
            throw new RuntimeException("No suitable spots available");
        }

        // 成功時 [cite: 100]
        for (ParkingSpot spot : foundSpots) {
            spot.occupy(); // 設定為 occupied [cite: 101]
        }

        // 依據會員身分注入 strategies.PricingStrategy [cite: 102]
        PricingStrategy strategy;
        if (vehicle.isMember()) {
            strategy = new MemberPricingStrategy();
        } else {
            strategy = new StandardPricingStrategy();
        }

        // 回傳 Ticket [cite: 103]
        return new ParkingTicket(vehicle, foundSpots, LocalDateTime.now(), strategy);
    }

    // 處理離場 [cite: 108]
    public double handleExit(ParkingTicket ticket, boolean hasCharged, Duration chargeDuration) {
        // 若 ticket 為 null 拋出異常 [cite: 109]
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null");
        }

        // 若外部已經設定了離場時間 (例如模擬測試)，不要覆蓋；否則設定為現在
        if (ticket.getExitTime() == null) {
            ticket.setExitTime(LocalDateTime.now());
        }
        Duration duration = ticket.getDuration();

        // 步驟1: 計算停車費 [cite: 110]
        double parkingFee = ticket.getStrategy().calculate(duration);
        System.out.println("Parking Fee: " + parkingFee);

        // 步驟2: 若 hasCharged 為 true 且是電動車，計算充電費 [cite: 111]
        double chargingFee = 0.0;
        if (hasCharged && ticket.getVehicle() instanceof ElectricVehicle) {
            // 假設充電費率簡單計算 (規格書未詳細定義費率，這裡示意)
            chargingFee = chargeDuration.toMinutes() * 2.0; // 假設每分鐘 2 元
            System.out.println("Charging Fee: " + chargingFee);
        }

        // 步驟3: 加總費用並顯示 [cite: 116]
        double totalFee = parkingFee + chargingFee;
        System.out.println("Total Fee: " + totalFee);

        // 步驟4: 釋放車位 [cite: 117]
        for (ParkingSpot spot : ticket.getAllocatedSpots()) {
            spot.release();
        }

        // 回傳計算結果，讓呼叫端 (例如 Main) 可以顯示或進一步處理
        return totalFee;
    }
}