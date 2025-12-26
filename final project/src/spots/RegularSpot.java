package spots;

import vehicles.Motorcycle;
import vehicles.Vehicle;

public class RegularSpot extends ParkingSpot {
    public RegularSpot(int id, int floor) {
        super(id, floor);
    }

    @Override
    public boolean canAccommodate(Vehicle v) {
        if (v instanceof Motorcycle) {
            return false;
        }
        if (v.needsCharging()) {
            return false;
        }
        return true; // 包含 vehicles.Sedan 與 vehicles.Truck
    }
}