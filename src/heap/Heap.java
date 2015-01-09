/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heap;

import java.util.ArrayList;

/**
 * Heap class uses an ArrayList to store the data of a maxheap.
 *
 * Parent position = (position - 1) / 2
 *
 * Left child position = 2 * position + 1
 *
 * Right child position = 2 * position + 2
 *
 * @author Joon
 */
public class Heap{

    private ArrayList<Integer> data;
    private int heapSize;
    private int maxSize;

    public Heap() {
        data = new ArrayList<Integer>();
        heapSize = 0;
    }

    public int parentPosition(int position) {
        return (position - 1) / 2;
    }

    public int leftChildPosition(int position) {
        return 2 * position + 1;
    }

    public int rightChildPosition(int position) {
        return 2 * position + 2;
    }

    public String toString() {
        return data.subList(0, heapSize).toString();
    }

    public boolean isEmpty() {
        return (heapSize == 0);
    }

    public void swap(int index1, int index2) throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }
        if (index1 < 0 || index2 < 0) {
            return;
        }
        if (index1 >= heapSize || index2 >= heapSize) {
            return;
        }

        Integer temp = data.get(index1);
        data.set(index1, data.get(index2));
        data.set(index2, temp);

    }

    /**
     * Place a new element in first available position. Compares the element
     * with its parent, and swap the two if the element has a greater value than
     * its parent. Repeat the process until either the item's parent is finally
     * greater than the element or the item reaches the root(index 0).
     *
     * O(log n)
     *
     * @param item
     * @throws Exception
     */
    public void insert(int item) throws Exception {
        int position;
        heapSize++;

        data.add(heapSize - 1, item);
        position = heapSize - 1;

        // Repeats the process until either the item's parent is finally
        // greater than the element or the item reaches the root(index 0).
        while (position > 0
                && data.get(position) > data.get(parentPosition(position))) {
            swap(position, parentPosition(position));
            position = parentPosition(position);
        }
    }

    /**
     * Removes the root and returns its value. Replace the root with the last
     * element and reheapify using fixHeap() method.
     *
     * O(log n)
     *
     * @return the removed item
     * @throws Exception
     */
    public int remove() throws Exception {
        int answer;
        if (isEmpty()) {
            throw new Exception();
        }
        answer = data.get(0); // to be returned
        data.set(0, data.get(heapSize - 1));
        heapSize--;
        //reheapify
        fixHeap(heapSize);
        return answer;
    }

    /**
     * Trickle down the element at the root. While the element has a value lower
     * than one of its children, swap it with the higher valued child.
     *
     * @precondition The last element was moved to the root prior to the start
     * of the method.
     *
     * @param heapSize
     */
    public void fixHeap(int heapSize) throws Exception {
        int position = 0;
        int childPosition;
        //while at least the left child exists
        while (leftChildPosition(position) < heapSize) {
            childPosition = leftChildPosition(position);

            if (childPosition < heapSize - 1 //check if right child exists
                    && data.get(childPosition + 1) > data.get(childPosition)) // right child is greater than left child
            {
                childPosition++;
            }
            if (data.get(position) < data.get(childPosition)) {
                swap(position, childPosition);
                position = childPosition;
            } else {
                return;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Heap heap = new Heap();
        heap.insert(1);
        heap.insert(2);
        heap.insert(5);
        System.out.println(heap);
        heap.remove();
        System.out.println(heap);
        
        

    }

}
