public class RegularSpot extends ParkingSpot {
    public RegularSpot(int id, int floor) { super(id, floor); }

    @Override
    public boolean canFit(Vehicle v) {
        // Regular can fit medium and large and small
        return v.getSize() == VehicleSize.MEDIUM || v.getSize() == VehicleSize.LARGE || v.getSize() == VehicleSize.SMALL;
    }

    @Override
    public SpotType getSpotType() { return SpotType.REGULAR; }
}

