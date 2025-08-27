package heaps.implementation;

// Given an array nums representing a min-heap, convert it into a max-heap.
// Modify the original array in-place, no need to return anything.
// A max-heap is a complete binary tree where the value of each node is greater than or equal to the values of its children.
// The resulting array should represent a valid max-heap.
// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    private void heapifyDown(int[] arr, int ind) {
        int n = arr.length; // Size of the array

        // To store the index of largest element
        int largestInd = ind;

        // Indices of the left and right children
        int leftChildInd = 2*ind + 1;
        int rightChildInd = 2*ind + 2;
        
        // If the left child holds larger value, update the largest index
        if(leftChildInd < n && arr[leftChildInd] > arr[largestInd]) {
            largestInd = leftChildInd;
        }

        // If the right child holds larger value, update the largest index
        if(rightChildInd < n && arr[rightChildInd] > arr[largestInd]) {
            largestInd = rightChildInd;
        }

        // If the largest element index is updated
        if(largestInd != ind) {
            // Swap the largest element with the current index
            int temp = arr[largestInd];
            arr[largestInd] = arr[ind];
            arr[ind] = temp;

            // Recursively heapify the lower subtree
            heapifyDown(arr, largestInd);
        }
    }

    public int[] minToMaxHeap(int[] nums) {
        int n = nums.length;

        for(int i = n/2 - 1; i >= 0; i--) {
            heapifyDown(nums, i);
        }
        return nums;
    }
}