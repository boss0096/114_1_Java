public class HandicappedSpot extends ParkingSpot {
    public HandicappedSpot(int id, int floor) { super(id, floor); }

    @Override
    public boolean canFit(Vehicle v) {
        return v.hasHandicapPermit();
    }

    @Override
    public SpotType getSpotType() { return SpotType.HANDICAPPED; }
}

