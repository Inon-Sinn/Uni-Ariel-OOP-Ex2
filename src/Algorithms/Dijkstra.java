package Algorithms;

import Classes.DWG;
import Classes.NodeData;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Dijkstra {

    public HashMap<Integer,Integer> prev;
    public HashMap<Integer,Double> d;

    public Dijkstra(DWG g, int start){
        Iterator<api.NodeData> node_it = g.nodeIter();
        while(node_it.hasNext()){
            int currkey = node_it.next().getKey();
            prev.put(currkey,null);
            d.put(currkey,Double.valueOf(Integer.MAX_VALUE));
        }
        d.put(start,0.0);
        //PriorityQueue<> Q =


    }
    public double shortestPathDis(int dest){
        if(d.get(dest)==Integer.MAX_VALUE)
            return -1;
        return d.get(dest);
    }


    public ArrayList<api.NodeData> shortestPath(int dest){
        if(shortestPathDis(dest)==-1)
            return null;
        return null;
    }

    public double maxWeight(){
        double w= 0;
        Iterator<Double> it = d.values().iterator();
        while(it.hasNext()){
            double cur = it.next();
            if(cur > w)
                w = cur;
        }
        return w;
    }
}
