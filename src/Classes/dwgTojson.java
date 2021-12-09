package Classes;

import java.util.ArrayList;

public class dwgTojson {
    ArrayList<primitiveEdgeData> Edges;
    ArrayList<primitiveNodeData> Nodes;

    public dwgTojson(ArrayList<primitiveEdgeData> edges, ArrayList<primitiveNodeData> nodes) {
        this.Edges = edges;
        this.Nodes = nodes;
    }

    public ArrayList<primitiveEdgeData> getEdges() {
        return Edges;
    }

    public void setEdges(ArrayList<primitiveEdgeData> edges) {
        this.Edges = edges;
    }

    public ArrayList<primitiveNodeData> getNodes() {
        return Nodes;
    }

    public void setNodes(ArrayList<primitiveNodeData> nodes) {
        this.Nodes = nodes;
    }
    public void addNode(NodeData nodeData){
        String pos = nodeData.getLocation().toString();
        primitiveNodeData s = new primitiveNodeData(pos,nodeData.getKey());
        Nodes.add(s);
    }
    public void addEdge(EdgeData edgeData){
        primitiveEdgeData e = new primitiveEdgeData(edgeData.getSrc(),edgeData.getWeight(),edgeData.getDest());
        Edges.add(e);
    }
}
