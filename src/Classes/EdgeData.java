package Classes;

/**
 * This class represents a single edge between two nodes in the graph
 */
public class EdgeData implements api.EdgeData {

    //auxiliary
    private int id;

    private int src;
    private int dest;
    private double w;
    private String info;
    private int tag = 0; //default 0

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


}
