package Classes;

import java.util.HashMap;
import java.util.Iterator;

public class Nodes extends HashMap<Integer,Node> {
    private HashMap<Integer,Node> nodes;
    Iterator itr = nodes.entrySet().iterator();
    public boolean containsKey(int key){
        return this.nodes.containsKey(Integer.valueOf(key));
    }
}
