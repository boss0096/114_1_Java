package strategies;

import java.time.Duration;

public interface PricingStrategy {
    double calculate(Duration duration);
}