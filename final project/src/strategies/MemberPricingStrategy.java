package strategies;

import java.time.Duration;

public class MemberPricingStrategy implements PricingStrategy {
    // 假設標準費率是 40，會員打 5 折就是 20
    private static final double MEMBER_HOURLY_RATE = 20.0;

    @Override
    public double calculate(Duration duration) {
        long minutes = duration.toMinutes();
        double hours = minutes / 60.0;
        return hours * MEMBER_HOURLY_RATE;
    }
}