import java.util.ArrayList;

public class Graph {

    ArrayList<Roommate> roommates; // List of vertices = roommates

    public Graph() {
        this.roommates = new ArrayList<>();
    }

    public int getIndex(String vertex) {
        for (int i = 0; i < this.roommates.size(); i++) {
            if (this.roommates.get(i).roommateName == vertex)
                return i;
        }
        return -1;
    }

    public Roommate addRoommate(String roommate) {
        Roommate toAdd = new Roommate(roommate);

        if (getIndex(roommate) == -1) {
            roommates.add(toAdd);
        } else {
            System.out.println("Roommate " + roommate + " already exists.");
        }
        return toAdd;
    }

    public void addAmount(String roommateA, String roommateB, double amount) {
        Roommate start;
        Roommate end;
        // check if start vertex exists or not
        int startPos = this.getIndex(roommateA);
        if (startPos < 0) {
            start = this.addRoommate(roommateA);
        } else {
            start = this.roommates.get(startPos);
        }
        // check if end vertex exists or not
        int endPos = this.getIndex(roommateB);
        if (endPos < 0) {
            end = this.addRoommate(roommateB);
        } else {
            end = this.roommates.get(endPos);
        }

        for (int i = 0; i < start.edges.size(); i++) {
            if (start.edges.get(i).to.roommateName == roommateB) {
                System.out.println("End Edge (Debt) already exists in Graph");
                return;
            }
        }

        // Add same edge from end to start
        start.edges.add(new Edge(start, end, amount));
        // add vertex to startVertex arraylist of edges if edge with endVertex is not existing
    }

    public void removeAmount(String roommateA, String roommateB, double amount) {
        Roommate start;
        Roommate end;
        // check if start vertex exists or not
        int startPos = this.getIndex(roommateA);
        if (startPos < 0) {
            // Q: How do we get rid of this but still initialize start?
            System.out.println("Start vertex (Roommate with debt) does not exist in Graph.");
            start = this.addRoommate(roommateA);
        } else {
            start = this.roommates.get(startPos);
        }

        // check if end vertex exists or not
        int endPos = this.getIndex(roommateB);
        if (endPos < 0) {
            System.out.println("End vertex (Roommate owed to) does not exist in Graph.");
            end = this.addRoommate(roommateB);
        } else {
            end = this.roommates.get(endPos);
        }

        //boolean found = false;
        // Iterate through the edges of the start vertex (Roommate A)
        for (int i = 0; i < start.edges.size(); i++) {
            Edge edge = start.edges.get(i);
            // Keep iterating until the desired end vertex is found or all the edges have been checked
            if (edge == null) {
                edge = edge.next;
            }
            while (edge != null) {
                // If edge (debt) exists between Roommate A and Roommate B
                if (amount >= edge.weight && edge.weight != 0) {
                    // If payment is greater than or equal to debt
                    amount -= edge.weight;
                    start.edges.remove(i);
                    System.out.println(edge.from.roommateName + " paid " + edge.weight +
                            " to " + edge.to.roommateName);
                    System.out.println(edge.from.roommateName + "'s remaining balance is "
                            + amount);
                    if (amount == 0) { // If remaining balance is 0, break out of the loop
                        break;
                    } else if (amount > 0) {
                        if (start.edges.size() == 0) {
                            amount = 0;
                            System.out.println(edge.from.roommateName + "'s debts are all cleared. Remaining balance is returned.");
                            break;
                        }
                        else {
                            edge = start.edges.get(i);
                        }
                    }
                } else if (amount < edge.weight) {
                    edge.weight -= amount;
                    System.out.println(edge.from.roommateName + " paid " + amount +
                            " but still owes " + edge.to.roommateName + " $" + edge.weight);
                    // edge.weight should equal to the remaining debt
                    amount = 0;
                    break;
                }
            }
        }
    }

    // Get the string representation of the graph
    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < this.roommates.size(); i++) {
            for (Edge edge: this.roommates.get(i).edges) {
                stb = stb.append("[" + this.roommates.get(i).roommateName);
                //stb = stb.append(", " + edge.from.name);
                stb = stb.append(" owes " + edge.to.roommateName);
                stb = stb.append(" $" + edge.weight + "]; ");
            }
            //stb.append("\n");
        }
        return stb.toString();
    }
}
