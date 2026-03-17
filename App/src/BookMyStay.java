/**
 * Book My Stay App
 * Version 2.1
 *
 * Demonstrates Room abstraction, inheritance, and basic availability.
 *
 * @author Oviya
 * @version 2.1
 */

// Abstract Class
abstract class Room {
    String type;
    int beds;
    double price;

    // Constructor
    Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    // Display method
    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: $" + price);
    }
}

// Single Room
class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 1, 1000);
    }
}

// Double Room
class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 2, 1800);
    }
}

// Suite Room
class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 3, 3000);
    }
}

// Main Class
public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v2.1 =====");

        // Create room objects (Polymorphism)
        Room r1 = new SingleRoom();
        Room r2 = new DoubleRoom();
        Room r3 = new SuiteRoom();

        // Static availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Display details
        r1.displayDetails();
        System.out.println("Available: " + singleAvailable);
        System.out.println("------------------------");

        r2.displayDetails();
        System.out.println("Available: " + doubleAvailable);
        System.out.println("------------------------");

        r3.displayDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}