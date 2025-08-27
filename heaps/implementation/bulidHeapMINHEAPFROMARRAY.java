package heaps.implementation;
// Given an array nums, build a min-heap from it in-place.
// A min-heap is a complete binary tree where the value of each node is less than or equal to the values of its children.
// The resulting array should represent a valid min-heap.
// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    private void heapifyDown(int[] arr, int ind) {
        int n = arr.length; // Size of the array

        // Index of smallest element
        int smallest_Ind = ind;

        // Indices of the left and right children
        int leftChild_Ind = 2 * ind + 1;
        int rightChild_Ind = 2 * ind + 2;

        // If the left child holds a smaller value, update the smallest index
        if (leftChild_Ind < n && arr[leftChild_Ind] < arr[smallest_Ind]) {
            smallest_Ind = leftChild_Ind;
        }

        // If the right child holds a smaller value, update the smallest index
        if (rightChild_Ind < n && arr[rightChild_Ind] < arr[smallest_Ind]) {
            smallest_Ind = rightChild_Ind;
        }

        // If the smallest element index is updated
        if (smallest_Ind != ind) {
            // Swap the smallest element with the current index
            int temp = arr[smallest_Ind];
            arr[smallest_Ind] = arr[ind];
            arr[ind] = temp;

            // Recursively heapify the lower subtree
            heapifyDown(arr, smallest_Ind);
        }
    }

    public void buildMinHeap(int[] nums) {
        int n = nums.length;

        for(int i = (n / 2) - 1; i >= 0; i--) {
            heapifyDown(nums, i);
        }
        
        return;
    }
}
