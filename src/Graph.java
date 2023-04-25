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
        }
        else {
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
        }
        else {
            start = this.roommates.get(startPos);
        }
        // check if end vertex exists or not
        int endPos = this.getIndex(roommateB);
        if (endPos < 0) {
            System.out.println("End vertex (Roommate owed to) does not exist in Graph.");
            end = this.addRoommate(roommateB);
        }
        else {
            end = this.roommates.get(endPos);
        }

        boolean found = false;
        // Iterate through the edges of the start vertex (Roommate A)
        for (int i = 0; i < start.edges.size(); i++) {
            Edge edge = start.edges.get(i);
            if (edge.to.roommateName.equals(roommateB)) {
                // If edge (debt) exists between Roommate A and Roommate B, remove it
                if (amount == edge.weight) {
                    amount -= edge.weight;
                    start.edges.remove(i);
                    found = true;
                    System.out.println(edge.from.roommateName + " owes "
                            + edge.to.roommateName + " $" + amount);
                    // amount should equal to 0.
                    //break;
                }
                // If debt is larger than payment:
                else if (amount < edge.weight) {
                    edge.weight -= amount;
                    System.out.println(edge.from.roommateName + " paid " + amount +
                            " but still owes " + edge.to.roommateName + " $" + edge.weight);
                    // edge.weight should equal to the remaining debt
                }
                // If payment is larger than debt:
                else if (amount > edge.weight) {
                    amount -= edge.weight;
                    start.edges.remove(i);
                    System.out.println(edge.from.roommateName + " owes "
                            + edge.to.roommateName + " $0");
                    System.out.println(edge.from.roommateName + "'s remaining balance is "
                            + amount);
                }

                    boolean found1 = false;
                    // Iterate through the edges of the start vertex (Roommate A)
                    for (int j = 0; j < start.edges.size(); j++) {
                        Edge edge1 = start.edges.get(j);
                        if (edge.to.roommateName.equals(roommateB)) {
                            // If edge (debt) exists between Roommate A and Roommate B, remove it
                            if (amount == edge1.weight) {
                                amount -= edge1.weight;
                                start.edges.remove(j);
                                found1 = true;
                                // amount should equal to 0.
                                //j++;
                                if (amount == 0) {
                                    break;
                                }
                            }
                            else if (amount > edge1.weight) {
                                amount -= edge1.weight;
                                start.edges.remove(j);
                                System.out.println(edge1.from.roommateName + " owes "
                                        + edge1.to.roommateName + " $0");
                                System.out.println(edge1.from.roommateName + "'s remaining balance is "
                                        + amount);
                            }
                            else if (amount < edge1.weight) {
                                edge1.weight -= amount;
                                System.out.println(edge1.from.roommateName + " paid " + amount +
                                        " but still owes " + edge1.to.roommateName + " $" + edge1.weight);
                                // edge.weight should equal to the remaining debt
                            }
                        }
                    }
            }
            else if (!found) {
                System.out.println("Edge (Debt) does not exist between " +
                        edge.from.roommateName + " and " + edge.to.roommateName);
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
