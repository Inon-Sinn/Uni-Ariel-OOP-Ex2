package api;

import Classes.NodeData;

/**
 * a Interface for a BinaryHeap of NodeData's
 */
public interface Binary_Heap {
    /** include hashmap for extracting a given node by its ID and exchanging for a key */

    /**
     * Returns the number of nodes left in the heap
     * @return int - number of nodes ledt in the heap
     */
    public int  size();

    /**
     *  Heapify up
     * @param index int - the index of the node we want to heapify up
     */
    public void swim(int index);

    /**
     * Heapify down
     * @param index int - the index of the node we want heapify down
     */
    public void sink(int index);

    /**
     * Insert a new node to the heap
     * @param nodeData Classes.NodeData - the node we want to insert
     */
    public void insert(NodeData nodeData);

    /**
     * Removes a given node from the heap
     * @param nodeData - classes.nodeData - the node we want to Remove
     * @return Classes.NodeData - the node we removed
     */
    public NodeData remove(NodeData nodeData);

    /**
     * Removes a node from the heap by the id of the node
     * @param node int - the id of the node we want to remove
     * @return Classes.NodeData - the node we removed
     */
    public NodeData remove(int node);

    /**
     * Checks if the heap is empty
     * @return boolean - True: the heap is empty, Fasle - the heap isn't empty
     */
    public boolean isEmpty();

    /**
     * Removes the smallest node from the heap( arcoding to the Comparator of NodeData)
     * @return Classes.NodeData - the smallest node
     */
    public NodeData deleteMin();

    /**
     * Should be O(log n) if done correctly
     * Decrease the key of a node in the heap
     * @param nodeData Classes.NodeData - the node with the key we want to decrease
     * @param value double - the new key
     */
    public void DecreaseKey(NodeData nodeData, double value);

    /**
     * Decrease the key of a node in the hea
     * @param index int - the index of the node with the key we want to decrase in the heap
     * @param value double - the new key
     */
    public void DecreaseKey(int index, double value);

    /**
     * An auxiliary function: swap, swaps to node in the heap
     * @param i int - the index of the first node in the heap
     * @param j int - the index of the second node in the heap
     */
    public void swap(int i, int j);

}
