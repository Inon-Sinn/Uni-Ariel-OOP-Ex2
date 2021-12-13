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

/**
 * This class contains all the algorithms we run of the Directed Weighted Graph,
 * it implements the Interface DirectedWeightedGraphAlgorithm
 * graph - DWG - the graph on which the algorithms are executed
 */
public class DWG_algo implements api.DirectedWeightedGraphAlgorithms{

    private Classes.DWG graph;


    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = (DWG)g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() { //O(n)
        DWG copy= new DWG();
        Iterator<NodeData> itNodes = this.graph.nodeIter();
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

    /**
     * Builds the reverse graph of the given graph
     * @return DirectedWeightedGraph - the reverse graph
     */
    public DirectedWeightedGraph ReverseCopy() { //O(n)
        DWG copy= new DWG();
        Iterator<NodeData> itNodes = this.graph.nodeIter();
        while(itNodes.hasNext()){
            NodeData nodeData = itNodes.next();
            copy.addNode((NodeData)nodeData);
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

    /**
     * isConnected - O(|V| + |E|)
     * we Run the the DFS algorithem on the graph from the same node twice.
     * If in the DFS the first and last node we visited was the same node,
     * we would build the reverse graph(make all edges point in the other direction) and Run DFS again and if then the first and last node is still the same,
     * we know the graph is Connected.
     * Why? Because we can reach every node from the given node and every node can reach the given node so that means the whole graph is connected.
     * @return boolean -True: the graph is connected, False: the graph isn't connected
     */
    @Override
    public boolean isConnected() {
        if(graph.nodeSize()==0)
            return true;
        NodeData first = graph.nodeIter().next();
        BFS cur = new BFS((DWG)copy(),first.getKey());
        if (!cur.connected())
            return false;
        cur = new BFS((DWG)ReverseCopy(),first.getKey());
        return cur.connected();
    }

    /**
     * shortestPathDist - O(|E|log|V|)
     * we are given two nodes id's of the source and the destination,
     * we then run Dijstra using those two id's and then return the distance.
     * @param src - start node
     * @param dest - end (target) node
     * @return double - the Distance of the shortest path
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        Dijkstra current = new Dijkstra(graph,src);
        return current.shortestPathDis(dest);
    }

    /**
     *shortestPath - O(|E|log|V|)
     * we are given two nodes id's of the source and the destination,
     * we then run Dijkstra using those two id's and then return the path to get from the source to the destination.
     * @param src - start node
     * @param dest - end (target) node
     * @return List<NodeData> - the shortest path from the souce node to the destination
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        Dijkstra current = new Dijkstra(graph,src);
        return current.shortestPath(src,dest);
    }

    /**
     * center - O(|V||E|log|V|)
     * The cetnter is the node which minimizes the max distance to all the other nodes.
     * First we check if the graph is even connected else there won't be a center at all.
     * If the graph is connected we run Dijkstra from every node we return the node minimizes the max distance to all the other nodes.
     * @return NodeData - the center node
     */
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

    /**
     * tsp - O(n^2*|E|log|V|)
     * traveling salesman problem(almost),
     * we get a list of cities(id's of nodes) and have to return a path that passes through all cities(not the shortest just a path).
     * We do this using a greedy algorithem,
     * we start from the first city, the next city is the closest unvisited city to it,
     * which we find with Dijkstra, and we contuine like this until we went over all city's.
     * After which we return the path we went.
     * @param cities List<NodeData> - a list with the id's of all the cities
     * @return List<NodeData> - the path through all the given cities
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        //Outliers
        if(cities == null)
            return null;
        if(cities.size()==1)
            return cities;

        ArrayList<NodeData> completePath = new ArrayList<>();
        int currentCity = cities.remove(0).getKey();
        int nextCity,remove;
        double min,distance;
        boolean found = false;
        ArrayList<NodeData> currentPath = new ArrayList<>();
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
        Iterator<NodeData> iterator1 = graph.nodeIter();
        Iterator<api.EdgeData> iterator2 = graph.edgeIter();
        while(iterator1.hasNext()) {
            tojson.addNode((Classes.NodeData) iterator1.next());
        }
        while(iterator2.hasNext()){
           tojson.addEdge((EdgeData)iterator2.next());
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter;
        try {
            fileWriter= new FileWriter("data\\" + file);
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
        String file_name = file;
        Gson gson = new Gson();
        FileReader r=null;
        try {
            r = new FileReader(file_name);
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
        }
        catch (FileNotFoundException e){
            System.out.println("[FILE LOAD] FILE NOT FOUND");
        }
        return graph != null;
    }
}
