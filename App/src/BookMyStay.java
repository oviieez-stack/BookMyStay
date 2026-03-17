/**
 * Book My Stay App
 * Version 6.1
 *
 * Demonstrates reservation confirmation and room allocation
 * using Queue, HashMap, and Set to prevent double booking.
 *
 * @author Oviya
 * @version 6.1
 */

import java.util.*;

// Reservation
class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Inventory
class RoomInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void reduceAvailability(String type) {
        inventory.put(type, getAvailability(type) - 1);
    }

    public void display() {
        System.out.println("Inventory: " + inventory);
    }
}

// Booking Service
class BookingService {

    private Queue<Reservation> queue;
    private RoomInventory inventory;

    // roomType -> allocated room IDs
    private HashMap<String, Set<String>> allocatedRooms = new HashMap<>();

    public BookingService(Queue<Reservation> queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    // Process bookings
    public void processBookings() {

        while (!queue.isEmpty()) {

            Reservation r = queue.poll();

            String type = r.roomType;

            if (inventory.getAvailability(type) > 0) {

                // Generate unique room ID
                String roomId = type.substring(0, 2).toUpperCase() + "_" + UUID.randomUUID().toString().substring(0, 5);

                // Store in Set (no duplicates)
                allocatedRooms.putIfAbsent(type, new HashSet<>());
                allocatedRooms.get(type).add(roomId);

                // Update inventory
                inventory.reduceAvailability(type);

                System.out.println("Booking Confirmed: " + r.guestName +
                        " → " + type + " | Room ID: " + roomId);

            } else {
                System.out.println("Booking Failed (No Availability): " + r.guestName +
                        " → " + type);
            }
        }
    }

    public void displayAllocations() {
        System.out.println("\nAllocated Rooms:");
        System.out.println(allocatedRooms);
    }
}

// Main Class
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v6.1 =====");

        // Queue (FIFO)
        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Alice", "Single Room"));
        queue.add(new Reservation("Bob", "Single Room"));
        queue.add(new Reservation("Charlie", "Single Room")); // will fail
        queue.add(new Reservation("David", "Suite Room"));

        RoomInventory inventory = new RoomInventory();

        BookingService service = new BookingService(queue, inventory);

        // Process bookings
        service.processBookings();

        // Show results
        service.displayAllocations();
        inventory.display();
    }
}