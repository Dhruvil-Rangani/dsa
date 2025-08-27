package heaps.implementation;

class Solution {
    public boolean isHeap(int[] nums) {
        int n = nums.length;

        for(int i = n/2 - 1; i >= 0; i--) {
            int leftChildInd = 2 * i + 1;
            int rightChildInd = 2 * i + 2;

            if(leftChildInd < n && nums[leftChildInd] > nums[i]) return false;
            if(rightChildInd < n && nums[rightChildInd] > nums[i]) return false;
        }

        return true;
    }
}