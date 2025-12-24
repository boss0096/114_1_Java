import java.time.Duration;

public class ChargingService {
    private static final double RATE_PER_HOUR = 50.0;
    public static double calculateFee(Duration d) { return d.toHours() * RATE_PER_HOUR; }
}

