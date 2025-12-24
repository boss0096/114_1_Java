import java.time.Duration;

public class PeakNightPricingStrategy implements PricingStrategy {
    public double calculateFee(Duration duration, Vehicle vehicle) { return duration.toHours() * 60.0; }
    public String getDescription() { return "夜間/尖峰費率"; }
}
