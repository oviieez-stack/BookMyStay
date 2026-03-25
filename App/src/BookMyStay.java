import java.util.*;

class Reservation {
    String reservationId;
    String guestName;
    String roomType;
    double cost;

    Reservation(String reservationId, String guestName, String roomType, double cost) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.cost = cost;
    }

    public String toString() {
        return reservationId + " | " + guestName + " | " + roomType + " | Rs." + cost;
    }
}

class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation r) {
        history.add(r);
    }

    public List<Reservation> getAllReservations() {
        return history;
    }
}

class BookingReportService {
    public void generateReport(List<Reservation> reservations) {
        double total = 0;

        System.out.println("Booking History:");
        for (Reservation r : reservations) {
            System.out.println(r);
            total += r.cost;
        }

        System.out.println("Total Revenue: Rs." + total);
        System.out.println("Total Bookings: " + reservations.size());
    }
}

public class BookMyStay {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        history.addReservation(new Reservation("RES101", "Dinesh", "Deluxe", 2000));
        history.addReservation(new Reservation("RES102", "Oviya", "Suite", 3500));
        history.addReservation(new Reservation("RES103", "Arun", "Standard", 1500));

        List<Reservation> all = history.getAllReservations();

        reportService.generateReport(all);
    }
}