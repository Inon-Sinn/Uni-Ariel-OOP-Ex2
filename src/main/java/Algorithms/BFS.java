package Algorithms;

import Classes.DWG;
import api.NodeData;

import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * The class represents the BFS algorithm
 * graph - DWG - the graph on which we execute the BFS algorithm
 * Q - PriorityQueue<Integer> - a Queue used by the algorithm
 * d - HashMap<Integer,Integer> - a hashmap that saves the distance to the start node
 * prev - HashMap<Integer,Integer> - a hashmap that saves the predecessor of a node in the BFS algorithm
 * white - int - a final int representing the color white
 * gray - int - a final int representing the color gray
 * black - int - a final int representing the color black
 */
public class BFS {

    private DWG graph;
    PriorityQueue<Integer> Q;
    private HashMap<Integer,Integer> d ;
    private HashMap<Integer,Integer> prev;
    private static final int white = 0;
    private static final int gray = 1;
    private static final int black = 2;

    /**
     * Runs the BFS algorithm as learned in class
     * @param g DWG - the graph on whichwe run the BFS algorith
     * @param s int - the id of the node from which we start
     */
    public BFS(DWG g, int s){
        this.Q = new PriorityQueue<>();
        this.d = new HashMap<>();
        this.prev = new HashMap<>();
        this.graph = g;
        Iterator<NodeData> node_it = graph.nodeIter();
        while(node_it.hasNext()) {
            NodeData node = node_it.next();
            node.setTag(white);
            prev.put(node.getKey(), null);
            d.put(node.getKey(), Integer.MAX_VALUE);
        }
        graph.getNode(s).setTag(gray);
        d.put(s,0);
        Q.add(s);
        while(!Q.isEmpty()) {
            BFS_Visit(Q.poll());

        }

    }

    /**
     * Used by the BFS algorithm
     * goes over all the sibling of a node and adds them to the Queue if they have the color white
     * @param currentVisitNode int - the id of the node for which we used this function
     */
    // first enqueue all the neighbors of currentVisitNode
    private void BFS_Visit(int currentVisitNode){
        Classes.NodeData nodeData = (Classes.NodeData) graph.getNode(currentVisitNode);
        // iterating through its edges and adding them to the Q
        for (api.EdgeData edgeData : nodeData.getEdgeCollection()) {
            int dest = edgeData.getDest();
            // for each neighbor of currentVisitNode
            // set its distance to currentVisitNode distance and increment by one and put
            // prev to be currentVisitNode
            if (graph.getNode(dest).getTag() == white) {
                graph.getNode(dest).setTag(gray);
                d.put(dest, d.get((currentVisitNode) + 1));
                prev.put(dest, currentVisitNode);
                Q.add(dest);
            }
        }
        nodeData.setTag(black);
    }

    /**
     * An Auxiliary function used to check if the graph is connected
     * @return boolean -True: the graph is connected, False: the graph isn't connected
     */
    public boolean connected(){
        Iterator<NodeData> node_it = graph.nodeIter();
        while(node_it.hasNext()) {
            if(node_it.next().getTag()==white)
                return false;
        }
        return true;
    }



}
