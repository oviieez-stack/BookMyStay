/**
 * Book My Stay App
 * Version 5.1
 *
 * Demonstrates booking request handling using Queue (FIFO).
 *
 * @author Oviya
 * @version 5.1
 */

import java.util.*;

// Reservation Class
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Room: " + roomType);
    }
}

// Booking Queue Manager
class BookingQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    // Add request
    public void addRequest(Reservation r) {
        queue.add(r);
        System.out.println("Request Added: " + r.guestName);
    }

    // Show all requests
    public void displayQueue() {
        System.out.println("\n===== Booking Requests (FIFO) =====");

        for (Reservation r : queue) {
            r.display();
        }
    }
}

// Main Class
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v5.1 =====");

        BookingQueue bookingQueue = new BookingQueue();

        // Add booking requests
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));

        // Display queue (FIFO order)
        bookingQueue.displayQueue();
    }
}