import java.io.*;
import java.util.*;

class Reservation implements Serializable {
    String reservationId;
    String guestName;
    double cost;

    Reservation(String reservationId, String guestName, double cost) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.cost = cost;
    }

    public String toString() {
        return reservationId + " | " + guestName + " | Rs." + cost;
    }
}

class SystemState implements Serializable {
    List<Reservation> bookings;
    int availableRooms;

    SystemState(List<Reservation> bookings, int availableRooms) {
        this.bookings = bookings;
        this.availableRooms = availableRooms;
    }
}

class PersistenceService {

    public void save(SystemState state, String file) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(state);
            out.close();
            System.out.println("State saved successfully");
        } catch (Exception e) {
            System.out.println("Error saving state");
        }
    }

    public SystemState load(String file) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            SystemState state = (SystemState) in.readObject();
            in.close();
            System.out.println("State loaded successfully");
            return state;
        } catch (Exception e) {
            System.out.println("No previous state found. Starting fresh.");
            return new SystemState(new ArrayList<>(), 5);
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {

        PersistenceService service = new PersistenceService();
        String file = "data.ser";

        SystemState state = service.load(file);

        state.bookings.add(new Reservation("RES201", "Oviya", 3000));
        state.bookings.add(new Reservation("RES202", "Dinesh", 2500));
        state.availableRooms -= 2;

        System.out.println("Current Bookings:");
        for (Reservation r : state.bookings) {
            System.out.println(r);
        }

        System.out.println("Available Rooms: " + state.availableRooms);

        service.save(state, file);
    }
}