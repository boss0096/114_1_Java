package spots;

import vehicles.Vehicle;

public class ElectricSpot extends ParkingSpot {
    public ElectricSpot(int id, int floor) {
        super(id, floor);
    }

    @Override
    public boolean canAccommodate(Vehicle v) {
        return v.needsCharging();
    }
}