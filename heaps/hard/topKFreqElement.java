package heaps.hard;

import java.util.*;

// Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
// Time Complexity: O(n log k) where n is the number of elements in nums
// Space Complexity: O(n)
// Not the most optimal approach, but easy to understand
class Solution1 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        // Min-heap by freq: [num, freq]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            minHeap.offer(new int[]{entry.getKey(), entry.getValue()});
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        int[] res = new int[k];
        int i = 0;
        while (!minHeap.isEmpty()) {
            res[i++] = minHeap.poll()[0];
        }
        return res;
    }
}

