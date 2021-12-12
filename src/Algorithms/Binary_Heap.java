package Algorithms;

import Classes.NodeData;


public interface Binary_Heap {
    /** include hashmap for extracting a given node by its ID and exchanging for a key */

    public int  size();

    public void swim(int index);

    public void sink(int index);

    public void insert(NodeData nodeData);

    public Classes.NodeData remove(NodeData nodeData);

    public Classes.NodeData remove(int node);

    public boolean isEmpty();

    public Classes.NodeData deleteMin();

    public void DecreaseKey(NodeData nodeData, double value);

    public void DecreaseKey(int index, double value);

    public void swap(int i, int j);
}
