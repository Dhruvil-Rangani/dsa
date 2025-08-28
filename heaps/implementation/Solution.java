package heaps.implementation;
// Time Complexity: O(n log n)
// Space Complexity: O(1)
class Solution {
     private void heapifyDown(int[] arr, int last, int ind) {
        // Index of largest element
        int largestInd = ind; 

        // Indices of the left and right children
        int leftChildInd = 2 * ind + 1, rightChildInd = 2 * ind + 2;
        
        // If the left child holds larger value, update the largest index
        if(leftChildInd <= last && arr[leftChildInd] > arr[largestInd]) {
            largestInd = leftChildInd;
        }

        // If the right child holds larger value, update the largest index
        if(rightChildInd <= last && arr[rightChildInd] > arr[largestInd]) {
            largestInd = rightChildInd;
        }

        // If the largest element index is updated
        if(largestInd != ind) {
            // Swap the largest element with the current index
            int temp = arr[largestInd];
            arr[largestInd] = arr[ind];
            arr[ind] = temp;

            // Recursively heapify the lower subtree
            heapifyDown(arr, last, largestInd);
        }
    }

    private void buildMaxHeap(int[] nums) {
        int n = nums.length;

        for(int i = n/2 - 1; i >= 0; i--) {
            heapifyDown(nums, n - 1, i);
        }
    }

    public void heapSort(int[] nums) {
        int n = nums.length;
        buildMaxHeap(nums);

        int last = n - 1;
        while(last > 0) {
            int temp = nums[0];
            nums[0] = nums[last];
            nums[last] = temp;
            last--;

            if(last > 0) heapifyDown(nums, last, 0);
        }
    }
}