package vehicles;

public class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public int getRequiredSpotCount() {
        return 2;
    }

    @Override
    public boolean needsCharging() {
        return false;
    }

    @Override
    public String getTypeName() {
        return "vehicles.Truck";
    }
}