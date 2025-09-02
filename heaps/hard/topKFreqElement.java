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

// this is most optimal approach
// Time Complexity: O(n) where n is the number of elements in nums
// Space Complexity: O(n)

class Solution {
    @SuppressWarnings("unchecked")
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);

        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for(int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            buckets[entry.getValue()].add(entry.getKey());
        }

        int[] ans = new int[k];
        int i = 0;
        for(int freq = buckets.length - 1; freq >= 0; freq--) {
            for(int num : buckets[freq]) {
                ans[i++] = num;
                if(i == k) {
                    return ans;
                }
            }
        }

        return ans;
    }
}

