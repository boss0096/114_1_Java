package vehicles;

public class ElectricVehicle extends Vehicle {
    private boolean wantsToCharge;

    public ElectricVehicle(String licensePlate, boolean wantsToCharge) {
        super(licensePlate);
        this.wantsToCharge = wantsToCharge;
    }

    @Override
    public int getRequiredSpotCount() {
        return 1;
    }

    @Override
    public boolean needsCharging() {
        return this.wantsToCharge;
    }

    @Override
    public String getTypeName() {
        return "Electric vehicles.Vehicle";
    }
}