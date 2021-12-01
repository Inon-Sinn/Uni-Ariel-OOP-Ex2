package Classes;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.HashMap;
import java.util.Iterator;

// This class represents The Directed Weighted Graph
public class DWG implements api.DirectedWeightedGraph {
    int size,nextKey;
    Nodes nodes;
    Edges edges;
    // create new Directed Weighted Graph
    // create hashmap with key source, and value of another hashmap.
    // each key represents a single node and the hashmap for this node represents the nodes connected to him

    public DWG(){
        size = 0;nextKey = 0;
        this.nodes = new Nodes();
        this.edges = new Edges();

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
        return this.edges.get(new Tuple(src,dest));
    }
    @Override
    public void addNode(NodeData n) {
        Node node = new Node();
        node.setInfo(n.getInfo());
        node.setLocation(n.getLocation());
        node.setTag(n.getTag());
        node.setWeight(n.getWeight());
        node.setKey(this.nextKey++);
        Integer k = Integer.valueOf(this.nextKey);
        this.nodes.put(k,node);
    }

    @Override
    public void connect(int src, int dest, double w) {
        // check if both nodes are inside the graph.

        // if at least one is not inside the graph then do nothing
        if ( !(this.nodes.containsKey(Integer.valueOf(src)) &&
                this.nodes.containsKey(Integer.valueOf(dest)))){
            return;
        }
        // else connect them together
        else {
            Tuple tuple = new Tuple(src, dest);
            Edge edge = new Edge();
            edge.setEdge(tuple);
            // edit info and tag, unclear use
            edge.setInfo("xd");
            edge.setTag(-1);

            edge.setWeight(w);
            this.edges.put(tuple, edge);
        }
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
