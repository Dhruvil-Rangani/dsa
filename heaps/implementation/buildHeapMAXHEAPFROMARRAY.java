package heaps.implementation;
// Given an array nums, build a max-heap from it in-place.
// A max-heap is a complete binary tree where the value of each node is greater than
// or equal to the values of its children.
// The resulting array should represent a valid max-heap.  
// Time Complexity: O(n)
// Space Complexity: O(1)
class Solution2 {
    private void heapifyDown(int[] arr, int ind) {
        int n = arr.length; // Size of the array

        // Index of largest element
        int largest_Ind = ind;

        // Indices of the left and right children
        int leftChild_Ind = 2 * ind + 1;
        int rightChild_Ind = 2 * ind + 2;

        // If the left child holds a larger value, update the largest index
        if (leftChild_Ind < n && arr[leftChild_Ind] > arr[largest_Ind]) {
            largest_Ind = leftChild_Ind;
        }

        // If the right child holds a larger value, update the largest index
        if (rightChild_Ind < n && arr[rightChild_Ind] > arr[largest_Ind]) {
            largest_Ind = rightChild_Ind;
        }

        // If the largest element index is updated
        if (largest_Ind != ind) {
            // Swap the largest element with the current index
            int temp = arr[largest_Ind];
            arr[largest_Ind] = arr[ind];
            arr[ind] = temp;

            // Recursively heapify the lower subtree
            heapifyDown(arr, largest_Ind);
        }
    }

    public void buildMaxHeap(int[] nums) {
        int n = nums.length;

        for(int i = (n / 2) - 1; i >= 0; i--) {
            heapifyDown(nums, i);
        }
        
        return;
    }
}