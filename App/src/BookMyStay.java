/**
 * Book My Stay App
 * Version 3.1
 *
 * Demonstrates centralized room inventory management using HashMap.
 *
 * @author Oviya
 * @version 3.1
 */

import java.util.HashMap;

// Inventory Class
class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor
    public RoomInventory() {
        inventory = new HashMap<>();

        // Initialize room availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("===== Room Inventory =====");
        for (String type : inventory.keySet()) {
            System.out.println(type + " → Available: " + inventory.get(type));
        }
    }
}

// Main Class
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v3.1 =====");

        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Update inventory
        inventory.updateAvailability("Single Room", 4);

        System.out.println("\nAfter Update:");
        inventory.displayInventory();
    }
}