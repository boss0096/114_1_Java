package vehicles;

public class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public int getRequiredSpotCount() {
        return 1;
    }

    @Override
    public boolean needsCharging() {
        return false;
    }

    @Override
    public String getTypeName() {
        return "vehicles.Motorcycle";
    }
}