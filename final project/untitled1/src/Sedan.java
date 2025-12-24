public class Sedan extends Vehicle {
    public Sedan(String plate, boolean needsCharging, boolean isMember, boolean passValid, boolean hasHandicap) {
        super(plate, needsCharging, isMember, passValid, hasHandicap);
    }

    @Override
    public VehicleSize getSize() { return VehicleSize.MEDIUM; }

    @Override
    public VehicleType getType() { return VehicleType.SEDAN; }
}

