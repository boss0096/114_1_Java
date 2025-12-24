import java.time.Duration;
import java.time.Instant;

public class ParkingSession {
    private final Instant start;
    private final Instant end;
    private final PricingStrategy strategy;

    public ParkingSession(Instant start, Instant end, PricingStrategy strategy) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("end must be after start");
        }
        this.start = start;
        this.end = end;
        this.strategy = strategy;
    }

    public Duration getDuration() {
        return Duration.between(start, end);
    }

    public double calculateFee() {
        return strategy.calculate(getDuration());
    }
}

