package spots;

import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.spots = new ArrayList<>();
    }

    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    // 尋找單一車位 (適用 vehicles.Sedan, vehicles.Motorcycle, EV)
    public ParkingSpot findSingleSpot(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied() && spot.canAccommodate(vehicle)) {
                return spot;
            }
        }
        return null;
    }

    // 尋找連續車位 (適用 vehicles.Truck)
    public List<ParkingSpot> findConsecutiveSpots(Vehicle vehicle, int count) {
        for (int i = 0; i <= spots.size() - count; i++) {
            boolean sequenceFound = true;
            List<ParkingSpot> potentialSpots = new ArrayList<>();

            for (int j = 0; j < count; j++) {
                ParkingSpot current = spots.get(i + j);
                // 檢查是否未佔用且能容納 (規格: 必須通過 canAccommodate 驗證) [cite: 99]
                // 這裡假設連續車位需為同類型或皆可容納該車種
                if (current.isOccupied() || !current.canAccommodate(vehicle)) {
                    sequenceFound = false;
                    break;
                }
                potentialSpots.add(current);
            }

            if (sequenceFound) {
                return potentialSpots;
            }
        }
        return null;
    }
}