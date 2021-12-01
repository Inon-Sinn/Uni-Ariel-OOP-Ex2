package Classes;

import api.GeoLocation;
import api.NodeData;

// this class represents a single Node in the graph
public class Node implements api.NodeData {
    private int key,tag;
    private double weight;
    private String info;
    private GL geoLocation;

    @Override
    public int getKey() {
        return 0;
    }

    @Override
    public GeoLocation getLocation() {
        return this.geoLocation;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.geoLocation.setX(p.x());
        this.geoLocation.setY(p.y());
        this.geoLocation.setZ(p.z());
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        // is valid?
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
        // is valid?
        this.tag = tag;
    }
    public void setKey(int key){
        this.key = key;

    }
}
