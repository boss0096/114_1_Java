
/**
 * 抽象類別：車輛 (Vehicle)
 * 定義所有車輛共有的屬性與行為合約
 */
public abstract class Vehicle {
    private String licensePlate;
    private boolean isMember;

    public Vehicle(String licensePlate) {
        if (licensePlate == null || licensePlate.trim().isEmpty()) {
            throw new IllegalArgumentException("License plate cannot be null or empty");
        }
        this.licensePlate = licensePlate;
        this.isMember = false; // 預設非會員
    }

    public String getLicensePlate() { return licensePlate; }

    public boolean isMember() { return isMember; }

    public void setMember(boolean member) { isMember = member; }

    // --- 抽象方法：強迫子類別實作具體特徵 ---

    // 取得所需車位數量 (多型)
    public abstract int getRequiredSpotCount();

    // 是否需要充電 (多型)
    public abstract boolean needsCharging();

    // 取得車種顯示名稱
    public abstract String getTypeName();
}

// --- 以下為具體車種實作 ---

/**
 * 貨車：需要 2 個車位
 */
class Truck extends Vehicle {
    public Truck(String plate) { super(plate); }

    @Override public int getRequiredSpotCount() { return 2; }
    @Override public boolean needsCharging() { return false; }
    @Override public String getTypeName() { return "Truck"; }
}

/**
 * 轎車：需要 1 個車位
 */
class Sedan extends Vehicle {
    public Sedan(String plate) { super(plate); }

    @Override public int getRequiredSpotCount() { return 1; }
    @Override public boolean needsCharging() { return false; }
    @Override public String getTypeName() { return "Sedan"; }
}

/**
 * 機車：需要 1 個車位 (通常是小型位)
 */
class Motorcycle extends Vehicle {
    public Motorcycle(String plate) { super(plate); }

    @Override public int getRequiredSpotCount() { return 1; }
    @Override public boolean needsCharging() { return false; }
    @Override public String getTypeName() { return "Motorcycle"; }
}

/**
 * 電動車：需要 1 個車位，可選擇是否充電
 */
class ElectricVehicle extends Vehicle {
    private boolean wantsToCharge;

    public ElectricVehicle(String plate, boolean wantsToCharge) {
        super(plate);
        this.wantsToCharge = wantsToCharge;
    }

    @Override public int getRequiredSpotCount() { return 1; }
    @Override public boolean needsCharging() { return wantsToCharge; }
    @Override public String getTypeName() { return "Electric Vehicle"; }
}

