public class Edge {

    Roommate from; // Source vertex
    Roommate to; // Destination vertex
    double weight; // Amount owed from source to destination

    Edge next; // use this.next instead of next

    public Edge(Roommate from, Roommate to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.next = null; // initialize this.next to null
    }

    @Override
    public String toString() {
        return "[" + from.roommateName + ", " + to.roommateName + ", " + weight + "]";
    }
}
