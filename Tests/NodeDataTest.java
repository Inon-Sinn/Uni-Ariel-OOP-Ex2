import Classes.EdgeData;
import Classes.GeoLoc;
import Classes.NodeData;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

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
        assertEquals(n1.getKey(),0);
        assertEquals(n2.getKey(),1);
        assertEquals(n3.getKey(),2);
    }

    @Test
    void getLocation() {
        assertEquals(n1.getLocation(),g1);
        assertEquals(n2.getLocation(),g2);
        assertEquals(n3.getLocation(),g3);
    }

    @Test
    void setLocation() {
        GeoLoc g4 = new GeoLoc(1,2,3);
        n1.setLocation(g4);
        assertEquals(n1.getLocation(),g4);
    }

    @Test
    void getWeight() {
        n1.setWeight(1.26);
        assertEquals(n1.getWeight(),1.26);
    }

    @Test
    void getInfo() {
        n2.setInfo("new Info");
        n3.setInfo("Galway Girl is currently playing");
        assertEquals(n1.getInfo(),"unmarked node");
        assertEquals(n2.getInfo(),"new Info");
        assertEquals(n3.getInfo(),"Galway Girl is currently playing");
    }

    @Test
    void getTag() {
        n2.setTag(1);
        n3.setTag(111);
        assertEquals(n1.getTag(),0);
        n1.setTag(10);
        assertEquals(n1.getTag(),10);
        assertEquals(n2.getTag(),1);
        assertEquals(n3.getTag(),111);
    }
    @Test
    void edge(){
        EdgeData e1 = new EdgeData(0,0,0,0);
        EdgeData e2 = new EdgeData(0,9,15,1);
        EdgeData e3 = new EdgeData(0,9,1.51,2);

        // Check of the same edge
        n1.addEdge(e1);

        try{
            n1.addEdge(e2);
        }
        catch (RuntimeException r){
            System.out.println("works");
        }

        n1.addEdge(e3);
        assertEquals(((EdgeData)n1.getEdge(9)).getId(),2);
        assertEquals(e3,n1.getEdge(9));
        n1.removeEdge(0);
        assertEquals(n1.getEdgeCollection().size(),1);
        assertEquals(e3,n1.getEdge(9));

        EdgeData e4 = new EdgeData(1,9,15,1);
        EdgeData e5 = new EdgeData(1,9,1.51,2);
        n2.addEdge(e4);
        n2.addEdge(e5);
        assertEquals(n2.getEdgeCollection().size(),1);
        assertEquals(n2.getEdge(9).getWeight(),1.51);
    }
    @Test
    void pointers(){
        n1.addPointer(2);
        n1.addPointer(2);
        assertEquals(n1.getPointers().size(),1);
        assertEquals(n1.getPointers().get(0),2);

        n2.addPointer(15);
        n2.removePointer(15);
        assertEquals(n2.getPointers().size(),0);
    }
}