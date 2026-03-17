/**
 * Book My Stay App
 * Version 4.1
 *
 * Demonstrates read-only room search using inventory.
 *
 * @author Oviya
 * @version 4.1
 */

import java.util.*;

// Room (same as UC2 simplified)
abstract class Room {
    String type;
    int beds;
    double price;

    Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println(type + " | Beds: " + beds + " | Price: ₹" + price);
    }
}

class SingleRoom extends Room {
    SingleRoom() { super("Single Room", 1, 1000); }
}

class DoubleRoom extends Room {
    DoubleRoom() { super("Double Room", 2, 1800); }
}

class SuiteRoom extends Room {
    SuiteRoom() { super("Suite Room", 3, 3000); }
}

// Inventory (read-only usage)
class RoomInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0); // unavailable
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public Set<String> getAllRoomTypes() {
        return inventory.keySet();
    }
}

// Search Service
class RoomSearchService {

    public static void searchAvailableRooms(RoomInventory inventory) {

        System.out.println("===== Available Rooms =====");

        // Room objects
        Map<String, Room> roomMap = new HashMap<>();
        roomMap.put("Single Room", new SingleRoom());
        roomMap.put("Double Room", new DoubleRoom());
        roomMap.put("Suite Room", new SuiteRoom());

        for (String type : inventory.getAllRoomTypes()) {
            int available = inventory.getAvailability(type);

            // Filter only available rooms
            if (available > 0) {
                roomMap.get(type).displayDetails();
                System.out.println("Available: " + available);
                System.out.println("----------------------");
            }
        }
    }
}

// Main Class
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v4.1 =====");

        RoomInventory inventory = new RoomInventory();

        // Read-only search
        RoomSearchService.searchAvailableRooms(inventory);
    }
}