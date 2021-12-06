package Classes;

import java.util.HashMap;
import java.util.Iterator;

/**
 * This class represents The Directed Weighted Graph
 */
public class DWG implements api.DirectedWeightedGraph {

    int mc=0;
    int nextKeyNode;
    int nextKeyEdge;
    HashMap<Integer, api.NodeData> nodes;
    HashMap<Integer, api.EdgeData> edges;

    /**
     * create new Directed Weighted Graph
     */
    public DWG(){
        this.nextKeyEdge=0;
        this.nextKeyNode=0;
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
    }

    /**
     * Returns the node by the key
     * @param key - the node_id
     * @return NodeDate - if the key exists, null - else
     */
    @Override
    public api.NodeData getNode(int key) {
        return nodes.getOrDefault(key, null);
    }

    @Override
    public api.EdgeData getEdge(int src, int dest) {
        return this.edges.get(getEdgeId(src, dest));
        //return ((NodeData)nodes.get(src)).getEdge(dest); saves a little more time but is messier
    }
    @Override
    public void addNode(api.NodeData n) {
        Integer k = this.nextKeyNode++; // TODO check potential bug cuz no cast from interface used
        this.nodes.put(k,n);
    }

    @Override
    public void connect(int src, int dest, double w) {
        // check if both nodes are inside the graph.

        // if at least one is not inside the graph then do nothing
        if ( !(this.nodes.containsKey(src) && this.nodes.containsKey(dest))){
            return;
        }
        // else connect them together
        else {
            EdgeData edgeData = new EdgeData(src,dest,w,this.nextKeyEdge++);
            edgeData.setInfo("xd");
            edgeData.setTag(-1);
            this.edges.put(edgeData.getId(),edgeData);
            // add the edge to the source hashmap
            NodeData source = (NodeData)this.nodes.get(src);
            source.addEdge(edgeData);

        }
    }

    @Override
    public Iterator<api.NodeData> nodeIter()
    {
        return this.nodes.values().iterator();
    }

    @Override
    public Iterator<api.EdgeData> edgeIter()
    {
        return this.edges.values().iterator();
    }

    @Override
    public Iterator<api.EdgeData> edgeIter(int node_id) {
       NodeData l = null;
       l = (NodeData) nodes.get(node_id);
       return l.getConnectedEdgeCollection().iterator();
    }

    @Override
    public api.NodeData removeNode(int key) {
        NodeData removed = (NodeData) nodes.get(key);
        nodes.remove(key);
        for (int i = 0; i < edges.size(); i++) {
            if(edges.get(i).getDest() == key || edges.get(i).getSrc() == key){
                edges.remove(i);
            }
        }
        return removed;
    }

    @Override
    public api.EdgeData removeEdge(int src, int dest) { // O(1)
        int id = getEdgeId(src,dest);
        EdgeData edgeData = (EdgeData) edges.remove(id);
        return edgeData;
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() { return edges.size(); }

    @Override
    public int getMC() {
        return this.mc;
    }

    /**
     * Gets the src and dest of an edge and return its key in the edges Hashmap
     * @param src int - src of the edge, its starting point
     * @param dest int - dest of the edge its end point;
     * @return int - id of the edge in the edges hashmap
     */
    public int getEdgeId(int src, int dest){
        NodeData  currentNode= (NodeData)nodes.get(src);
        EdgeData currentEdge= (EdgeData)currentNode.getEdge(dest);
        return currentEdge.getId();

        //return the same but without building new objects and because of that is faster and saves place but is messier
        //return ((EdgeData)((NodeData)nodes.get(src)).getEdge(dest)).getId();

    }
}
