import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 類別：停車票券 (ParkingTicket)
 * 紀錄一次停車週期的完整資訊
 */
public class ParkingTicket {
    private String ticketId;
    private Vehicle vehicle;
    private List<ParkingSpot> allocatedSpots;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime; // 可為 null
    private PricingStrategy strategy;

    public ParkingTicket(Vehicle v, List<ParkingSpot> spots, LocalDateTime time, PricingStrategy strategy) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = v;
        this.allocatedSpots = spots;
        this.entryTime = time;
        this.strategy = strategy;
    }

    // --- Getters & Setters ---

    // 測試用：允許修改進場時間以模擬時長
    public void testSetEntryTime(LocalDateTime t) { this.entryTime = t; }

    public LocalDateTime getEntryTime() { return entryTime; }

    public List<ParkingSpot> getAllocatedSpots() { return allocatedSpots; }

    public Vehicle getVehicle() { return vehicle; }

    public PricingStrategy getStrategy() { return strategy; }

    public LocalDateTime getExitTime() { return exitTime; }

    // 設定離場時間並回傳計算出的費用
    public double checkout(LocalDateTime exit) {
        if (exit.isBefore(entryTime)) {
            throw new IllegalArgumentException("exit must be after entry");
        }
        this.exitTime = exit;
        return calculateFee();
    }

    // 若尚未離場則回傳目前已過時間的費用（以 exitTime 或現在為基準）
    public double calculateFee() {
        LocalDateTime end = (exitTime != null) ? exitTime : LocalDateTime.now();
        Duration dur = Duration.between(entryTime, end);
        return strategy.calculate(dur);
    }
}

