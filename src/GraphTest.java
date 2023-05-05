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

        graph.addAmount("A", "B", 10.25);
        graph.addAmount("A", "C", 20.50);
        graph.addAmount("A", "D", 10.25);

        System.out.println(graph);
        graph.removeAmount("A", "B", 50.00);
        System.out.println(graph);

        graph.addAmount("D", "A", 500);
        graph.addAmount("D", "B", 200);
        graph.addAmount("D", "C", 200);
        graph.addAmount("D", "E", 100);
        graph.addAmount("D", "L", 50.50);

        System.out.println(graph);
        graph.removeAmount("D", "A", 1000.00);
        System.out.println(graph);
    }
}