import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class ParkingTicket {
    private String ticketId;
    private Vehicle vehicle;
    private List<ParkingSpot> allocatedSpots;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private PricingStrategy strategy;

    public ParkingTicket(Vehicle v, List<ParkingSpot> spots, LocalDateTime entry, PricingStrategy strategy) {
        this.ticketId = java.util.UUID.randomUUID().toString().substring(0, 8);
        this.vehicle = v;
        this.allocatedSpots = spots;
        this.entryTime = entry;
        this.strategy = strategy;
    }

    public Vehicle getVehicle() { return vehicle; }
    public List<ParkingSpot> getAllocatedSpots() { return allocatedSpots; }
    public PricingStrategy getStrategy() { return strategy; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public void setExitTime(LocalDateTime t) { this.exitTime = t; }
    public Duration getDuration() { if (exitTime == null) return Duration.ZERO; return Duration.between(entryTime, exitTime); }
}
