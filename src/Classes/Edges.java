package Classes;

import java.util.HashMap;
import java.util.Iterator;

public class Edges extends HashMap<Tuple,Edge>{
    private HashMap<Tuple,Edge> edges;
    Iterator itr = edges.entrySet().iterator();
    public Edges(){
        this.edges = new HashMap<Tuple,Edge>();
    }

}
