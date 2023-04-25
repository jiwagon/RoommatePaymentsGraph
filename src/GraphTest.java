import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @org.junit.jupiter.api.Test
    void addVertex() {

        Graph graph = new Graph();

        // Test adding a single class
        graph.addRoommate("A");
        assertEquals(1, graph.roommates.size());
        assertEquals("A", graph.roommates.get(0).name);

        // Test adding multiple classes
        graph.addRoommate("B");
        graph.addRoommate("C");
        assertEquals(3, graph.roommates.size());
        assertEquals("B", graph.roommates.get(1).name);
        assertEquals("C", graph.roommates.get(2).name);
    }

    @org.junit.jupiter.api.Test
    void addEdge() {
        // Create graph
        Graph graph = new Graph();

        graph.addRoommate("A");
        graph.addRoommate("B");
        graph.addRoommate("C");
        graph.addRoommate("D");

        graph.addDistance("A", "B", 500.25);
        graph.addDistance("B", "C", 600.50);
        graph.addDistance("C", "D", 700.75);
        graph.addDistance("D", "A", 900.25);
        graph.addDistance("A", "C", 800.00);

        // Assert that the edge was not added again and the weight was updated
        assertEquals(2, graph.roommates.get(0).edges.size());
        assertEquals(1, graph.roommates.get(1).edges.size());
        assertEquals(1, graph.roommates.get(2).edges.size());
        assertEquals(1, graph.roommates.get(3).edges.size());

        assertEquals("B", graph.roommates.get(0).edges.get(0).to.name);
        assertEquals("B", graph.roommates.get(1).edges.get(0).from.name);
        assertEquals(500.25, graph.roommates.get(0).edges.get(0).weight);
        assertEquals(600.50, graph.roommates.get(1).edges.get(0).weight);

        // Assert that the edge "B" -> "C" was added correctly
        assertEquals("B", graph.roommates.get(1).edges.get(0).from.name);
        assertEquals(600.50, graph.roommates.get(1).edges.get(0).weight);

        // Assert that the edge "A" -> "C"
        // Current Result: "End Edge (Debt) already exists in Graph"
        // Should I update or add to the debt amount(weight)?
        graph.addDistance("A", "C", 100);
        assertEquals("C", graph.roommates.get(0).edges.get(1).to.name);
        assertEquals(800.00, graph.roommates.get(0).edges.get(1).weight);

        System.out.println(graph);
    }
}