package Classes;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents a single edge between two nodes in the graph
 * src - int - source node id
 * dest - int - destination node id
 * w - double - weight of the edge
 * info - String - The Meta data describing the edge
 * tag - int - Temporal data (aka color: e,g, white, gray, black)  represented as a number
 * id - int - the id of the edge, aka the key used in the hashmap of all edges
 */
public class EdgeData implements api.EdgeData {

    @SerializedName("src")
    private final int src;
    @SerializedName("dest")
    private final int dest;
    @SerializedName("w")
    private double w;
    private String info;
    private int tag = 0; //default 0
    //auxiliary
    private final int id;

    /**
     * The Builder of the edge class gets src,dest, weight and id
     * @param src int - source node id
     * @param dest int - destination node id
     * @param w double - weight of the edge
     * @param id int - the id of the edge aka the key used in the hashmap of all edges
     */
    public EdgeData(int src, int dest, double w,int id){
        this.id = id;
        this.src = src;
        this.dest = dest;
        this.w = w;
        this.info = "unmarked edge";
    }

    public  int getId(){
        return this.id;
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        // TODO check
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

    public void setWeight(double w){
        this.w = w;
    }

    @Override
    public String toString() {
        return "EdgeData{" +
                "src=" + src +
                ", dest=" + dest +
                ", w=" + w +
                ", info='" + info + '\'' +
                ", tag=" + tag +
                ", id=" + id +
                '}';
    }
}
