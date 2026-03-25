import java.util.*;

class AddOnService {
    String name;
    double cost;

    AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String toString() {
        return name + " (Rs." + cost + ")";
    }
}

class AddOnServiceManager {
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    public List<AddOnService> getServices(String reservationId) {
        return serviceMap.getOrDefault(reservationId, new ArrayList<>());
    }

    public double getTotalCost(String reservationId) {
        double total = 0;
        for (AddOnService s : getServices(reservationId)) {
            total += s.cost;
        }
        return total;
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "RES101";

        manager.addService(reservationId, new AddOnService("Breakfast", 200));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 500));
        manager.addService(reservationId, new AddOnService("Extra Bed", 300));

        List<AddOnService> services = manager.getServices(reservationId);

        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Selected Services:");
        for (AddOnService s : services) {
            System.out.println(s);
        }

        System.out.println("Total Add-On Cost: Rs." + manager.getTotalCost(reservationId));
    }
}