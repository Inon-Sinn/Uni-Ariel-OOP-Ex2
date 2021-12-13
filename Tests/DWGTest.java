import Classes.DWG;
import Classes.EdgeData;
import Classes.GeoLoc;
import Classes.NodeData;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class DWGTest {

    GeoLoc g1 = new GeoLoc(0,0,0);
    GeoLoc g2 = new GeoLoc(7,4,3);
    GeoLoc g3 = new GeoLoc(17,6,2);

    NodeData n1 = new NodeData(0,g1);
    NodeData n2 = new NodeData(1,g2);
    NodeData n3 = new NodeData(2,g3);

    DWG graph1 = new DWG();

    @Test
    void getNode() {
        assertNull(graph1.getNode(n1.getKey()));

        graph1.addNode(n1);
        graph1.addNode(n2);
        graph1.addNode(n3);

        assertEquals(graph1.getNode(n1.getKey()).getKey(), n1.getKey());
        assertEquals(graph1.getNode(n2.getKey()).getKey(), n2.getKey());
        assertEquals(graph1.getNode(n3.getKey()).getKey(), n3.getKey());
        assertNull(graph1.getNode(10));
    }

    @Test
    void getEdge() {
        assertNull( graph1.getEdge(0,1));
        graph1.addNode(n1);
        graph1.addNode(n2);
        assertNull(graph1.getEdge(0,1));
        graph1.connect(n1.getKey(),n2.getKey(),1.5);
        EdgeData e1 = new EdgeData(n1.getKey(),n2.getKey(),1.5,0);
        assertEquals(((EdgeData)graph1.getEdge(n1.getKey(),n2.getKey())).getId(),e1.getId());
    }

    @Test
    void addNode() { //TODO changed, doesnt throws excepetion anymore
        graph1.addNode(n1);
        graph1.addNode(n2);
        graph1.addNode(n3);

        graph1.addNode(n1);
        assertEquals(graph1.nodeSize(),3);
        graph1.addNode(new NodeData(5,g1));
        assertEquals(graph1.nodeSize(),4);
    }

    @Test
    void connect() {
//        NodeData n1 = new NodeData(0,g1);
//        NodeData n2 = new NodeData(1,g2);

        //Check for throwing exceptions if the src and dest are the same
        try {
            graph1.connect(1,1,0);
        }
        catch (RuntimeException e){
            System.out.println("works");
        }

        //check if at least one of the node doesn't exist

        try {
            graph1.connect(n1.getKey(),n2.getKey(),10);
        }
        catch (RuntimeException e){
            System.out.println("works2");
        }
        graph1.addNode(n1);
        try {
            graph1.connect(n1.getKey(),n2.getKey(),10);
        }
        catch (RuntimeException e){
            System.out.println("works3");
        }

        try {
            graph1.connect(n2.getKey(), n1.getKey(),10);
        }
        catch (RuntimeException e){
            System.out.println("works4");
        }

        //tests if the edge is getting added right
        graph1.addNode(n2);
        assertEquals(graph1.nodeSize(),2);

        graph1.connect(n1.getKey(),n2.getKey(),10);
        EdgeData eNew = new EdgeData(0,1,10,0);
        assertEquals(((EdgeData) graph1.getEdge(n1.getKey(),n2.getKey())).getId(),eNew.getId());
        EdgeData eNew2 = (EdgeData)((NodeData)graph1.getNode(n1.getKey())).getEdge(n2.getKey());
        assertEquals(eNew2.getId(),eNew.getId());
        assertTrue(((NodeData)graph1.getNode(n2.getKey())).getPointers().contains(n1.getKey()));
        //TODO finish it
    }

    @Test
    void nodeIter() {
        graph1.addNode(n1);
        graph1.addNode(n2);
        graph1.addNode(n3);
        Iterator<api.NodeData> it = graph1.nodeIter();
        it.next();
//        graph1.addNode(n3);
//        try{
//            it.next();
//        }
//        catch(RuntimeException e){
//            System.out.println("works");
//        }
        it.remove();
        assertEquals(graph1.nodeSize(),2);
        System.out.println(it.next().getKey());
    }

    @Test
    void edgeIter() {
        graph1.addNode(n1);
        graph1.addNode(n2);
        graph1.addNode(n3);
        graph1.connect(n1.getKey(), n2.getKey(),1);
        graph1.connect(n2.getKey(), n3.getKey(),5);
        graph1.connect(n1.getKey(), n3.getKey(),10);
        Iterator<api.EdgeData> it = graph1.edgeIter();
        it.next();
        graph1.addNode(n3);
        try{
            graph1.addNode(new NodeData(4,g1));
            it.next();
        }
        catch(RuntimeException e){
            System.out.println("works");
        }
//        it.remove();
//        assertEquals(graph1.edgeSize(),2);
//        System.out.println(it.next().getWeight());
    }

    @Test
    void testEdgeIter() {
        graph1.addNode(n1);
        graph1.addNode(n2);
        graph1.addNode(n3);
        graph1.connect(n1.getKey(), n2.getKey(),1);
        graph1.connect(n2.getKey(), n3.getKey(),5);
        graph1.connect(n1.getKey(), n3.getKey(),10);
        Iterator<api.EdgeData> it = graph1.edgeIter(n1.getKey());
        it.next();
        graph1.addNode(n3);
//        try{
//            graph1.addNode(new NodeData(4,g1));
//            it.next();
//        }
//        catch(RuntimeException e){
//            System.out.println("works");
//        }
        it.remove();
        assertEquals(graph1.edgeSize(),2);
        System.out.println(it.next().getWeight());
    }

    @Test
    void removeNode() {
        assertNull(graph1.removeNode(n1.getKey()));
        graph1.addNode(n1);
        graph1.addNode(n2);
        graph1.addNode(n3);
        assertEquals(graph1.removeNode(n1.getKey()).getKey(),n1.getKey());
        assertEquals(graph1.nodeSize(),2);
        assertNull(graph1.getNode(n1.getKey()));

        graph1.addNode(n1);
        graph1.connect(n1.getKey(),n2.getKey(),0);
        assertEquals(((NodeData)graph1.getNode(n2.getKey())).getPointers().size(),1);
        graph1.removeNode(n1.getKey());
        assertEquals(graph1.nodeSize(),2);
        assertNull(graph1.getEdge(n1.getKey(),n2.getKey()));
        assertEquals(((NodeData)graph1.getNode(n2.getKey())).getPointers().size(),0);

        graph1.addNode(n1);
        graph1.connect(n2.getKey(),n1.getKey(),0);
        assertEquals(((NodeData)graph1.getNode(n1.getKey())).getPointers().size(),1);
        graph1.removeNode(n1.getKey());
        assertNull(graph1.getEdge(n2.getKey(),n1.getKey()));
        assertEquals(((NodeData)graph1.getNode(n2.getKey())).getEdgeCollection().size(),0);

        graph1.addNode(n1);
        graph1.connect(n2.getKey(),n1.getKey(),0);
        graph1.connect(n1.getKey(),n2.getKey(),1);
        graph1.removeNode(n1.getKey());
        assertNull(graph1.getEdge(n2.getKey(),n1.getKey()));
        assertNull(graph1.getEdge(n1.getKey(),n2.getKey()));
        assertEquals(((NodeData)graph1.getNode(n2.getKey())).getEdgeCollection().size(),0);
        assertEquals(((NodeData)graph1.getNode(n2.getKey())).getPointers().size(),0);


    }

    @Test
    void removeEdge() {
        graph1.addNode(n1);
        graph1.addNode(n2);
        graph1.connect(n1.getKey(),n2.getKey(),1.5);
        assertEquals(graph1.edgeSize(),1);
        assertEquals(((NodeData)graph1.getNode(n1.getKey())).getEdgeCollection().size(),1);
        assertTrue(((NodeData)graph1.getNode(n2.getKey())).getPointers().contains(n1.getKey()));

        EdgeData e1 = new EdgeData(n1.getKey(),n2.getKey(),1.5,0);
        graph1.removeEdge(n1.getKey(), n2.getKey());
        assertEquals(graph1.nodeSize(),2);
        assertEquals(graph1.edgeSize(),0);
        assertEquals(((NodeData)graph1.getNode(n1.getKey())).getEdgeCollection().size(),0);
        assertFalse(((NodeData)graph1.getNode(n2.getKey())).getPointers().contains(n1.getKey()));
        assertEquals(((NodeData)graph1.getNode(n1.getKey())).getPointers().size(),0);
    }

    @Test
    void nodeSize() {
        graph1.addNode(n1);
        graph1.addNode(n2);
        graph1.addNode(n3);
        assertEquals(graph1.nodeSize(),3);
        graph1.removeNode(n1.getKey());
        assertEquals(graph1.nodeSize(),2);
    }

    @Test
    void edgeSize() {
        graph1.addNode(n1);
        graph1.addNode(n2);
        graph1.addNode(n3);
        graph1.connect(n1.getKey(),n2.getKey(),1);
        graph1.connect(n2.getKey(),n3.getKey(),1);
        graph1.connect(n1.getKey(),n3.getKey(),1);
        assertEquals(graph1.edgeSize(),3);
    }

    @Test
    void getMC() {
        assertEquals(graph1.getMC(),0);
        graph1.addNode(n1);
        graph1.addNode(n2);
        assertEquals(graph1.getMC(),2);
        graph1.connect(n1.getKey(),n2.getKey(),10);
        assertEquals(graph1.getMC(),3);
        graph1.removeEdge(n1.getKey(), n2.getKey());
        assertEquals(graph1.getMC(),4);
        graph1.connect(n1.getKey(),n2.getKey(),10);
        graph1.removeNode(n1.getKey());
        assertEquals(graph1.getMC(),7);
    }

    @Test
    void getEdgeId() {
        try {
            graph1.getEdgeId(0, 0);
        }
        catch (RuntimeException e){
            System.out.println("works");
        }


        graph1.addNode(n1);
        try {
            graph1.getEdgeId(n1.getKey(),1);
        }
        catch (RuntimeException e){
            System.out.println("works2");
        }
        try {
            graph1.getEdgeId(1,n1.getKey());
        }
        catch (RuntimeException e){
            System.out.println("works3");
        }


        graph1.addNode(n2);
        try {
            graph1.getEdgeId(n1.getKey(),n2.getKey());
        }
        catch (RuntimeException e){
            System.out.println("works3");
        }

        graph1.connect(n1.getKey(),n2.getKey(),10);
        assertEquals(graph1.getEdgeId(n1.getKey(),n2.getKey()),0);
    }
}