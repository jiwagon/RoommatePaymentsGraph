import java.util.ArrayList;

public class Roommate {

    // Constructors:
    // Roommate name
    String name;
    double amount; // Total amount owed by this roommate

    ArrayList<Edge> edges;
    public Roommate(String name) {
        this.name = name;
        this.amount = 0.0;
        this.edges = new ArrayList<Edge>();
    }
}
