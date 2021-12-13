import Classes.EdgeData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EdgeDataTest {

    EdgeData e1 = new EdgeData(0,0,0,0);
    EdgeData e2 = new EdgeData(5,10,15,1);
    EdgeData e3 = new EdgeData(3,9,1.51,2);

    @Test
    void getId() {
        assertEquals(e1.getId(),0);
        assertEquals(e2.getId(),1);
        assertEquals(e3.getId(),2);
    }

    @Test
    void getSrc() {
        assertEquals(e1.getSrc(),0);
        assertEquals(e2.getSrc(),5);
        assertEquals(e3.getSrc(),3);
    }

    @Test
    void getDest() {
        assertEquals(e1.getDest(),0);
        assertEquals(e2.getDest(),10);
        assertEquals(e3.getDest(),9);
    }


    @Test
    void getWeight() {
        assertEquals(e1.getWeight(),0);
        assertEquals(e2.getWeight(),15);
        assertEquals(e3.getWeight(),1.51);
        e1.setWeight(1.1515);
        assertEquals(e1.getWeight(),1.1515);
    }

    @Test
    void getInfo() {
        e2.setInfo("new Info");
        e3.setInfo("Galway Girl is currently playing");
        assertEquals(e1.getInfo(),"unmarked edge");
        assertEquals(e2.getInfo(),"new Info");
        assertEquals(e3.getInfo(),"Galway Girl is currently playing");
    }


    @Test
    void getTag() {
        e2.setTag(1);
        e3.setTag(111);
        assertEquals(e1.getTag(),0);
        e1.setTag(10);
        assertEquals(e1.getTag(),10);
        assertEquals(e2.getTag(),1);
        assertEquals(e3.getTag(),111);
    }


}