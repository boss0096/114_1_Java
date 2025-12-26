package strategies;

import java.time.Duration;

public class StandardPricingStrategy implements PricingStrategy {
    private static final double HOURLY_RATE = 40.0;

    @Override
    public double calculate(Duration duration) {
        long minutes = duration.toMinutes();
        double hours = minutes / 60.0;
        // 簡單實作：無條件進位或直接計算，這裡採用精確小數點計算
        return hours * HOURLY_RATE;
    }
}