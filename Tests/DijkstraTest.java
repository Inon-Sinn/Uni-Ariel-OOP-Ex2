import Algorithms.Dijkstra;
import Classes.DWG;
import Classes.GeoLoc;
import Classes.NodeData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {




    @BeforeEach
    void setUp() {
        GeoLoc g = new GeoLoc(0,0,0);

        NodeData n1 = new NodeData(1,g);
        NodeData n2 = new NodeData(2,g);
        NodeData n3 = new NodeData(3,g);
        NodeData n4 = new NodeData(4,g);

        DWG g1 = new DWG();
        g1.addNode(n1);
        g1.addNode(n2);
        g1.addNode(n3);
        g1.addNode(n4);

        g1.connect(n1.getKey(),n2.getKey(),5);
        g1.connect(n1.getKey(),n3.getKey(),10);
        g1.connect(n2.getKey(),n3.getKey(),4);
        g1.connect(n2.getKey(),n4.getKey(),1);
        g1.connect(n3.getKey(),n2.getKey(),3);
        g1.connect(n4.getKey(),n3.getKey(),2);

        Dijkstra d1 = new Dijkstra(g1,n1.getKey());
    }

    @Test
    void relax() {
        System.out.println("hi?");
    }

    @Test
    void shortestPathDis() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void maxWeight() {
    }
}