import java.util.*;

class BookingRequest {
    String guestName;

    BookingRequest(String guestName) {
        this.guestName = guestName;
    }
}

class Inventory {
    private int rooms;

    Inventory(int rooms) {
        this.rooms = rooms;
    }

    public synchronized boolean allocateRoom(String guestName) {
        if (rooms > 0) {
            System.out.println(guestName + " booked a room");
            rooms--;
            return true;
        } else {
            System.out.println(guestName + " failed (No rooms)");
            return false;
        }
    }
}

class BookingProcessor implements Runnable {
    private Queue<BookingRequest> queue;
    private Inventory inventory;

    BookingProcessor(Queue<BookingRequest> queue, Inventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {
        while (true) {
            BookingRequest request;

            synchronized (queue) {
                if (queue.isEmpty()) {
                    break;
                }
                request = queue.poll();
            }

            inventory.allocateRoom(request.guestName);
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {

        Queue<BookingRequest> queue = new LinkedList<>();
        queue.add(new BookingRequest("Dinesh"));
        queue.add(new BookingRequest("Oviya"));
        queue.add(new BookingRequest("Arun"));
        queue.add(new BookingRequest("Kiran"));
        queue.add(new BookingRequest("Meena"));

        Inventory inventory = new Inventory(3);

        Thread t1 = new Thread(new BookingProcessor(queue, inventory));
        Thread t2 = new Thread(new BookingProcessor(queue, inventory));

        t1.start();
        t2.start();
    }
}