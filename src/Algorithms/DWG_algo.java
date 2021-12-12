package Algorithms;

import Classes.*;
import api.DirectedWeightedGraph;
import api.NodeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
            NodeData nodeData = itNodes.next();
            copy.addNode(nodeData);
        }
        Iterator<api.EdgeData> itEdges = this.graph.edgeIter();
        while(itEdges.hasNext()){
            EdgeData e = (EdgeData) itEdges.next();
            int src = e.getSrc();
            int dest = e.getDest();
            double w = e.getWeight();
            copy.connect(src,dest,w);
        }
        return copy;
    }

    public DirectedWeightedGraph ReverseCopy() { //O(n)
        DWG copy= new DWG();
        Iterator<api.NodeData> itNodes = this.graph.nodeIter();
        while(itNodes.hasNext()){
            NodeData nodeData = itNodes.next();
            copy.addNode((api.NodeData)nodeData);
        }
        Iterator<api.EdgeData> itEdges = this.graph.edgeIter();
        while(itEdges.hasNext()){
            EdgeData e = (EdgeData) itEdges.next();
            int src = e.getSrc();
            int dest = e.getDest();
            double w = e.getWeight();
            copy.connect(dest,src,w);
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
            if(currMin<min && min != -1 ) {
                min = currMin;
                res = graph.getNode(currkey);
            }
        }
        return res;
    }
// Will return null if there is a city we can't connect to or the cities is null
// if cities contains a city multiple times it will be looked at as different cities and visited multiple times
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        //Outliers
        if(cities == null)
            return null;
        if(cities.size()==1)
            return cities;

        ArrayList<api.NodeData> completePath = new ArrayList<>();
        int currentCity = cities.remove(0).getKey();
        int nextCity,remove;
        double min,distance;
        boolean found = false;
        ArrayList<api.NodeData> currentPath = new ArrayList<>();
        while(!cities.isEmpty()){
            nextCity = 0; //Todo will problem when there is no path
            remove = 0;
            min = Double.POSITIVE_INFINITY;
            for (int i = 0; i < cities.size(); i++) {
                ArrayList<NodeData> path = (ArrayList<NodeData>) shortestPath(currentCity, cities.get(i).getKey());
                if (path!= null)
                    distance = graph.getNode(cities.get(i).getKey()).getWeight();
                else distance = Double.POSITIVE_INFINITY;
                if (distance < min) {
                    nextCity = cities.get(i).getKey();
                    remove = i;
                    currentPath = path;
                    min = distance;
                    found = true;
                }
            }
            if (!found)
                return null;
            found = false;
            currentCity = nextCity;
            cities.remove(remove);
            completePath.addAll(currentPath);
        }
        //Removes the dublicates
        for (int i = completePath.size()-1; i >0; i--) {
            if(completePath.get(i).getKey()==completePath.get(i-1).getKey())
                completePath.remove(i);
        }
        return completePath;
    }

    @Override
    public boolean save(String file)
    {
        dwgTojson tojson = new dwgTojson(new ArrayList<primitiveEdgeData>(),new ArrayList<primitiveNodeData>());
        Iterator<api.NodeData> iterator1 = graph.nodeIter();
        Iterator<api.EdgeData> iterator2 = graph.edgeIter();
        while(iterator1.hasNext()) {
            tojson.addNode((Classes.NodeData) iterator1.next());
        }
        while(iterator2.hasNext()){
           tojson.addEdge((Classes.EdgeData)iterator2.next());
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter;
        try {
            fileWriter= new FileWriter(file);
            gson.toJson(tojson,fileWriter);
            fileWriter.close();
        }
        catch(IOException e){
            System.out.println("CANT SAVE TO FILE");
            return false;
        }
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
