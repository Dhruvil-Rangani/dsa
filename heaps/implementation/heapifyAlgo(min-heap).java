package heaps.implementation;

// Given an array nums representing a min-heap and two integers ind and val
//, set the value at index ind (0-based) to val
// and perform the heapify algorithm such that the resulting array follows the min-heap property.

// Modify the original array in-place, no need to return anything.
class Solution {
    private void heapifyDown(int[] nums, int ind) {
        int n = nums.length;
        int smallestInd = ind;

        int leftChildInd = 2 * ind + 1;
        int rightChildInd = 2 * ind + 2;

        if(leftChildInd < n && nums[leftChildInd] < nums[smallestInd]) {
            smallestInd = leftChildInd;
        }

        if (rightChildInd < n && nums[rightChildInd] < nums[smallestInd]) {
            smallestInd = rightChildInd;
        }

        if(smallestInd != ind) {
            int temp = nums[smallestInd];
            nums[smallestInd] = nums[ind];
            nums[ind] = temp;

            heapifyDown(nums, smallestInd);
        }
    }

    private void heapifyUp(int[] nums, int ind) {
        int parentInd = (ind - 1) / 2;
        if(ind > 0 && nums[ind] < nums[parentInd]) {
            int temp = nums[ind];
            nums[ind] = nums[parentInd];
            nums[parentInd] = temp;
            heapifyUp(nums, parentInd);
        }
    }
    public void heapify(int[] nums, int ind, int val) {
        if(nums[ind] > val) {
            nums[ind] = val;
            heapifyUp(nums, ind);
        } else {
            nums[ind] = val;
            heapifyDown(nums, ind);
        }
    }
}