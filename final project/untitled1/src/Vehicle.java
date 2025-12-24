public abstract class Vehicle {
    protected String licensePlate;
    protected boolean needsCharging;
    protected boolean isMember;
    protected boolean passValid;
    protected boolean hasHandicapPermit;

    public Vehicle(String licensePlate, boolean needsCharging, boolean isMember, boolean passValid, boolean hasHandicapPermit) {
        this.licensePlate = licensePlate;
        this.needsCharging = needsCharging;
        this.isMember = isMember;
        this.passValid = passValid;
        this.hasHandicapPermit = hasHandicapPermit;
    }

    // Concrete getters used across the project
    public String getLicensePlate() { return licensePlate; }
    public boolean needsCharging() { return needsCharging; }
    public boolean isMember() { return isMember; }
    public boolean isPassValid() { return passValid; }
    public boolean hasHandicapPermit() { return hasHandicapPermit; }

    // Abstract info each concrete vehicle must provide
    public abstract VehicleType getType();
    public abstract VehicleSize getSize();
}
