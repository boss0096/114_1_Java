import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        PricingStrategy standard = new StandardPricingStrategy(); // $40/hr, 向上進位
        PricingStrategy member = new MemberPricingStrategy();

        Instant start = Instant.now();
        Instant end = start.plus(Duration.ofMinutes(90)); // 1.5 小時

        ParkingSession sessionStandard = new ParkingSession(start, end, standard);
        System.out.println("Standard fee for 1.5h: $" + sessionStandard.calculateFee()); // 預期 $80

        ParkingSession sessionMember = new ParkingSession(start, end, member);
        System.out.println("Member fee for 1.5h: $" + sessionMember.calculateFee()); // 預期 $0
    }
}

