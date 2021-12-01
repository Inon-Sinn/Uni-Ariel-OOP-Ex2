package Classes;

public class Tuple {
    private int src, dest;
    public Tuple(int src, int dest){
        this.src = src;
        this.dest = dest;
    }

    public int getDest() {
        return dest;
    }

    public int getSrc() {
        return src;
    }
}
