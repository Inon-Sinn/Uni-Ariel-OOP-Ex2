import Algorithms.DWG_algo;
import Algorithms.Dijkstra;
import Classes.DWG;
import Classes.GeoLoc;
import Classes.NodeData;
import Classes.dwgFromJson;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
        graph1.connect(n3.getKey(),n2.getKey(),5);
        graph1.connect(n2.getKey(),n3.getKey(),5);
        algo1.init(graph1);
        assertTrue(algo1.isConnected());
        graph1.connect(n3.getKey(),n1.getKey(),5);
        graph1.connect(n1.getKey(),n3.getKey(),5);
        algo1.init(graph1);
        assertTrue(algo1.isConnected());
    }

    @Test
    void shortestPathDist() {
        GeoLoc g = new GeoLoc(0,0,0);

        NodeData node1 = new NodeData(1,g);
        NodeData node2 = new NodeData(2,g);
        NodeData node3 = new NodeData(3,g);
        NodeData node4 = new NodeData(4,g);

        DWG g1 = new DWG();
        g1.addNode(node1);
        g1.addNode(node2);
        g1.addNode(node3);
        g1.addNode(node4);

        g1.connect(node1.getKey(),node2.getKey(),5);
        g1.connect(node1.getKey(),node3.getKey(),10);
        g1.connect(node2.getKey(),node3.getKey(),4);
        g1.connect(node2.getKey(),node4.getKey(),1);
        g1.connect(node3.getKey(),node2.getKey(),3);
        g1.connect(node4.getKey(),node3.getKey(),2);

        DWG_algo algo1 = new DWG_algo();
        algo1.init(g1);

        assertEquals(algo1.shortestPathDist(1,2),5);
        assertEquals(algo1.shortestPathDist(1,3),8);
        assertEquals(algo1.shortestPathDist(1,4),6);

        assertEquals(algo1.shortestPathDist(2,1),-1);
        assertEquals(algo1.shortestPathDist(2,3),3);
        assertEquals(algo1.shortestPathDist(2,4),1);

        assertEquals(algo1.shortestPathDist(3,1),-1);
        assertEquals(algo1.shortestPathDist(3,2),3);
        assertEquals(algo1.shortestPathDist(3,4),4);

        assertEquals(algo1.shortestPathDist(4,1),-1);
        assertEquals(algo1.shortestPathDist(4,2),5);
        assertEquals(algo1.shortestPathDist(4,3),2);



    }

    @Test
    void shortestPath() {
        GeoLoc g = new GeoLoc(0,0,0);

        NodeData node1 = new NodeData(1,g);
        NodeData node2 = new NodeData(2,g);
        NodeData node3 = new NodeData(3,g);
        NodeData node4 = new NodeData(4,g);

        DWG g1 = new DWG();
        g1.addNode(node1);
        g1.addNode(node2);
        g1.addNode(node3);
        g1.addNode(node4);

        g1.connect(node1.getKey(),node2.getKey(),5);
        g1.connect(node1.getKey(),node3.getKey(),10);
        g1.connect(node2.getKey(),node3.getKey(),4);
        g1.connect(node2.getKey(),node4.getKey(),1);
        g1.connect(node3.getKey(),node2.getKey(),3);
        g1.connect(node4.getKey(),node3.getKey(),2);

        DWG_algo algo1 = new DWG_algo();
        algo1.init(g1);

        ArrayList<NodeData> nodes = (ArrayList) algo1.shortestPath(1,3);
        for(NodeData node:nodes)
            System.out.println(node.getKey() + " -> ");

    }

    @Test
    void center() {
        GeoLoc g = new GeoLoc(0,0,0);

        NodeData node1 = new NodeData(1,g);
        NodeData node2 = new NodeData(2,g);
        NodeData node3 = new NodeData(3,g);
        NodeData node4 = new NodeData(4,g);

        DWG g1 = new DWG();
        g1.addNode(node1);
        g1.addNode(node2);
        g1.addNode(node3);
        g1.addNode(node4);

        g1.connect(node1.getKey(),node2.getKey(),5);
        g1.connect(node1.getKey(),node3.getKey(),10);
        g1.connect(node2.getKey(),node3.getKey(),4);
        g1.connect(node2.getKey(),node4.getKey(),1);
        g1.connect(node3.getKey(),node2.getKey(),3);
        g1.connect(node4.getKey(),node3.getKey(),2);

        DWG_algo algo1 = new DWG_algo();
        algo1.init(g1);
        assertNull(algo1.center());

        g1.connect(node3.getKey(),node1.getKey(),20);
        algo1.init(g1);
        assertEquals(algo1.center().getKey(),1);
    }

    @Test
    void tsp() {
        GeoLoc g = new GeoLoc(0,0,0);

        NodeData node1 = new NodeData(1,g);
        NodeData node2 = new NodeData(2,g);
        NodeData node3 = new NodeData(3,g);
        NodeData node4 = new NodeData(4,g);

        DWG g1 = new DWG();
        g1.addNode(node1);
        g1.addNode(node2);
        g1.addNode(node3);
        g1.addNode(node4);

        g1.connect(node1.getKey(),node2.getKey(),5);
        g1.connect(node1.getKey(),node3.getKey(),10);
        g1.connect(node2.getKey(),node3.getKey(),4);
        g1.connect(node2.getKey(),node4.getKey(),1);
        g1.connect(node3.getKey(),node2.getKey(),3);
        g1.connect(node4.getKey(),node3.getKey(),2);

        DWG_algo algo1 = new DWG_algo();
        algo1.init(g1);

        //Normal test
        ArrayList<api.NodeData> cities = new ArrayList<>();
        cities.add(node1);
        //cities.add(node2);
        //cities.add(node3);
        //cities.add(node4);
        ArrayList<NodeData> nodes = (ArrayList) algo1.tsp(cities);
        for(NodeData node:nodes)
            System.out.println(node.getKey() + " -> ");

        //Test of an unreachable node - return null
        NodeData node5 = new NodeData(5,g);

        g1.addNode(node5);
        algo1.init(g1);
        //cities.add(node1);
        cities.add(node5);
        nodes = (ArrayList) algo1.tsp(cities);
        assertNull(nodes);

        // Test - duplicated cities TODO
        cities.remove(0);
        cities.add(node1);
        cities.add(node1);
        nodes = (ArrayList) algo1.tsp(cities);
        for(NodeData node:nodes)
            System.out.println(node.getKey() + " -> ");

        cities.add(node1);
        cities.add(node1);
        cities.add(node5);
        nodes = (ArrayList) algo1.tsp(cities);
        assertNull(nodes);
    }

    @Test
    void save() {
        DWG_algo algo = new DWG_algo();
        algo.load("data\\G1.json");
        algo.save("test1.json");
    }

    @Test
    void load() {
        // visual test by debugging
        DWG_algo algo = new DWG_algo();
        algo.load("G1.json");
        DWG dwg = (DWG) algo.getGraph();

        algo.load("G2.json");
        dwg = (DWG)algo.getGraph();

        algo.load("G3.json");
        dwg = (DWG)algo.getGraph();
    }
}