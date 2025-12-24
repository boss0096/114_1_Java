import java.time.Duration;

public class MemberPricingStrategy implements PricingStrategy {
    @Override
    public double calculate(Duration duration) {
        return 0.0; // 會員免費，或在此改為折扣計算
    }
}

