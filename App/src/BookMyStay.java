import java.util.*;

class Reservation {
    String reservationId;
    String roomType;

    Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}

class Inventory {
    private Map<String, Integer> rooms = new HashMap<>();

    Inventory() {
        rooms.put("Standard", 1);
        rooms.put("Deluxe", 1);
    }

    public boolean allocate(String type) {
        if (rooms.containsKey(type) && rooms.get(type) > 0) {
            rooms.put(type, rooms.get(type) - 1);
            return true;
        }
        return false;
    }

    public void release(String type) {
        rooms.put(type, rooms.get(type) + 1);
    }

    public void display() {
        System.out.println("Inventory: " + rooms);
    }
}

class CancellationService {
    private Map<String, Reservation> bookings = new HashMap<>();
    private Stack<String> rollbackStack = new Stack<>();
    private Inventory inventory;

    CancellationService(Inventory inventory) {
        this.inventory = inventory;
    }

    public void book(String id, String type) {
        if (inventory.allocate(type)) {
            bookings.put(id, new Reservation(id, type));
            rollbackStack.push(type);
            System.out.println("Booked: " + id + " (" + type + ")");
        } else {
            System.out.println("Booking failed: " + type);
        }
    }

    public void cancel(String id) {
        if (!bookings.containsKey(id)) {
            System.out.println("Cancellation failed: Invalid ID");
            return;
        }

        Reservation r = bookings.remove(id);
        if (!rollbackStack.isEmpty()) {
            String type = rollbackStack.pop();
            inventory.release(type);
            System.out.println("Cancelled: " + id + " (" + r.roomType + ")");
        }
    }
}

public class BookMystay {
    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        CancellationService service = new CancellationService(inventory);

        service.book("RES1", "Standard");
        service.book("RES2", "Deluxe");

        inventory.display();

        service.cancel("RES1");
        service.cancel("RES3");

        inventory.display();
    }
}