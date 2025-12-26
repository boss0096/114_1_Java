package vehicles;

public class Sedan extends Vehicle {
    public Sedan(String licensePlate) {
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
        return "vehicles.Sedan";
    }
}