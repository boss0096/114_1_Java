import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingSystem {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("      啟動停車場系統 (Parking System)      ");
        System.out.println("==========================================\n");

        ParkingLot lot = new ParkingLot(3, 10);

        System.out.println("--- [情境 1] 一般轎車進場 ---");
        Vehicle sedan = new Sedan("ABC-1234", false, false, false, false);
        ParkingTicket ticket1 = lot.handleEntry(sedan, LocalDateTime.now());
        simulateParkingDuration(ticket1, Duration.ofHours(3));
        lot.handleExit(ticket1, false, Duration.ZERO);

        System.out.println("\n------------------------------------------\n");

        System.out.println("--- [情境 2] 貨車進場 (需連續車位) ---");
        Vehicle truck = new Truck("TRK-9999");
        ParkingTicket ticket2 = lot.handleEntry(truck, LocalDateTime.now());
        simulateParkingDuration(ticket2, Duration.ofHours(5));
        lot.handleExit(ticket2, false, Duration.ZERO);

        System.out.println("\n------------------------------------------\n");

        System.out.println("--- [情境 3] 電動車進場 (充電 + 停車) ---");
        Vehicle ev = new EV("EV-8888", true, false, false);
        ParkingTicket ticket3 = lot.handleEntry(ev, LocalDateTime.now());
        simulateParkingDuration(ticket3, Duration.ofHours(4));
        lot.handleExit(ticket3, true, Duration.ofHours(2));

        System.out.println("\n------------------------------------------\n");

        System.out.println("--- [情境 4] 會員進場 (VIP) ---");
        Vehicle memberCar = new Sedan("VIP-0001", false, true, true, false);
        ParkingTicket ticket4 = lot.handleEntry(memberCar, LocalDateTime.now());
        simulateParkingDuration(ticket4, Duration.ofHours(10));
        lot.handleExit(ticket4, false, Duration.ZERO);
    }

    private static void simulateParkingDuration(ParkingTicket ticket, Duration duration) {
        if (ticket != null) ticket.setExitTime(ticket.getEntryTime().plus(duration));
    }
}

