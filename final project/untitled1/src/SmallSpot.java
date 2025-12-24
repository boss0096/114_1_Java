public class SmallSpot extends ParkingSpot {
    public SmallSpot(int id, int floor) { super(id, floor); }

    @Override
    public boolean canFit(Vehicle v) {
        return v.getSize() == VehicleSize.SMALL;
    }

    @Override
    public SpotType getSpotType() { return SpotType.SMALL; }
}

