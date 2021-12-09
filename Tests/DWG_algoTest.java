import Algorithms.DWG_algo;
import Classes.DWG;
import Classes.GeoLoc;
import Classes.NodeData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DWG_algoTest {

    GeoLoc g1 = new GeoLoc(0,0,0);
    GeoLoc g2 = new GeoLoc(7,4,3);
    GeoLoc g3 = new GeoLoc(17,6,2);

    NodeData n1 = new NodeData(0,g1);
    NodeData n2 = new NodeData(1,g2);
    NodeData n3 = new NodeData(2,g3);

    DWG graph1 = new DWG();



    @Test
    void init() {
        DWG_algo algo1 = new DWG_algo();
        algo1.init(graph1);
        System.out.println("hi");
    }

    @Test
    void getGraph() {

    }

    @Test
    void copy() {
        graph1.addNode(n1);
        graph1.addNode(n2);
        graph1.addNode(n3);
        graph1.connect(n1.getKey(),n2.getKey(),5);
        DWG_algo algo1 = new DWG_algo();
        algo1.init(graph1);
        DWG copy = (DWG) algo1.copy();
        assertEquals(copy.nodeSize(),3);
        assertEquals(copy.edgeSize(),1);
        assertEquals(copy.getEdge(n1.getKey(),n2.getKey()).getWeight(),5);
    }

    @Test
    void reverseCopy() {
        graph1.addNode(n1);
        graph1.addNode(n2);
        graph1.addNode(n3);
        graph1.connect(n1.getKey(),n2.getKey(),5);
        DWG_algo algo1 = new DWG_algo();
        algo1.init(graph1);
        DWG Reverse_copy = (DWG) algo1.ReverseCopy();
        assertEquals(Reverse_copy.nodeSize(),3);
        assertEquals(Reverse_copy.edgeSize(),1);
        assertNull(Reverse_copy.getEdge(n1.getKey(),n2.getKey()));
        assertEquals(Reverse_copy.getEdge(n2.getKey(),n1.getKey()).getWeight(),5);
    }

    @Test
    void isConnected() {
        graph1.addNode(n1);
        graph1.addNode(n2);
        graph1.addNode(n3);
        DWG_algo algo1 = new DWG_algo();
        algo1.init(graph1);
        assertFalse(algo1.isConnected());
        graph1.connect(n1.getKey(),n2.getKey(),5);
        graph1.connect(n2.getKey(),n1.getKey(),5);
        algo1.init(graph1);
        assertFalse(algo1.isConnected());
    }

    @Test
    void shortestPathDist() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void center() {
    }

    @Test
    void tsp() {
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}