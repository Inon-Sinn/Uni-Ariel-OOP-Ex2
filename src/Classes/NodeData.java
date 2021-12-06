package Classes;

import java.util.Collection;
import java.util.HashMap;

// this class represents a single Node in the graph
public class NodeData implements api.NodeData {

    private int id;
    private double weight = 0; //default 0
    private GeoLoc pos;
    private HashMap<Integer, api.EdgeData> edgesConnected;
    private String info;
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

    public Collection<api.EdgeData> getConnectedEdgeCollection(){
        return this.edgesConnected.values();
    }

    //Is it needed?
    public void setKey(int key){
        this.id = key;
    }

    public void addEdge(api.EdgeData edgeData){
        EdgeData e = (EdgeData) edgeData;
        this.edgesConnected.put(e.getDest(),e);
    }

}
