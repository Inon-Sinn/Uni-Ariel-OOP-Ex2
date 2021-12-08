package Testers;

import Classes.DWG;
import Classes.GeoLoc;
import Classes.NodeData;
import org.junit.jupiter.api.Test;

class DirectedWeightedGraphTest {

    @Test
    void getNode() {
    DWG dwg = new DWG();
    GeoLoc geoLoc = new GeoLoc(1,1,1);
    NodeData node = new NodeData(1, geoLoc);
    dwg.addNode(node);
    System.out.println(dwg.getNode(1).getInfo());

    }

    @Test
    void getEdge() {
    }

    @Test
    void addNode() {
    }

    @Test
    void connect() {
    }

    @Test
    void nodeIter() {
    }

    @Test
    void edgeIter() {
    }

    @Test
    void testEdgeIter() {
    }

    @Test
    void removeNode() {
    }

    @Test
    void removeEdge() {
    }

    @Test
    void nodeSize() {
    }

    @Test
    void edgeSize() {
    }

    @Test
    void getMC() {
    }
}