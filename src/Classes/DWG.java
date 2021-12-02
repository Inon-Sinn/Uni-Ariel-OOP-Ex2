package Classes;

import api.EdgeData;
import api.NodeData;

import java.util.ArrayList;
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
    public Iterator<NodeData> nodeIter() { return this.nodes.itr; }

    @Override
    public Iterator<EdgeData> edgeIter() { return this.edges.itr; }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        ArrayList <EdgeData> a = new ArrayList<EdgeData>();
        for (int i = 0; i < this.edges.size(); i++) {
            if(edges.get(i).getDest() == node_id|| edges.get(i).getSrc() == node_id){
                a.add(edges.get(i));
            }
        }
        return a.iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        Node removed = nodes.get(key);
        nodes.remove(key);
        for (int i = 0; i < edges.size(); i++) {
            if(edges.get(i).getDest() == key || edges.get(i).getSrc() == key){
                edges.remove(i);
            }
        }
        return removed;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) { //O(number of edges) - need to fix
        for (int i = 0; i < edges.size(); i++) {
            if(edges.get(i).getSrc() == src && edges.get(i).getDest() == dest){
                Edge removed = edges.get(i);
                edges.remove(i);
                return removed;
            }
        }
        return null;
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() { return edges.size(); }

    @Override
    public int getMC() {
        return 0;
    }
}
