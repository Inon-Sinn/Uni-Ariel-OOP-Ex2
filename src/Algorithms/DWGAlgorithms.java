package Algorithms;

import Classes.DWG;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import java.util.List;

public class DWGAlgorithms implements DirectedWeightedGraphAlgorithms {
    DWG g;
    @Override
    public void init(DirectedWeightedGraph g) {
        g = this.g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.g;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DWG gCopy = g;
        return gCopy;
    }
    void DFSUtil(int v, boolean visited[])
    {
//        visited[v] = true;
//        Iterator gItr = g.nodeIter();
//        while(gItr.hasNext()){
//            NodeData n = (NodeData) gItr.next();
//            if (!visited[n.getKey()]) {
//                DFSUtil(n.getKey(), visited);
//            }
//        }
    }
    @Override
    public boolean isConnected() {
//        boolean [] visited = new boolean[g.nodeSize()];
//        for (int i = 0; i < g.nodeSize(); i++) {
//            visited[i] = false;
//        }
//        for (int i = 0; i < g.nodeSize(); i++) {
//            DFSUtil(i, visited);
//        }
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
