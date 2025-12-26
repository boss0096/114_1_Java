package spots;

import vehicles.Vehicle;

public abstract class ParkingSpot {
    private int id;
    private int floor;
    private boolean occupied;

    public ParkingSpot(int id, int floor) {
        this.id = id;
        this.floor = floor;
        this.occupied = false;
    }

    public int getId() {
        return id;
    }

    public int getFloor() {
        return floor;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupy() {
        this.occupied = true;
    }

    public void release() {
        this.occupied = false;
    }

    // 抽象方法：判斷是否能容納該車輛
    public abstract boolean canAccommodate(Vehicle v);
}