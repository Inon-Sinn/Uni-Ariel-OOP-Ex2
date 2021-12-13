package Algorithms;

import Classes.NodeData;
import api.Binary_Heap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class Represents a Min Heap, implements Interface Binaryheap
 * keyToIndex - HashMap<Integer,Integer> - a hashmap that maps the id's of the node to index's in the heap
 * heap - ArrayList<NodeData> - a Arraylist which represent the min heap
 */
public class MinHeap implements Binary_Heap {
    // keyToIndex maps id of the node to the index in the heap
    private HashMap<Integer,Integer> keyToIndex;
    private ArrayList<NodeData> heap;

    public MinHeap(){
        this.keyToIndex = new HashMap<>();
        this.heap = new ArrayList<>();
        this.heap.add(null);
    }

    public MinHeap(ArrayList<NodeData> nodes){
        this.keyToIndex = new HashMap<>();
        this.heap = new ArrayList<>();
        heap.add(null);//Todo
        for (NodeData node: nodes)
            insert(node);
    }

    @Override
    public int size() {
        return heap.size();
    }

    /** recursive calling up untill parent is less than the node*/

    @Override
    public void swim(int index) {
        // getting root node then get out
        if(index==1)
            return;
        NodeData node = heap.get(index);
        int parent = index /2;

        int flag = node.compareTo(heap.get(parent));
        // if the current node is smaller than its parent then swap
        // and call swim up
        if(flag == -1){
            swap(index,parent);
            swim(parent);
        }
    }//TODO check

    /**Gets a node and check children who is smaller and swap with the smaller one
     * then go recursivly down untill the node reached its place */
    @Override
    public void sink(int index) {
        NodeData parentNode = heap.get(index);
        int leftChild = index*2, rightChild = index*2+1;
        if(index == 0){
            leftChild=1;rightChild=2;
        }
        if(leftChild >= size()){
            return;
        }
        int minimumChild = leftChild;
        if (rightChild < size())
            minimumChild = heap.get(leftChild).compareTo(heap.get(rightChild)) < 0 ? leftChild : rightChild;

        int flag = parentNode.compareTo(heap.get(minimumChild));
        if(flag == 1){
            swap(minimumChild, index);
            sink(minimumChild);
        }


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
        NodeData res = heap.remove(heap.size()-1);
        if(size()>1)
            sink(index);
        return res;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 1;
    }
    // O(log n)
    @Override
    public NodeData deleteMin() {
        return remove(1);
    }

    @Override
    public void DecreaseKey(NodeData nodeData, double w) {
        DecreaseKey(nodeData.getKey(),w);
    }
    // O(logn)
    @Override
    public void DecreaseKey(int key, double w){
        if(keyToIndex.get(key)<size()) {
            heap.get(keyToIndex.get(key)).setWeight(w);
            swim(keyToIndex.get(key));
        }
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
