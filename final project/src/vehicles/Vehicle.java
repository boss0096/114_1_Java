package vehicles;

public abstract class Vehicle {
    private String licensePlate;
    private boolean isMember;

    public Vehicle(String licensePlate) {
        if (licensePlate == null || licensePlate.isEmpty()) {
            throw new IllegalArgumentException("License plate cannot be null or empty");
        }
        this.licensePlate = licensePlate;
        this.isMember = false; // 預設為 false
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    // 抽象方法
    public abstract int getRequiredSpotCount();
    public abstract boolean needsCharging();
    public abstract String getTypeName();
}