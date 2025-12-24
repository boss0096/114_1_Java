import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber, int capacity) {
        this.floorNumber = floorNumber;
        this.spots = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            ParkingSpot s;
            if (i == 0) s = new SmallSpot(i, floorNumber);
            else if (i == 1) s = new ElectricSpot(i, floorNumber);
            else if (i == 2) s = new HandicappedSpot(i, floorNumber);
            else s = new RegularSpot(i, floorNumber);
            spots.add(s);
        }
    }

    public boolean isFull() { return spots.stream().allMatch(ParkingSpot::isOccupied); }

    public ParkingSpot findSpotFor(Vehicle v) {
        // Prefer exact-match spots (electric for EV needing charge, handicapped for permits), else first fit
        if (v.getType() == VehicleType.EV && v.needsCharging()) {
            for (ParkingSpot s : spots) if (!s.isOccupied() && s.getSpotType() == SpotType.ELECTRIC && s.canFit(v)) return s;
        }
        if (v.hasHandicapPermit()) {
            for (ParkingSpot s : spots) if (!s.isOccupied() && s.getSpotType() == SpotType.HANDICAPPED && s.canFit(v)) return s;
        }
        for (ParkingSpot s : spots) if (!s.isOccupied() && s.canFit(v)) return s;
        return null;
    }

    // find consecutive regular spots for trucks
    public List<ParkingSpot> findConsecutiveRegularSpots(int count) {
        List<ParkingSpot> result = new ArrayList<>();
        for (int i = 0; i <= spots.size() - count; i++) {
            boolean ok = true;
            List<ParkingSpot> tmp = new ArrayList<>();
            for (int j = 0; j < count; j++) {
                ParkingSpot s = spots.get(i + j);
                if (s.isOccupied() || s.getSpotType() != SpotType.REGULAR) { ok = false; break; }
                tmp.add(s);
            }
            if (ok) return tmp;
        }
        return result;
    }

    public List<ParkingSpot> getSpots() { return spots; }
}

