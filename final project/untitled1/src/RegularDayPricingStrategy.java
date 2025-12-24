import java.time.Duration;

public class RegularDayPricingStrategy implements PricingStrategy {
    public double calculateFee(Duration duration, Vehicle vehicle) { return duration.toHours() * 40.0; }
    public String getDescription() { return "標準日間費率"; }
}
