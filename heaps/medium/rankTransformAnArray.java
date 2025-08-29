package heaps.medium;
import java.util.*;
// Given an integer array arr, replace each element with its rank.
// The rank represents how large the element is. The rank has the following rules:
// 1. Rank is an integer starting from 1.
// 2. The larger the element, the larger the rank.
// 3. If two elements are equal, their rank must be the same.
// 4. Rank should be as small as possible.
// Time Complexity: O(n log n)
// Space Complexity: O(n)
// This is the most optimal solution using sorting and hashing

class Solution {
    public int[] arrayRankTransform(int[] nums) {
        int n = nums.length;
        if (n == 0) return new int[0];

        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            set.add(num);
        }

        Integer[] unique = set.toArray(new Integer[0]);
        Arrays.sort(unique);

        Map<Integer, Integer> rankMap = new HashMap<>();
        for(int i = 0; i < unique.length; i++) {
            rankMap.put(unique[i], i + 1);
        }

        int[] result = new int[n];
        for(int i = 0; i < n; i++) {
            result[i] = rankMap.get(nums[i]);
        }
        return result;    
    }
}