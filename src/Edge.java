public class Edge {

    Roommate from; // Source vertex
    Roommate to; // Destination vertex
    double weight; // Amount owed from source to destination

    public Edge(Roommate from, Roommate to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "[" + from.name + ", " + to.name + ", " + weight + "]";
    }
}
