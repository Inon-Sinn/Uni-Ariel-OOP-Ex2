package Algorithms;

import Classes.NodeData;
import org.w3c.dom.Node;

import java.security.Key;
import java.sql.Array;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MinHeap implements Binary_Heap{

    private HashMap<Integer,Integer> keyToIndex;
    private ArrayList<Classes.NodeData> heap;



    public MinHeap(){
        this.keyToIndex = new HashMap<>();
        this.heap = new ArrayList<>();

    }

    public MinHeap(ArrayList<NodeData> nodes){
        this.keyToIndex = new HashMap<>();
        this.heap = new ArrayList<>();
        for (NodeData node: nodes)
            insert(node);
    }

    @Override
    public int size() {
        return heap.size();
    }


    @Override
    public void swim(int index) {
        /** recursive calling up untill parent is less than the node*/
        Classes.NodeData node = heap.get(index);
        int parent = (int) index/2;
        int flag = node.compareTo(heap.get(parent));
        // if the current node is smaller than its parent then swap
        // and call swim up
        if(flag == -1){
            swap(index,parent);
            swim(parent);
        }
        // if the current node equal or bigger than its parent
        else{
            return;
        }

    }//TODO check

    @Override
    public void sink(int index) {
        /**Gets a node and check children who is smaller and swap with the smaller one
         * then go recursivly down untill the node reached its place */
        Classes.NodeData parentNode = heap.get(index);
        int leftChild = index*2, rightChild = index*2+1;
        if(leftChild >= size()){
            return;
        }
        int minimumChild;
        //check if minimum child < parent
        //left child exist
        try{
            heap.get(leftChild);
            try{// right child exist
                heap.get(rightChild);
                minimumChild = heap.get(leftChild).compareTo(heap.get(rightChild))
                        == -1 ? leftChild : rightChild;
            }
            catch(IndexOutOfBoundsException e){
                return;
            }
        }
        catch(IndexOutOfBoundsException e){
            try{
                heap.get(rightChild);
                minimumChild = rightChild;

            }
            catch(IndexOutOfBoundsException e1){
                return;
            }
        }
        int flag = parentNode.compareTo(heap.get(minimumChild));
        if(flag > 1){
            swap(minimumChild, index);
            sink(minimumChild);
        }
        return;



    }//TODO check

    // O(logn)
    @Override
    public void insert(NodeData nodeData) {
        /* adds the node to the last place in the heap and then calls to compare up with swim */
        if( nodeData == null)
            throw new RuntimeException("cant add null to the heap");
        this.heap.add(nodeData);
        keyToIndex.put(nodeData.getKey(),heap.size()-1);
        swim(heap.size()-1);
    }

    // O(log n)
    @Override
    public NodeData remove(NodeData nodeData) {
        return remove(this.keyToIndex.get(nodeData.getKey()));
    }

    // O(log n)
    @Override
    public NodeData remove(int index) {
        swap(index,heap.size()-1);
        sink(0);
        return heap.remove(heap.size()-1);
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }
    // O(log n)
    @Override
    public NodeData deleteMin() {
        return remove(0);
    }

    @Override
    public void DecreaseKey(NodeData nodeData, double w) {
        DecreaseKey(nodeData.getKey(),w);
    }
    // O(logn)
    @Override
    public void DecreaseKey(int key, double w){
        heap.get(keyToIndex.get(key)).setWeight(w);
        swim(keyToIndex.get(key));
    }
    //O(1)
    @Override
    public void swap(int i, int j) {
        if(j < i){
            int temp = j;
            j = i;
            i = temp;
        }
        if(i < j) {
            NodeData iNode = heap.remove(i);
            NodeData jNode = heap.remove(j - 1);
            heap.add(i, jNode);
            heap.add(j, iNode);
            //swap indexes
            keyToIndex.put(heap.get(i).getKey(), i);
            keyToIndex.put(heap.get(j).getKey(), j);
        }

    }
}
