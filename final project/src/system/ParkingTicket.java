package system;

import spots.ParkingSpot;
import strategies.PricingStrategy;
import vehicles.Vehicle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ParkingTicket {
    private String ticketId;
    private Vehicle vehicle;
    private List<ParkingSpot> allocatedSpots;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private PricingStrategy strategy;

    public ParkingTicket(Vehicle vehicle, List<ParkingSpot> allocatedSpots, LocalDateTime entryTime, PricingStrategy strategy) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.allocatedSpots = allocatedSpots;
        this.entryTime = entryTime;
        this.strategy = strategy;
        this.exitTime = null;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public List<ParkingSpot> getAllocatedSpots() {
        return allocatedSpots;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public PricingStrategy getStrategy() {
        return strategy;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    // 輔助方法：計算停車時長
    public java.time.Duration getDuration() {
        if (exitTime == null) return java.time.Duration.ZERO;
        return java.time.Duration.between(entryTime, exitTime);
    }
}