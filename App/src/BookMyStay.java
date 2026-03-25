import java.util.*;

class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

class Inventory {
    private Map<String, Integer> rooms = new HashMap<>();

    Inventory() {
        rooms.put("Standard", 2);
        rooms.put("Deluxe", 1);
        rooms.put("Suite", 0);
    }

    public void bookRoom(String type) throws InvalidBookingException {
        if (!rooms.containsKey(type)) {
            throw new InvalidBookingException("Invalid room type");
        }

        if (rooms.get(type) <= 0) {
            throw new InvalidBookingException("No rooms available for " + type);
        }

        rooms.put(type, rooms.get(type) - 1);
        System.out.println("Room booked successfully: " + type);
    }
}

public class BookMyStay {
    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        String[] requests = {"Deluxe", "Suite", "Premium", "Standard", "Standard"};

        for (String req : requests) {
            try {
                inventory.bookRoom(req);
            } catch (InvalidBookingException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}