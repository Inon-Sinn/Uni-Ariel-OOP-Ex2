package Classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * This class represents a single Node in the graph, the parameters that describe the node are:
 * id - int - the id of the node, the key used in the hashmap of all nodes
 * pos - GeoLoc - the placement of the node
 * edgesConnected - HashMap<Integer, api.EdgeData> - a hash map holding all edges going out from this node
 * pointing_to_me - ArrayList<Integer> - all ids of the nodes having an edge pointing to this node
 * info - String - The Meta-data describing the edge
 * weight - double - weight of the node
 * tag - int - Temporal data (aka color: e,g, white, gray, black)  represented as a number
 */
public class NodeData implements api.NodeData {

    private final int id;
    private GeoLoc pos;

    private HashMap<Integer, api.EdgeData> edgesConnected;
    private ArrayList<Integer> pointing_to_me = new ArrayList<>(); //todo check it doesn't make problems

    private String info;
    private double weight = 0; //default 0
    private int tag = 0; //default 0


    public NodeData(int id, GeoLoc pos){
        this.id = id;
        this.pos = pos;
        this.edgesConnected = new HashMap<>();
        this.info = "unmarked node";
    }

    @Override
    public int getKey() {
        return this.id;
    }

    @Override
    public api.GeoLocation getLocation() {
        return this.pos;
    }

    @Override
    public void setLocation(api.GeoLocation p) {
        this.pos = (GeoLoc)p;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = String.copyValueOf(s.toCharArray());
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    /**
     * Return the edge given its destination
     */
    public api.EdgeData getEdge(int dest){
        return this.edgesConnected.get(dest);
    }

    /**
     * removes the edge with the with destination "dest"
     * @param dest - int - id of the destination of the edge we want to remove
     */
    public void removeEdge(int dest){
        edgesConnected.remove(dest);
    }

    public Collection<api.EdgeData> getEdgeCollection(){
        return this.edgesConnected.values();
    }

    /**
     * Adds an edge to the hashmaps of all edges of this node
     * @param edgeData api.EdgeData - the edge to add
     */
    public void addEdge(api.EdgeData edgeData) throws RuntimeException{
        EdgeData e = (EdgeData) edgeData;
        // throws a Run Time exception if the given edges source isn't the current node
        if(e.getSrc()!=this.id)
            throw new RuntimeException("trying to add a edge to the wrong node");
        this.edgesConnected.put(e.getDest(),e);
    }

    /**
     * Returns an Array with the id of all nodes which have an edge going to this node
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getPointers(){
        return this.pointing_to_me;
    }

    /**
     * When a node gets an edge which points to this node it will add it to the list of nodes which point to this node
     * @param src int - the id  of the node which get an edge to this node
     */
    public void addPointer(int src){
        if(!pointing_to_me.contains(src))
            this.pointing_to_me.add(src);
    }

    public void removePointer(int src) {
        this.pointing_to_me.remove((Integer) src);//TODO check it removes the object and not the placement
    }

}