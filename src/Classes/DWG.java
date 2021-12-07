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
        this.mc++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        // check if both nodes are inside the graph, if at least one is not inside the graph then do nothing
        if (this.nodes.containsKey(src) && this.nodes.containsKey(dest)){
            EdgeData edgeData = new EdgeData(src,dest,w,this.nextKeyEdge++);
            this.edges.put(edgeData.getId(),edgeData);
            NodeData source = (NodeData)getNode(src);
            source.addEdge(edgeData);
            NodeData destination = (NodeData)getNode(src);
            destination.addPointer(src);
            this.mc++;
        }
    }

    @Override
    public Iterator<api.NodeData> nodeIter() {

        return new Iterator<>() {

            NodeData currNode;
            private int currMc = mc;
            private final Iterator<api.NodeData> normal_it = nodes.values().iterator();

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
                currMc = mc;
            }
        };
    }

    @Override
    public Iterator<api.EdgeData> edgeIter() {

        return new Iterator<>() {

            EdgeData currEdge;
            private int currMc = mc;
            private final Iterator<api.EdgeData> normal_it = edges.values().iterator();

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
                currMc = mc;
            }
        };
    }

    @Override
    public Iterator<api.EdgeData> edgeIter(int node_id) {

        return new Iterator<>() {

            EdgeData currEdge;
            private int currMc = mc;
            private final Iterator<api.EdgeData> normal_it = ((NodeData) nodes.get(node_id)).getEdgeCollection().iterator();

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
                currMc = mc;
            }
        };
    }

    @Override
    public api.NodeData removeNode(int key) {
        //remove the node from all nodes
        NodeData removed = (NodeData) nodes.get(key);
        Iterator<api.EdgeData> it = edgeIter(key);
        nodes.remove(key);
        // remove the edges going out from this node
        while(it.hasNext()){
            it.next();
            it.remove();
        }
        // remove the edges going to this node
        for (int i = removed.getPointers().size()-1; i >=0 ; i--)
            removeEdge(removed.getPointers().get(i),key);
        //return the node
        this.mc++;
        return removed;
    }

    @Override
    public api.EdgeData removeEdge(int src, int dest) { // O(1)
        //Remove from all edges
        EdgeData edgeData = (EdgeData) edges.remove(getEdgeId(src,dest));
        //Remove from the node its in (src)
        NodeData  srcNode= (NodeData)nodes.get(src);
        srcNode.removeEdge(dest);
        //Remove the pointer (dest)
        NodeData  destNode= (NodeData)nodes.get(dest);
        destNode.removePoiner(src);
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
        NodeData  currentNode= (NodeData)nodes.get(src);
        EdgeData currentEdge= (EdgeData)currentNode.getEdge(dest);
        return currentEdge.getId();

        //return the same but without building new objects and because of that is faster and saves place but is messier
        //return ((EdgeData)((NodeData)nodes.get(src)).getEdge(dest)).getId();

    }
}
