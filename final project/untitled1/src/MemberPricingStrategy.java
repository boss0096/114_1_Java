import java.time.Duration;

public class MemberPricingStrategy implements PricingStrategy {
    public double calculateFee(Duration duration, Vehicle vehicle) { return 0.0; }
    public String getDescription() { return "會員月票方案"; }
}
