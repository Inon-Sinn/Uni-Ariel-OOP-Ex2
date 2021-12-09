package Algorithms;

import Classes.DWG;
import Classes.EdgeData;
import Classes.NodeData;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Dijkstra {

    public DWG graph;
    public HashMap<Integer,Integer> prev;

    public Dijkstra(DWG g, int start) {
        this.graph = g;
        NodeData sNode = (NodeData) graph.getNode(start);
        PriorityQueue<NodeData> Q = new PriorityQueue<>();
        sNode.setWeight(0);
        Iterator<api.NodeData> node_it = g.nodeIter();
        while (node_it.hasNext()) {
            int currkey = node_it.next().getKey();
            Q.add((NodeData) graph.getNode(currkey));
            prev.put(currkey, null);
        }
        while (!Q.isEmpty()) {
            int u = Q.remove().getKey();
            Iterator<api.EdgeData> edges = graph.edgeIter(u);
            while (edges.hasNext()) {
                EdgeData e = (EdgeData) edges.next();
                relax(e.getSrc(), e.getDest(), e.getWeight());
            }
        }
    }

    public void relax(int src,int dest,double w){
        if(graph.getNode(src).getWeight() > (graph.getNode(dest).getWeight() + w)){
            graph.getNode(dest).setWeight(graph.getNode(dest).getWeight() + w);
            prev.put(dest,src);
        }
    }
    public double shortestPathDis(int dest){
        if(graph.getNode(dest).getWeight()==Double.POSITIVE_INFINITY)
            return -1;
        return graph.getNode(dest).getWeight();
    }


    public ArrayList<api.NodeData> shortestPath(int src,int dest){
        if(shortestPathDis(dest)==-1)
            return null;
        ArrayList<api.NodeData> path = new ArrayList<>();
        int current = dest;
        while(prev.get(current)!=src){
            path.add(0,graph.getNode(current));
            current = prev.get(current);
        }
        path.add(0,graph.getNode(src));
        return path;
    }

    public double maxWeight(){
        double w= 0;
        Iterator<api.NodeData> it = graph.nodeIter();
        while(it.hasNext()){
            double cur = it.next().getWeight();
            if(cur > w)
                w = cur;
        }
        return w;
    }
}
