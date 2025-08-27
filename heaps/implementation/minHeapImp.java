package heaps.implementation;

import java.util.*;
// Implement a min-heap with the following operations:
// initializeHeap() - Initializes the heap to be empty.
// insert(key) - Inserts a new key into the heap.
// changeKey(index, newVal) - Changes the value of the key at index to new
// extractMin() - Removes and returns the minimum key from the heap.
// isEmpty() - Returns true if the heap is empty, false otherwise.
// getMin() - Returns the minimum key from the heap without removing it.
// heapSize() - Returns the number of keys in the heap.
// Time Complexity: O(log n) for insert, changeKey, and extractMin operations
// O(1) for isEmpty, getMin, and heapSize operations
// Space Complexity: O(n) where n is the number of elements in the heap

class Solution {
    private List<Integer> arr;
    private int cnt;

    public Solution() {
        arr = new ArrayList<>();
        cnt = 0;
    }

    private void heapifyUp(List<Integer> arr, int ind) {
        int parentInd = (ind - 1)/2; 

        // If current index holds smaller value than the parent
        if(ind > 0 && arr.get(ind) < arr.get(parentInd)) {
            // Swap the values at the two indices
            int temp = arr.get(ind);
            arr.set(ind, arr.get(parentInd));
            arr.set(parentInd, temp);

            // Recursively heapify the upper nodes
            heapifyUp(arr, parentInd);
        } 

        return;
    }

    private void heapifyDown(List<Integer> arr, int ind) {
        int n = arr.size(); // Size of the array

        // To store the index of smallest element
        int smallestInd = ind; 

        // Indices of the left and right children
        int leftChildInd = 2*ind + 1;
        int rightChildInd = 2*ind + 2;
        
        // If the left child holds smaller value, update the smallest index
        if(leftChildInd < n && arr.get(leftChildInd) < arr.get(smallestInd)) 
            smallestInd = leftChildInd;

        // If the right child holds smaller value, update the smallest index
        if(rightChildInd < n && arr.get(rightChildInd) < arr.get(smallestInd)) 
            smallestInd = rightChildInd;

        // If the smallest element index is updated
        if(smallestInd != ind) {
            // Swap the largest element with the current index
            int temp = arr.get(smallestInd);
            arr.set(smallestInd, arr.get(ind));
            arr.set(ind, temp);

            // Recursively heapify the lower subtree
            heapifyDown(arr, smallestInd);
        }

        return; 
    }

    public void initializeHeap() {
        arr.clear();
        cnt = 0;
    }

    public void insert(int key) {
        arr.add(key);
        heapifyUp(arr, cnt);
        cnt++;
    }

    public void changeKey(int index, int newVal) {
        if(arr.get(index) > newVal) {
            arr.set(index, newVal);
            heapifyUp(arr, index);
        } else {
            arr.set(index, newVal);
            heapifyDown(arr, index);
        }
    }

    public void extractMin() {
        int el = arr.get(0);
        int temp = arr.get(cnt - 1);
        arr.set(cnt - 1, arr.get(0));
        arr.set(0, temp);
        
        arr.remove(cnt - 1);
        cnt--;
        if(cnt > 0) heapifyDown(arr, 0);
    }

    public boolean isEmpty() {
        return cnt == 0;
    }

    public int getMin() {
       return arr.get(0);
    }

    public int heapSize() {
        return cnt;
    }
}
