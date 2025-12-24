public class Motorcycle extends Vehicle {
    public Motorcycle(String plate) {
        super(plate, false, false, false, false);
    }

    @Override
    public VehicleSize getSize() { return VehicleSize.SMALL; }

    @Override
    public VehicleType getType() { return VehicleType.MOTO; }
}

