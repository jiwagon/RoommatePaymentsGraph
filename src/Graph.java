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

        boolean found = false;
        // Iterate through the edges of the start vertex (Roommate A)
        for (int i = 0; i < start.edges.size(); i++) {
            Edge edge = start.edges.get(i);
            // Keep iterating until the desired end vertex is found or all the edges have been checked
            if (edge == null) {
                edge = edge.next;
            }
            while (amount > 0 && edge != null) {
                // If edge (debt) exists between Roommate A and Roommate B
                if (amount >= edge.weight) {
                    // If payment is greater than or equal to debt
                    amount -= edge.weight;
                    start.edges.remove(i);
                    System.out.println(edge.from.roommateName + " paid " + edge.weight +
                            " to " + edge.to.roommateName);
                    System.out.println(edge.from.roommateName + "'s remaining balance is "
                            + amount);
                    if (amount == 0) { // If remaining balance is 0, break out of the loop
                        found = true;
                        break;
                    } else if (amount > 0) {
                        edge = start.edges.get(i).next;
                        continue;
                    }
                } else if (amount < edge.weight) {
                    edge.weight -= amount;
                    System.out.println(edge.from.roommateName + " paid " + amount +
                            " but still owes " + edge.to.roommateName + " $" + edge.weight);
                    // edge.weight should equal to the remaining debt
                    amount = 0;
                    break;
                }
                //break; // Break out of the loop since payment is made and no more debts to pay
            }
        }
    }

//                    Roommate current = edge.to; // The vertex where the debt is being paid
//                    while (amount > 0 && !found) { // Continue until remaining balance is 0 or no more debts to pay
//                        for (int j = 0; j < current.edges.size(); j++) { // Iterate through edges of current vertex
//                            Edge nextEdge = current.edges.get(j);
//                            if (nextEdge.weight <= amount) { // Check if debt can be fully paid
//                                amount -= nextEdge.weight;
//                                System.out.println(current.roommateName + " paid " + nextEdge.weight +
//                                        " to " + nextEdge.to.roommateName);
//                                System.out.println(current.roommateName + "'s remaining balance is " + amount);
//                                current.edges.remove(j); // Remove the edge from the list since it's fully paid
//                                //edge = start.edges.get(i).next;
//                                if (amount == 0) { // If remaining balance is 0, break out of the loop
//                                    found = true;
//                                    break;
//                                }
//                            } else { // If debt cannot be fully paid, pay what's left of the remaining balance
//                                System.out.println(current.roommateName + " paid " + amount +
//                                        " to " + nextEdge.to.roommateName);
//                                nextEdge.weight -= amount;
//                                System.out.println(current.roommateName + " still owes " + nextEdge.to.roommateName + " $" + nextEdge.weight);
//                                //amount = 0; // Set amount to 0 since remaining balance is paid
//                                break; // Break out of the loop since no more debts can be paid with the remaining balance
//                            }
//                        }
//                    }

                 // If there are still debts to be paid, move on to the next vertex
//                        if (current.edges.size() > 0) {
//                            current = current.edges.get(0).to;
//                        } else {
//                            break; // Break out of the loop if there are no more debts to be paid
//                        }
//                if (!found) {
//                    System.out.println("Could not pay off all debts with remaining balance.");
//                }
//                if (amount == 0) {
//                    found = true;
//                    break;
//                }

//        if (found) {
//                    System.out.println("Edge (Debt) does not exist between " +
//                            start.roommateName + " and " + end.roommateName);
//            break;



//                while (amount > 0 && !found) {
//                     Iterate through the edges of the start vertex (Roommate A)
//                    for (int j = 0; j < start.edges.size(); j++) {
//                        Edge edge1 = start.edges.get(j);
//                        if (edge.to.roommateName.equals(roommateB)) {
//                            // If edge (debt) exists between Roommate A and Roommate B, remove it
//                            if (amount == edge1.weight) {
//                                amount -= edge1.weight;
//                                start.edges.remove(j);
//                                found = true;
//                                // amount should equal to 0.
//                                if (amount == 0) {
//                                    break;
//                                }
//                            } else if (amount > edge1.weight) {
//                                amount -= edge1.weight;
//                                start.edges.remove(j);
//                                System.out.println(edge1.from.roommateName + " owes "
//                                        + edge1.to.roommateName + " $0");
//                                System.out.println(edge1.from.roommateName + "'s remaining balance is "
//                                        + amount);
//
//                            } else if (amount < edge1.weight) {
//                                edge1.weight -= amount;
//                                System.out.println(edge1.from.roommateName + " paid " + amount +
//                                        " but still owes " + edge1.to.roommateName + " $" + edge1.weight);
//                                // edge.weight should equal to the remaining debt
//                                break;
//                            }
//                        }
//                                else if (edge.weight == 0) {
//                                    break; // Break out of the loop if amount becomes 0
//                                }
//                    }
//                }


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
