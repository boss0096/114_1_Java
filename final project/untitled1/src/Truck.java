public class Truck extends Vehicle {
    public Truck(String plate) {
        super(plate, false, false, false, false);
    }

    @Override
    public VehicleSize getSize() { return VehicleSize.LARGE; }

    @Override
    public VehicleType getType() { return VehicleType.TRUCK; }
}

