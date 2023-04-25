import java.util.ArrayList;

public class Graph {

    ArrayList<Roommate> roommates; // List of vertices = roommates

    public Graph() {
        this.roommates = new ArrayList<>();
    }

    public int getIndex(String vertex) {
        for (int i = 0; i < this.roommates.size(); i++) {
            if (this.roommates.get(i).name == vertex)
                return i;
        }
        return -1;
    }

    public Roommate addRoommate(String roommate) {
        Roommate toAdd = new Roommate(roommate);

        if (getIndex(roommate) == -1) {
            roommates.add(toAdd);
        }
        else {
            System.out.println("Roommate" + roommate + " already exists.");
        }
        return toAdd;
    }

    public void addDistance(String roommateA, String roommateB, double distance) {
        Roommate start;
        Roommate end;
        // check if start vertex exists or not
        int startPos = this.getIndex(roommateA);
        if (startPos < 0) {
            start = this.addRoommate(roommateA);
        }
        else {
            start = this.roommates.get(startPos);
        }
        // check if end vertex exists or not
        int endPos = this.getIndex(roommateB);
        if (endPos < 0) {
            end = this.addRoommate(roommateB);
        }
        else {
            end = this.roommates.get(endPos);
        }

        for (int i = 0; i < start.edges.size(); i++) {
            if (start.edges.get(i).to.name == roommateB) {
                System.out.println("End Edge (Debt) already exists in Graph");
                return;
            }
        }

        // Add same edge from end to start
        start.edges.add(new Edge(start, end, distance));
        // add vertex to startVertex arraylist of edges if edge with endVertex is not existing
        // add vertex to endVertex arraylist of edges if edge with startVertex is not existing
    }

    // Get the string representation of the graph
    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < this.roommates.size(); i++) {
            for (Edge edge: this.roommates.get(i).edges) {
                stb = stb.append("[" + this.roommates.get(i).name);
                //stb = stb.append(", " + edge.from.name);
                stb = stb.append(", " + edge.to.name);
                stb = stb.append(", " + edge.weight + "]; ");
            }
            stb.append("\n");
        }
        return stb.toString();
    }
}
