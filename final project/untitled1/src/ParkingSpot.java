public abstract class ParkingSpot {
    protected int id;
    protected int floor;
    protected boolean occupied = false;

    public ParkingSpot(int id, int floor) {
        this.id = id;
        this.floor = floor;
    }

    public int getId() { return id; }
    public int getFloor() { return floor; }
    public boolean isOccupied() { return occupied; }

    public void park(Vehicle v) { this.occupied = true; }
    public void leave() { this.occupied = false; }

    // whether this spot type can accommodate the vehicle
    public abstract boolean canFit(Vehicle v);
    public abstract SpotType getSpotType();
}

