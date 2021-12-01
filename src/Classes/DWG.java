package Classes;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.HashMap;
import java.util.Iterator;

// This class represents The Directed Weighted Graph
public class DWG implements api.DirectedWeightedGraph {
    int size;
    HashMap<Integer,Node> nodes;

    // create new Directed Weighted Graph
    // idea- create an Hashmap for the nodes, each node has an id
    // so we can get node with O(1)
    public DWG(){
        size = 0;
        nodes = new HashMap<Integer,Node>();
    }
    @Override
    public NodeData getNode(int key) {
        if(nodes.containsKey(key)){
            return nodes.get(key);
        }
        else{
            // there is no such node
            return null;
        }
    }

    @Override
    public EdgeData getEdge(int src, int dest) {

        return null;
    }

    @Override
    public void addNode(NodeData n) {

    }

    @Override
    public void connect(int src, int dest, double w) {

    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        return null;
    }

    @Override
    public int nodeSize() {
        return 0;
    }

    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public int getMC() {
        return 0;
    }
}
