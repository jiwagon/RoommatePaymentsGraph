import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @org.junit.jupiter.api.Test
    void addRoommate() {

        Graph graph = new Graph();

        // Test Case 1: Adding a single class
        graph.addRoommate("A");
        assertEquals(1, graph.roommates.size());
        assertEquals("A", graph.roommates.get(0).roommateName);

        // Test Case 2: Adding multiple classes
        graph.addRoommate("B");
        graph.addRoommate("C");
        assertEquals(3, graph.roommates.size());
        assertEquals("B", graph.roommates.get(1).roommateName);
        assertEquals("C", graph.roommates.get(2).roommateName);
    }

    @org.junit.jupiter.api.Test
    void addAmount() {
        Graph graph = new Graph();

        graph.addRoommate("A");
        graph.addRoommate("B");
        graph.addRoommate("C");
        graph.addRoommate("D");

        graph.addAmount("A", "B", 500.25);
        graph.addAmount("B", "C", 600.50);
        graph.addAmount("C", "D", 700.75);
        graph.addAmount("D", "A", 900.25);
        graph.addAmount("A", "C", 800.00);

        // Test Case 1: Assert that the edge was not added again and the weight was updated
        assertEquals(2, graph.roommates.get(0).edges.size());
        assertEquals(1, graph.roommates.get(1).edges.size());
        assertEquals(1, graph.roommates.get(2).edges.size());
        assertEquals(1, graph.roommates.get(3).edges.size());

        // Test Case 2:
        assertEquals("A", graph.roommates.get(0).edges.get(0).from.roommateName);
        assertEquals("B", graph.roommates.get(1).edges.get(0).from.roommateName);
        assertEquals(500.25, graph.roommates.get(0).edges.get(0).weight);
        assertEquals(600.50, graph.roommates.get(1).edges.get(0).weight);

        // Test Case 3: Assert that the edge "B" -> "C" was added correctly
        assertEquals("B", graph.roommates.get(1).edges.get(0).from.roommateName);
        assertEquals(600.50, graph.roommates.get(1).edges.get(0).weight);

        // Test Case 4: Assert that the edge "A" -> "C"
        // Current Expectation: "End Edge (Debt) already exists in Graph"
        // Should I update or add to the debt amount(weight)?
        graph.addAmount("A", "C", 100);
        assertEquals("C", graph.roommates.get(0).edges.get(1).to.roommateName);
        assertEquals(800.00, graph.roommates.get(0).edges.get(1).weight);

        System.out.println(graph.toString());
    }

    @Test
    void removeAmount() {
        Graph graph = new Graph();

        graph.addRoommate("A");
        graph.addRoommate("B");
        graph.addRoommate("C");
        graph.addRoommate("D");

        graph.addAmount("A", "B", 500.25);
        graph.addAmount("B", "C", 600.50);
        graph.addAmount("C", "D", 700.75);
        graph.addAmount("D", "A", 900.25);

        System.out.println("Original Debts");
        System.out.println(graph.toString());

        // Test case 1: Remove existing amount between roommates
        System.out.println("\nTest Case 1: ");
        // Expect: Successfully removed amount between A and B - A owes B $0.0
        graph.removeAmount("A", "B", 500.25);

        // Test case 2: Remove non-existing amount between roommates
        System.out.println("\nTest Case 2: ");
        // Expect: No edge (debt) exists between B and D
        graph.removeAmount("B", "D", 200.00);

        // Test case 3: Remove the remaining debt (Clearing the debt)
        // Expect: C paid $200.75 but still owes D $500.0
        System.out.println("\nTest Case 3: ");
        graph.removeAmount("C", "D", 200.75);
        // Expect: Successfully clear the debt between C & D - C owes D $0.0
        graph.removeAmount("C", "D", 500.0);

        // Test case 4: Remove amount between non-existing roommates
        System.out.println("\nTest Case 4: ");
        // Expect: Start and End vertex does not exist in Graph
        graph.removeAmount("E", "F", 300.75);
        // Current result: Roommate E & F already exists.
        graph.addRoommate("E");
        graph.addRoommate("F");
        // Current result: Roommate E & F already exists.
        graph.addAmount("E", "F", 100.50);
        // Expect: E paid 50.5 but still owes F $50.0
        graph.removeAmount("E", "F", 50.50);

        // Final Expectation:
        //
        // [B owes C $600.5];
        //
        // [D owes A $900.25];
        // [E owes F $50.0];
        System.out.println();
        System.out.println(graph.toString());
    }
}