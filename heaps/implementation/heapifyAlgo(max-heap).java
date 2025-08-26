package heaps.implementation;

class Solution {
    private void heapifyDown(int[] nums, int ind) {
        int n = nums.length;
        int largestInd = ind;

        int leftChildInd = 2 * ind + 1;
        int rightChildInd = 2 * ind + 2;

        if(leftChildInd < n && nums[leftChildInd] > nums[largestInd]) {
            largestInd = leftChildInd;
        }

        if (rightChildInd < n && nums[rightChildInd] > nums[largestInd]) {
            largestInd = rightChildInd;
        }

        if(largestInd != ind) {
            int temp = nums[largestInd];
            nums[largestInd] = nums[ind];
            nums[ind] = temp;

            heapifyDown(nums, largestInd);
        }
    }

    private void heapifyUp(int[] nums, int ind) {
        int parentInd = (ind - 1) / 2;
        if(ind > 0 && nums[ind] > nums[parentInd]) {
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
