package Classes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class represents The Directed Weighted Graph
 */
public class DWG implements api.DirectedWeightedGraph {

    private int mc=0;
    private int nextKeyEdge;
    @SerializedName("Nodes")
    private final HashMap<Integer, api.NodeData> nodes;
    @SerializedName("Edges")
    private final HashMap<Integer, api.EdgeData> edges;

    /**
     * create new Directed Weighted Graph
     */
    public DWG(){
        this.nextKeyEdge=0;
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
    }

    /**
     * Returns the node by the key,
     * won't return the exact node given to the graph in addNode but a Node with the same values
     * @param key - the node_id
     * @return NodeDate - if the key exists, null else
     */
    @Override
    public api.NodeData getNode(int key) {
        return nodes.getOrDefault(key, null);
    }

    /**
     * Return the edge given the source and destination, null if the edge doesn't exist
     * @param src int - the source of the edge
     * @param dest int - the destination of the edge
     * @return EdgeData - if the edge exists, null else
     */
    @Override
    public api.EdgeData getEdge(int src, int dest) {//O(1)
        //check if the node exists
        NodeData source = (NodeData) getNode(src);
        if (source==null)//O(1)
            return null;
        //Check if the edge exists
        if (source.getEdge(dest) == null)//O(1)
            return null;
        return this.edges.get(getEdgeId(src, dest));//O(1)
    }

    /**
     * Adds a Node to the graph,
     * the function won't enter the exact node given but a copy of it, then so that in case we get a different kind of node
     * that implements the interface NodeData we won't have any problems
     * In the case the node already exists we won't add it
     * @param n api.NodeData - the node we add to the graph
     */
    @Override
    public void addNode(api.NodeData n) {
        if(!nodes.containsKey(n.getKey())) {
            NodeData newNode = new NodeData(n.getKey(), ((GeoLoc) n.getLocation()));
            this.nodes.put(newNode.getKey(), newNode);
            this.mc++;
        }
    }

    public void connect(EdgeData edgeData){
        connect(edgeData.getSrc(),edgeData.getDest(),edgeData.getWeight());
    }

    /**
     * Adds an edge to the graph given the source, destination and weight
     * @param src - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w - positive weight representing the cost (aka time, price, etc.) between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {//TODO check what are invalid src,dest and w
        if(src==dest)
            throw new RuntimeException("cant create a edge from a node to itself");
        // check if both nodes are inside the graph, if at least one is not inside the graph then do nothing
        if (this.nodes.containsKey(src) && this.nodes.containsKey(dest) && w>=0){
            EdgeData edgeData = new EdgeData(src,dest,w,this.nextKeyEdge++);
            this.edges.put(edgeData.getId(),edgeData);
            NodeData source = (NodeData)getNode(src);
            source.addEdge(edgeData);
            NodeData destination = (NodeData)getNode(dest);
            destination.addPointer(src);
            this.mc++;
        }
        else
            throw new RuntimeException("Invalid parameters");
    }

    /**
     * Returns a new iterator for all nodes
     * @return Iterator<api.NodeData>
     */
    @Override
    public Iterator<api.NodeData> nodeIter() {

        return new Iterator<>() {

            NodeData currNode;
            private int currMc = mc;
            private final ArrayList<api.NodeData> valuesList= new ArrayList<>(nodes.values());
            private final Iterator<api.NodeData> normal_it = valuesList.iterator();

            @Override
            public boolean hasNext() {
                if(currMc !=mc)
                    throw new RuntimeException("There was a Change in the graph");
                return normal_it.hasNext();
            }
            @Override
            public api.NodeData next() {
                if(currMc !=mc)
                    throw new RuntimeException("There was a Change in the graph");
                currNode = (NodeData) normal_it.next();
                return currNode;
            }
            @Override
            public void remove() {
                if(currMc !=mc)
                    throw new RuntimeException("There was a Change in the graph");
                removeNode(currNode.getKey());
                normal_it.remove();
                currMc = mc;
            }
        };
    }

