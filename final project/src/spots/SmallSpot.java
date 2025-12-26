package spots;

import vehicles.Motorcycle;
import vehicles.Vehicle;

public class SmallSpot extends ParkingSpot {
    public SmallSpot(int id, int floor) {
        super(id, floor);
    }

    @Override
    public boolean canAccommodate(Vehicle v) {
        return v instanceof Motorcycle;
    }
}