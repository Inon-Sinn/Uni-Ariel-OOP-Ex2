package Algorithms;

import Classes.DWG;
import Classes.EdgeData;
import Classes.NodeData;
import org.w3c.dom.Node;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * This class Represent the BFS algorithm
 * Q - MinHeap - a minheap we use to to make thea algorithm faster
 * graph - DWG - the graph on which the Dijkstra runs on
 * prev -  HashMap<Integer,Integer> - a hashmap that recors for each node the node before it in the Dijkstra algorithm, used to get the shortest path
 */
public class Dijkstra {

    private MinHeap Q = new MinHeap();
    public DWG graph;
    public HashMap<Integer,Integer> prev = new HashMap<>();

    /**
     * Dijkstra algorithm as learned in class, O(|E|log|V|)*
     * E - the number of edges
     * V - the number of vertexes
     * @param g DWG - the graph on which the Dijkstra runs on
     * @param start int - the node from which we start
     */
    public Dijkstra(DWG g, int start) {
        this.graph = g;
        // making a start node and setting
        NodeData sNode = (NodeData) graph.getNode(start);
        // iterating through all the nodes and setting their weights to infinity
        Iterator<api.NodeData> node_it = g.nodeIter();
        while (node_it.hasNext()) {
            int currkey = node_it.next().getKey();
            graph.getNode(currkey).setWeight(Double.POSITIVE_INFINITY);
            NodeData node = (NodeData) graph.getNode(currkey);
            Q.insert(node);
            prev.put(currkey, null);
        }
        // seting the start node to weight 0
        Q.DecreaseKey(sNode,0);

        // iterating through the elements in the queue
        while (!Q.isEmpty()) {
            // deleting the current working node
            int u = Q.deleteMin().getKey();
            // iterating through the current node's edges
            Iterator<api.EdgeData> edges = graph.edgeIter(u);
            while (edges.hasNext()) {
                EdgeData e = (EdgeData) edges.next();
                // relaxing its edges
                relax(e.getSrc(), e.getDest(), e.getWeight());
            }
        }
    }

    /**
     * Relax, used by the Dijkstra algorithm
     * @param src int - the id of the source node
     * @param dest int - the id of the destination n
     * @param w double - the weight of theedge betweent them
     */
    public void relax(int src,int dest,double w){
        double newWeight = (graph.getNode(src).getWeight() + w);
        if(graph.getNode(dest).getWeight() > newWeight){
            Q.DecreaseKey(dest,newWeight);
//            graph.getNode(dest).setWeight(graph.getNode(src).getWeight() + w);
            prev.put(dest,src);
//            Q.remove((NodeData) graph.getNode(dest));
//            Q.insert((NodeData) graph.getNode(dest));
        }
    }


    public double shortestPathDis(int dest){
        if(graph.getNode(dest).getWeight()==Double.POSITIVE_INFINITY)
            return -1;
        return graph.getNode(dest).getWeight();
    }

    /**
     * An auxiliray function to get the shortestPath between two given nodes
     * using the prev Hashmap
     * @param src int - the id of the source node
     * @param dest int - the id of the destination node
     * @return ArrayList<api.NodeData> - the shortest path
     */
    public ArrayList<api.NodeData> shortestPath(int src,int dest){
        if(shortestPathDis(dest)==-1)
            return null;
        ArrayList<api.NodeData> path = new ArrayList<>();
        int current = dest;
        while(current!=src){
            path.add(0,graph.getNode(current));
            current = prev.get(current);
        }
        path.add(0,graph.getNode(src));
        return path;
    }

    /**
     * An auxiliary function to get the maximum distanceto a node in the graph
     * @return double - the maximum diastance
     */
    public double maxWeight(){
        double w= 0;
        Iterator<api.NodeData> it = graph.nodeIter();
        while(it.hasNext()){
            double cur = it.next().getWeight();
            if (cur == Double.POSITIVE_INFINITY)
                return -1;
            if(cur > w)
                w = cur;
        }
        return w;
    }
}
