package Classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeDataTest {

    GeoLoc g1 = new GeoLoc(0,0,0);
    GeoLoc g2 = new GeoLoc(7,4,3);
    GeoLoc g3 = new GeoLoc(17,6,2);

    NodeData n1 = new NodeData(0,g1);
    NodeData n2 = new NodeData(1,g2);
    NodeData n3 = new NodeData(2,g3);

    @Test
    void getKey() {
    }

    @Test
    void getLocation() {
    }

    @Test
    void setLocation() {
    }

    @Test
    void getWeight() {
    }

    @Test
    void setWeight() {
    }

    @Test
    void getInfo() {
    }

    @Test
    void setInfo() {
    }

    @Test
    void getTag() {
    }

    @Test
    void setTag() {
    }

    @Test
    void getEdge() {
    }

    @Test
    void removeEdge() {
    }

    @Test
    void getEdgeCollection() {
    }

    @Test
    void addEdge() {
    }

    @Test
    void getPointers() {
    }

    @Test
    void addPointer() {
    }

    @Test
    void removePoiner() {
    }
}