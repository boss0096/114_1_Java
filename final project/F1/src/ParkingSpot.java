/**
 * 抽象類別：車位 (ParkingSpot)
 */
public abstract class ParkingSpot {
    protected int id;
    protected int floor;
    protected boolean occupied;

    public ParkingSpot(int id, int floor) {
        this.id = id;
        this.floor = floor;
        this.occupied = false;
    }

    public void occupy() { this.occupied = true; }

    public void release() { this.occupied = false; }

    public boolean isOccupied() { return occupied; }

    public int getFloor() { return floor; }

    public int getId() { return id; }

    // 核心多型判斷：此車位是否能容納該車輛？
    public abstract boolean canAccommodate(Vehicle v);
}

// --- 以下為具體車位實作 ---

/**
 * 標準車位：不收機車，不收需充電的EV
 */
class RegularSpot extends ParkingSpot {
    public RegularSpot(int id, int floor) { super(id, floor); }

    @Override
    public boolean canAccommodate(Vehicle v) {
        if (v instanceof Motorcycle) return false;
        if (v.needsCharging()) return false;
        return true;
    }
}

/**
 * 小型車位：只收機車
 */
class SmallSpot extends ParkingSpot {
    public SmallSpot(int id, int floor) { super(id, floor); }

    @Override
    public boolean canAccommodate(Vehicle v) {
        return v instanceof Motorcycle;
    }
}

/**
 * 充電車位：只收需充電的EV
 */
class ElectricSpot extends ParkingSpot {
    public ElectricSpot(int id, int floor) { super(id, floor); }

    @Override
    public boolean canAccommodate(Vehicle v) {
        return v.needsCharging();
    }
}
