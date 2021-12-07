package Algorithms;

import Classes.DWG;
import api.DirectedWeightedGraph;
import api.NodeData;

import java.util.*;

public class DWGAlgorithms implements api.DirectedWeightedGraphAlgorithms {
    DWG g;
    boolean[] visited = new boolean[g.nodeSize()];
    private LinkedList<Integer> adj[];
    @Override
    public void init(DirectedWeightedGraph g) {
        g = this.g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return g;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DWG gCopy = g;
        return gCopy;
    }

    LinkedList<Integer>[] getAdj(){
        adj = new LinkedList[g.nodeSize()];
        for (int i=0; i< adj.length; i++) adj[i] = new LinkedList();
        return adj;
    }

    void DFS(int v, boolean visited[]) {
        visited[v] = true;
        Iterator<Integer> i = getAdj()[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) DFS(n, visited);
        }
    }

    @Override
    public boolean isConnected() {
        boolean visited[] = new boolean[g.nodeSize()];
        DFS(0, visited);
        int count = 0;
        for (int i = 0; i < visited.length; i++) {
            if(visited[i]) count++;
        }
        if(count == g.nodeSize()) return true;
        return false;
    }
    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
}