    /**
     * Returns a new iterator for all edges
     * @return Iterator<api.EdgeData>
     */
    @Override
    public Iterator<api.EdgeData> edgeIter() {

        return new Iterator<>() {

            EdgeData currEdge;
            private int currMc = mc;
            private final ArrayList<api.EdgeData> valuesList= new ArrayList<>(edges.values());
            private final Iterator<api.EdgeData> normal_it = valuesList.iterator();

            @Override
            public boolean hasNext() {
                if(currMc !=mc)
                    throw new RuntimeException("There was a Change in the graph");
                return normal_it.hasNext();
            }
            @Override
            public api.EdgeData next() {
                if(currMc !=mc)
                    throw new RuntimeException("There was a Change in the graph");
                currEdge = (EdgeData) normal_it.next();
                return currEdge;
            }
            @Override
            public void remove() {
                if(currMc !=mc)
                    throw new RuntimeException("There was a Change in the graph");
                removeEdge(currEdge.getSrc(),currEdge.getDest());
                normal_it.remove();
                currMc = mc;
            }
        };
    }

    /**
     * Returns a new iterator for all edges of a given node
     * @return Iterator<api.EdgeData>
     */
    @Override
    public Iterator<api.EdgeData> edgeIter(int node_id) {

        return new Iterator<>() {

            EdgeData currEdge;
            private int currMc = mc;
            private final ArrayList<api.EdgeData> valuesList= new ArrayList<>(((NodeData) nodes.get(node_id)).getEdgeCollection());
            private final Iterator<api.EdgeData> normal_it = valuesList.iterator();

            @Override
            public boolean hasNext() {
                if(currMc !=mc)
                    throw new RuntimeException("There was a Change in the graph");
                return normal_it.hasNext();
            }

            @Override
            public api.EdgeData next() {
                if(currMc !=mc)
                    throw new RuntimeException("There was a Change in the graph");
                currEdge = (EdgeData) normal_it.next();
                return currEdge;
            }
            @Override
            public void remove() {
                if(currMc !=mc)
                    throw new RuntimeException("There was a Change in the graph");
                removeEdge(currEdge.getSrc(),currEdge.getDest());
                normal_it.remove();
                currMc = mc;
            }
        };
    }

    /**
     * Removes a node from the graph by the key
     * @param key int - the id of the node
     * @return api.NodeData - the removed node
     */
    @Override
    public api.NodeData removeNode(int key) {
        //remove the node from all nodes
        NodeData removed = (NodeData) nodes.get(key);
        if(removed==null)
            return null;
        Iterator<api.EdgeData> it = edgeIter(key);
        // remove the edges going out from this node
        while(it.hasNext()){
            it.next();
            it.remove();
        }
        // remove the edges going to this node
        for (int i = removed.getPointers().size()-1; i >=0 ; i--)
            removeEdge(removed.getPointers().get(i),key);
        //return the node
        nodes.remove(key);
        this.mc++;
        return removed;
    }

    /**
     * Removes an edge from the graph
     * @param src int - the id of the source of the edge
     * @param dest int - the id if the destination of the edge
     * @return api.EdgeData - the removed edge
     */
    @Override
    public api.EdgeData removeEdge(int src, int dest) { // O(1)
        //Remove from all edges
        EdgeData edgeData = (EdgeData) edges.remove(getEdgeId(src,dest));
        //Remove from the node its in (src)
        NodeData  srcNode= (NodeData)nodes.get(src);
        srcNode.removeEdge(dest);
        //Remove the pointer (dest)
        NodeData  destNode= (NodeData)nodes.get(dest);
        destNode.removePointer(src);
        //return the removed edge
        this.mc++;
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
        NodeData  currentNode= (NodeData)getNode(src);
        if(currentNode == null)
            throw new RuntimeException("The Node doesn't exist");
        EdgeData currentEdge= (EdgeData)currentNode.getEdge(dest);
        if(currentEdge == null)
            throw new RuntimeException("The edge doesn't exist");
        return currentEdge.getId();

        //return the same but without building new objects and because of that is faster and saves place but is messier
        //return ((EdgeData)((NodeData)nodes.get(src)).getEdge(dest)).getId();

    }
}
