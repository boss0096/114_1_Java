public class ElectricSpot extends ParkingSpot {
    public ElectricSpot(int id, int floor) { super(id, floor); }

    @Override
    public boolean canFit(Vehicle v) {
        // Electric spot reserved for vehicles that need charging (EV) but can accept others if free
        return v.getType() == VehicleType.EV || v.getSize() == VehicleSize.MEDIUM || v.getSize() == VehicleSize.SMALL;
    }

    @Override
    public SpotType getSpotType() { return SpotType.ELECTRIC; }
}

