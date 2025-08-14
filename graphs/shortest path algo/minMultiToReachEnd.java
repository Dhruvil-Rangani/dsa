import java.util.*;
// LeetCode Problem: Minimum Multiplications to Reach End
// https://leetcode.com/problems/minimum-multiplications-to-reach-end/
// Time Complexity: O(N * M), where N is the number of elements in arr and M is the range of values (100000).
// Space Complexity: O(M), for the minSteps array and the queue.
// This code finds the minimum number of multiplications needed to reach a target value in a modular
// arithmetic setting using a breadth-first search approach.

class Solution {
    public int minimumMultiplications(int[] arr, int start, int end) {
       if(start == end) return 0;

       int n = arr.length;
       int mod = 100000;

       int[] minSteps = new int[mod];
       Arrays.fill(minSteps, Integer.MAX_VALUE);

       Queue<int[]> q = new LinkedList<>();
       minSteps[start] = 0;

       q.add(new int[]{0, start});

       while(!q.isEmpty()) {
        int[] p = q.poll();

        int steps = p[0], val = p[1];

        for(int i = 0; i < n; i++) {
            int num = (val * arr[i]) % mod;
            if(num == end) return steps + 1;

            if(steps + 1 < minSteps[num]) {
                minSteps[num] = steps + 1;
                q.add(new int[]{steps + 1, num});
            }
        }
       }

       return -1;
    }
}
