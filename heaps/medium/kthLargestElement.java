package heaps.medium;

import java.util.PriorityQueue;
// Given an integer array nums and an integer k, return the kth largest element in the array.
// Note that it is the kth largest element in the sorted order, not the kth distinct element
// You must solve it in O(n) time complexity.
// Time Complexity: O(n log k)
// Space Complexity: O(k)

class Solution {
    public int kthLargestElement(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < k; i++) {
            pq.add(nums[i]);
        }

        for(int i = k; i < nums.length; i++) {
            if(nums[i] > pq.peek()) {
                pq.poll();
                pq.add(nums[i]);
            }
        }

        return pq.peek();
    }
}

// QuickSelect algorithm to find the kth largest element
// Average Time Complexity: O(n), Worst-case Time Complexity: O(n^2)
// Space Complexity: O(1)

class Solution2 {
    private int randomInd(int left, int right) {
        return new Random().nextInt(right - left + 1) + left;
    }

    private int partition(int[] nums, int pIndex, int left, int right) {
        int pivot = nums[pIndex];
        int temp = nums[left];
        nums[left] = nums[pIndex];
        nums[pIndex] = temp;

        int ind = left + 1;

        for(int i = left + 1; i <= right; i++) {
            if(nums[i] > pivot) {
                temp = nums[ind];
                nums[ind] = nums[i];
                nums[i] = temp;
                ind++;
            }
        }

        temp = nums[ind - 1];
        nums[ind - 1] = nums[left];
        nums[left] = temp;
        return ind - 1;
    }

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        if(k > n) return -1;

        int left = 0, right = n - 1;

        while(true) {
            int pIndex = randomInd(left, right);

            pIndex = partition(nums, pIndex, left, right);

            if(pIndex == k - 1) return nums[pIndex];
            else if(pIndex > k - 1) right = pIndex - 1;
            else left = pIndex + 1;
        }
    }
}

