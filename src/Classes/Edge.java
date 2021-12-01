package Classes;

import api.EdgeData;

// This class represents a single edge between two nodes in the graph
public class Edge implements api.EdgeData {
    private int src,dest,tag;
    private double weight;
    private String info;
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
        return this.weight;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        // check
        this.info = String.copyValueOf(s.toCharArray());
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        // is valid?
        this.tag = t;
    }
    public void setWeight(int w){
        // is valid?
        this.weight = w;
    }
}
