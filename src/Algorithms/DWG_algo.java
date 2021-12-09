package Algorithms;

import Classes.DWG;
import Classes.EdgeData;
import Classes.dwgFromJson;
import api.DirectedWeightedGraph;
import api.NodeData;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.w3c.dom.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DWG_algo implements api.DirectedWeightedGraphAlgorithms{

    private DWG graph;

    @Override
    public void init(DirectedWeightedGraph g) {
        //Takes O(n) check for a better option
        this.graph = (DWG)g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() { //O(n)
        DWG copy= new DWG();
        Iterator<api.NodeData> itNodes = this.graph.nodeIter();
        while(itNodes.hasNext()){
            itNodes.next();
            copy.addNode((api.NodeData)itNodes);
        }
        Iterator<api.EdgeData> itEdges = this.graph.edgeIter();
        while(itEdges.hasNext()){
            itEdges.next();
            int src = ((EdgeData)itEdges).getSrc();
            int dest = ((EdgeData)itEdges).getDest();
            double w = ((EdgeData)itEdges).getWeight();
            copy.connect(src,dest,w);
        }
        return copy;
    }

    public DirectedWeightedGraph ReverseCopy() { //O(n)
        DWG copy= new DWG();
        Iterator<api.NodeData> itNodes = this.graph.nodeIter();
        while(itNodes.hasNext()){
            itNodes.next();
            copy.addNode((api.NodeData)itNodes);
        }
        Iterator<api.EdgeData> itEdges = this.graph.edgeIter();
        while(itEdges.hasNext()){
            itEdges.next();
            int src = ((EdgeData)itEdges).getDest();
            int dest = ((EdgeData)itEdges).getSrc();
            double w = ((EdgeData)itEdges).getWeight();
            copy.connect(src,dest,w);
        }
        return copy;
    }

    @Override
    public boolean isConnected() {
        DFS cur = new DFS((DWG)copy());
        if (!cur.connected())
            return false;
        cur = new DFS((DWG)ReverseCopy());
        if (!cur.connected())
            return false;
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        Dijkstra current = new Dijkstra(graph,src);
        return current.shortestPathDis(dest);
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        Dijkstra current = new Dijkstra(graph,src);
        return current.shortestPath(src,dest);
    }

    @Override
    public NodeData center() {
        if(!isConnected())
            return null;
        Iterator<NodeData> allNodes= graph.nodeIter();
        double min = Integer.MAX_VALUE;
        NodeData res = null;
        while(allNodes.hasNext()){
            int currkey = allNodes.next().getKey();
            Dijkstra current = new Dijkstra(graph,currkey);
            double currMin = current.maxWeight();
            if(currMin<min) {
                min = currMin;
                res = graph.getNode(currkey);
            }
        }
        return res;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file)
    {
        return false;
    }

    @Override
    public boolean load(String file)
    {
        Gson gson = new Gson();
        FileReader r=null;
        try {
            r = new FileReader(file);
        }
        catch (FileNotFoundException e){
            System.out.println("[FILE LOAD] FILE NOT FOUND");
        }
        JsonReader reader = new JsonReader(r);
        dwgFromJson dwg = gson.fromJson(reader, dwgFromJson.class);

        for (Classes.NodeData node: dwg.getNodes())
            node.filler();

        DWG jsonDWG = new DWG();
        for (int i = 0; i < dwg.getNodes().size(); i++) {
            jsonDWG.addNode(dwg.getNodes().get(i));
        }
        for (int i = 0; i < dwg.getEdges().size(); i++) {
            jsonDWG.connect(dwg.getEdges().get(i));
        }
        init(jsonDWG);
        if(graph != null){
            return true;
        }
        return false;
    }
}
