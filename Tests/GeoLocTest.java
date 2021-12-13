import Classes.GeoLoc;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoLocTest {
    GeoLoc g1 = new GeoLoc(0,0,0);
    GeoLoc g2 = new GeoLoc(7,4,3);
    GeoLoc g3 = new GeoLoc(17,6,2);

    @Test
    void x() {
        assertEquals(g1.x(),0);
        assertEquals(g2.x(),7);
        assertEquals(g3.x(),17);
    }

    @Test
    void y() {
        assertEquals(g1.y(),0);
        assertEquals(g2.y(),4);
        assertEquals(g3.y(),6);
    }

    @Test
    void z() {
        assertEquals(g1.z(),0);
        assertEquals(g2.z(),3);
        assertEquals(g3.z(),2);
    }

    @Test
    void distance() {
        assertEquals(g2.distance(g3),10.246950765959598);
    }

    @Override
    public String toString() {
        return "<" +
                g1 +
                "," + g2 +
                "," + g3 +
                '>';
    }
}