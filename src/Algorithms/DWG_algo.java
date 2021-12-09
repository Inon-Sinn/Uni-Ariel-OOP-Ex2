package Algorithms;

import Classes.DWG;
import Classes.EdgeData;
import Classes.dwgFromJson;
import api.DirectedWeightedGraph;
import api.NodeData;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        return cur.connected();
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
        ArrayList<api.NodeData> completePath = new ArrayList<>();
        int currentCity = cities.remove(0).getKey();
        double min = Double.POSITIVE_INFINITY;
        ArrayList<api.NodeData> currentPath = new ArrayList<>();
        while(!cities.isEmpty()){
            int nextCity = 0; //Todo will problem when there is no path
            int remove = 0;
            for (NodeData city : cities) {
                ArrayList<NodeData> path = (ArrayList<NodeData>) shortestPath(currentCity, city.getKey());
                double dis = graph.getNode(city.getKey()).getWeight();
                if (dis < min) {
                    nextCity = city.getKey();
                    currentPath = path;
                    min = dis;
                }
            }
            currentCity = nextCity;
            cities.remove(remove);
            completePath.addAll(currentPath);
        }
        return completePath;
    }

    @Override
    public boolean save(String file)
    {
        ArrayList<Classes.NodeData> nodes = new ArrayList<>();
        ArrayList<EdgeData> edges = new ArrayList<>();
        Iterator<api.NodeData> iterator1 = graph.nodeIter();
        Iterator<api.EdgeData> iterator2 = graph.edgeIter();
        while(iterator1.hasNext()) {
            nodes.add((Classes.NodeData)iterator1.next());
        }
        while(iterator2.hasNext()){
            edges.add((Classes.EdgeData)iterator2.next());
        }
        dwgFromJson tojson = new dwgFromJson(nodes,edges);
        Gson gson = new Gson();
        FileWriter fileWriter;
        try {
            fileWriter= new FileWriter(file);
        }
        catch(IOException e){
            System.out.println("CANT SAVE TO FILE");
            return false;
        }
        gson.toJson(tojson,fileWriter);
        return true;
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
        return graph != null;
    }
}
