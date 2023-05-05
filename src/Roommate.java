import java.util.ArrayList;

public class Roommate {

    // Constructors:
    // Roommate name
    String roommateName;
    double amount; // Total amount owed by this roommate

    ArrayList<Edge> edges;
    public Roommate(String roommateName) {
        this.roommateName = roommateName;
        this.amount = 0.0;
        this.edges = new ArrayList<>();
    }
}
