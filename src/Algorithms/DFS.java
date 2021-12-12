package Algorithms;

import Classes.DWG;
import Classes.NodeData;

import java.util.HashMap;
import java.util.Iterator;

public class DFS {

    private DWG graph;
    private HashMap<Integer,Integer> f = new HashMap<>();
    private HashMap<Integer,Integer> d = new HashMap<>();
    private HashMap<Integer,Integer> prev = new HashMap<>();
    private static final int white = 0;
    private static final int gray = 012;
    private static final int black = 0;
    // colors tag =0 - white, tag =1 - gray, tage=2 - black

    public DFS(DWG g){
        this.graph = g;
        Iterator<api.NodeData> node_it = graph.nodeIter();
        while(node_it.hasNext())
            prev.put(node_it.next().getKey(),null);
        int time =0;
        Iterator<api.NodeData> white_node_it = graph.nodeIter();
        while(white_node_it.hasNext()){
            NodeData curr = (NodeData)white_node_it.next();
            if(curr.getTag()==0)
                time = DFS_visit(curr.getKey(),time);
        }

    }

    public int DFS_visit(int id,int time){
        graph.getNode(id).setTag(1);
        time++;
        d.put(id,time);
        Iterator<api.EdgeData> edge_it = graph.edgeIter(id);
        while(edge_it.hasNext()){
            int dest_id = edge_it.next().getDest();
            if(graph.getNode(dest_id).getTag()==0){
                prev.put(dest_id,id);
                time = DFS_visit(dest_id,time);
            }
        }
        graph.getNode(id).setTag(2);
        time++;
        f.put(id,time);
        return time;
    }

    //return true if the graph itself is empty
    public boolean connected(){
        Iterator<api.NodeData> node_it = graph.nodeIter();
        if(!node_it.hasNext())
            return true;
        NodeData first = (NodeData) node_it.next();
        int first_finishing_time = f.get(first.getKey());
        for (Integer integer : f.values())
            if (integer > first_finishing_time)
                return false;
        return true;
    }
}
