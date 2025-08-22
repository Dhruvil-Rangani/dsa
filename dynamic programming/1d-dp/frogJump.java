import java.util.*;
// This is a solution for the Frog Jump problem using dynamic programming.
// but it is not the correct solution for the problem as described in the context.
// The problem is to find the minimum cost for a frog to jump from the first stone to the last stone,
// where the cost of jumping from stone i to stone j is given by the absolute difference in their heights.
// The frog can jump to the next stone or skip one stone.
// The code below implements three different approaches: memoization, tabulation, and space optimization.
// The frogJump function calculates the minimum cost for the frog to reach the last stone.
// Dynamic Programming Approach
// Time Complexity: O(n)
// space Complexity: O(n) for recursion stack space + O(n) for memoization
// Memoization Approach
class Solution1 {
    private int frogJump(int[] heights, int[] dp, int ind) {
        if(ind == 0) return 0;

        if(dp[ind] != -1) return dp[ind];

        int jumpOne = frogJump(heights, dp, ind - 1) + Math.abs(heights[ind] - heights[ind - 1]);

        int jumpTwo = Integer.MAX_VALUE;

        if(ind > 1) {
            jumpTwo = frogJump(heights, dp, ind - 2) + Math.abs(heights[ind] - heights[ind - 2]);
        }

        return dp[ind] = Math.min(jumpOne, jumpTwo);
    }
    public int frogJump(int[] heights) {
        int n = heights.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return frogJump(heights, dp, n - 1);
    }
}

// Tabulation Approach
// Time Complexity: O(n) 
// Space Complexity: O(n) for dp array
class Solution2 {
    public int frogJump(int[] heights) {
        int n = heights.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for(int ind = 1; ind < n; ind++) {
            int jumpOne = dp[ind - 1] + Math.abs(heights[ind] - heights[ind - 1]);
            int jumpTwo = Integer.MAX_VALUE;
            if(ind > 1) {
                jumpTwo = dp[ind - 2] + Math.abs(heights[ind] - heights[ind - 2]);
            }
            dp[ind] = Math.min(jumpOne, jumpTwo);
        }

        return dp[n - 1];
    }
}

// Space Optimization Approach
// Time Complexity: O(n)
// Space Complexity: O(1) for variables
class Solution3 {
    public int frogJump(int[] heights) {
        int n = heights.length;
        int prev = 0, prev2 = 0;

        for(int ind = 1; ind < n; ind++) {
            int jumpOne = prev + Math.abs(heights[ind] - heights[ind - 1]);
            int jumpTwo = Integer.MAX_VALUE;
            if(ind > 1) {
                jumpTwo = prev2 + Math.abs(heights[ind] - heights[ind - 2]);
            }
            int cur = Math.min(jumpOne, jumpTwo);
            prev2 = prev;
            prev = cur;
        }

        return prev;
    }
}