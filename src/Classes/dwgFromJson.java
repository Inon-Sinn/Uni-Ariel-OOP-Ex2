package Classes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class dwgFromJson {
    @SerializedName("Nodes")
    List<NodeData> nodes;
    @SerializedName("Edges")
    List<EdgeData> edges;


    public dwgFromJson(List<NodeData> nodes, List<EdgeData> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public List<EdgeData> getEdges() {
        return edges;
    }

    public List<NodeData> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeData> nodes) {
        this.nodes = nodes;
    }

    public void setEdges(List<EdgeData> edges) {
        this.edges = edges;
    }
}
