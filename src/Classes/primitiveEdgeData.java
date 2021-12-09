package Classes;

public class primitiveEdgeData {
    private int src;
    private double w;
    private int dest;

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public primitiveEdgeData(int src, double w, int dest) {
        this.src = src;
        this.w = w;
        this.dest = dest;
    }
}
