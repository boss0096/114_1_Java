import java.time.Duration;

public class StandardPricingStrategy implements PricingStrategy {
    private final double hourlyRate;

    public StandardPricingStrategy() {
        this.hourlyRate = 40.0;
    }

    public StandardPricingStrategy(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculate(Duration duration) {
        long minutes = duration.toMinutes();
        double hoursRoundedUp = Math.ceil(minutes / 60.0);
        return hoursRoundedUp * hourlyRate;
    }
}

