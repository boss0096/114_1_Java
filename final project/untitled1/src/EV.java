public class EV extends Vehicle {
    public EV(String plate, boolean needsCharging, boolean isMember, boolean passValid) {
        super(plate, needsCharging, isMember, passValid, false);
    }

    @Override
    public VehicleSize getSize() { return VehicleSize.MEDIUM; }

    @Override
    public VehicleType getType() { return VehicleType.EV; }
}

