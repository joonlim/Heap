/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heap;

/**
 *
 * @author Joon
 */
public class HeapSort {

    public static void main(String[] args) throws Exception {
        // test
        Integer[] array = new Integer[100000];
        long startTime;
        long endTime;
        long duration;

        array[0] = 100000;
        for (int i = 1; i < 100000; i++) {
            array[i] = (int) (Math.random() * 100000);
        }
        startTime = System.nanoTime();
        sort(array, 100000);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("heap sort: " + duration / 1000000);
    }

    public static void displayArray(int[] data) {

        for (int n : data) {
            System.out.print(n + " ");
        }

    }

    public static void makeHeap(Integer[] data, int size) throws Exception {
        int i;
        int childPosition;
        int position;

        for (i = parentPosition(size - 1); i >= 0; i--) {
            position = i;
            while (leftChildPosition(position) < size) {

                childPosition = leftChildPosition(position);

                if (childPosition < size - 1 // right child exists
                        && data[childPosition + 1] > data[childPosition]) // right child is greater in value
                {
                    childPosition++;
                }
                if (data[childPosition] > data[position]) {
                    swap(data, childPosition, position);
                    position = childPosition;
                } else {
                    break;
                }

            }
        }
    }

    public static int parentPosition(int position) {
        return (position - 1) / 2;
    }

    public static int leftChildPosition(int position) {
        return 2 * position + 1;
    }

    public static int rightChildPosition(int position) {
        return 2 * position + 2;
    }

    public static void swap(Integer[] data, int index1, int index2) throws Exception {
        if (data.length == 0) {
            throw new Exception();
        }
        if (index1 < 0 || index2 < 0) {
            throw new Exception();
        }
        if (index1 >= data.length || index2 >= data.length) {
            throw new Exception();
        }

        int temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;

    }

    /**
     * Trickle down the element at the root. While the element has a value lower
     * than one of its children, swap it with the higher valued child.
     *
     * @precondition The last element was moved to the root prior to the start
     * of the method.
     *
     * @param data
     * @param heapSize
     */
    public static void fixHeap(Integer[] data, int heapSize) throws Exception {
        int position = 0;
        int childPosition;
        //while at least the left child exists
        while (leftChildPosition(position) < heapSize) {
            childPosition = leftChildPosition(position);

            if (childPosition < heapSize - 1 //check if right child exists
                    && data[childPosition + 1] > data[childPosition]) // right child is greater than left child
            {
                childPosition++;
            }
            if (data[position] < data[childPosition]) {
                swap(data, position, childPosition);
                position = childPosition;
            } else {
                return;
            }
        }
    }

    public static void sort(Integer[] data, int size) throws Exception {
        int lastHeapPosition;
        makeHeap(data, size);
        lastHeapPosition = size - 1;
        while (lastHeapPosition > 0) {
            swap(data, 0, lastHeapPosition);
            fixHeap(data, lastHeapPosition);
            lastHeapPosition--;
        }
    }
}
