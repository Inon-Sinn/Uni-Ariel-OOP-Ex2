package Classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * This class represents a single Node in the graph, the parameters that describe the node are:
 * id - int - the Id of the node, the key used in the hashmap of all nodes
 * pos - 
 */
public class NodeData implements api.NodeData {

    private final int id;
    private final GeoLoc pos;

    private HashMap<Integer, api.EdgeData> edgesConnected;
    // pointers, are the id's of the nodes having a edge leading to this node
    private ArrayList<Integer> pointing_to_me = new ArrayList<>(); //todo check it doesnt make problems

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
        this.pos.setX(p.x());
        this.pos.setY(p.y());
        this.pos.setZ(p.z());
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

    //Possible to return the edge
    public void removeEdge(int dest){
        edgesConnected.remove(dest);
    }

    public Collection<api.EdgeData> getEdgeCollection(){
        return this.edgesConnected.values();
    }

    /**
     * Adds an edge to the hashmaps of all edges
     * @param edgeData api.EdgeData - the edge to add
     */
    public void addEdge(api.EdgeData edgeData){
        EdgeData e = (EdgeData) edgeData;
        this.edgesConnected.put(e.getDest(),e);
    }

    /**
     * Return an Array with all nodes for which have a edge with this node as the destination
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
        this.pointing_to_me.add(src);
    }

    public void removePoiner(int src){
        this.pointing_to_me.remove((Integer)src);//TODO check it removes the object and not the placement
    }

}