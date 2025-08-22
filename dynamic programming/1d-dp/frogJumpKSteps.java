import java.util.Arrays;
// Frog Jump with K Steps
// This is a solution for the Frog Jump problem with K steps using dynamic programming.
// The problem is to find the minimum cost for a frog to jump from the first stone to the last stone,
// where the cost of jumping from stone i to stone j is given by the absolute difference in their heights.
// The frog can jump up to K stones at a time.
// The code below implements a recursive approach to solve the problem.
// Time Complexity: O(n * k) where n is the number of stones and k is
// Space Complexity: O(n) for recursion stack space
// Recursive Approach
class Solution1 {
    private int func(int[] heights, int ind, int k) {
        if(ind == 0) return 0;

        int minSteps = Integer.MAX_VALUE;

        for(int j = 1; j <= k; j++) {
            if(ind - j >= 0) {
                int jump = func(heights, ind - j, k) + Math.abs(heights[ind] - heights[ind - j]);
                minSteps = Math.min(minSteps, jump);
            }
        }

        return minSteps;
    }
    public int frogJump(int[] heights, int k) {
        int ind = heights.length - 1;

        return func(heights, ind, k);
    }
}

// Memoization Approach
// Time Complexity: O(n * k)
// Space Complexity: O(n) for recursion stack space + O(n * k) for memoization
class Solution2 {
    private int func(int[] heights, int[] dp, int ind, int k) {
        if(ind == 0) return 0;

        if(dp[ind] != -1) return dp[ind];

        int minSteps = Integer.MAX_VALUE;

        for(int j = 1; j <= k; j++) {
            if(ind - j >= 0) {
                int jump = func(heights, dp, ind - j, k) + Math.abs(heights[ind] - heights[ind - j]);
                minSteps = Math.min(minSteps, jump);
            }
        }

        return dp[ind] = minSteps;
    }
    
    public int frogJump(int[] heights, int k) {
        int ind = heights.length - 1;
        int[] dp = new int[ind + 1];
        Arrays.fill(dp, -1);

        return func(heights, dp, ind, k);
    }
}

// Tabulation Approach
// Time Complexity: O(n * k)
// Space Complexity: O(n * k) for dp array
class Solution3 {
    public int frogJump(int[] heights, int k) {
        int ind = heights.length;
        int[] dp = new int[ind];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for(int i = 1; i < ind; i++) {
            int minSteps = Integer.MAX_VALUE;

            for(int j = 1; j <= k; j++) {
                if(i - j >= 0){
                    int jump = dp[i - j] + Math.abs(heights[i] - heights[i - j]);
                    minSteps = Math.min(minSteps, jump);
                }
            }
            dp[i] = minSteps;
        }

        return dp[ind - 1];
    }
}

// Space Optimization Approach
// Time Complexity: O(n * k)
// Space Complexity: O(1) for variables
class Solution4 {
    public int frogJump(int[] heights, int k) {
        int ind = heights.length;
        int[] dp = new int[k];

        for(int i = 1; i < ind; i++) {
            int minSteps = Integer.MAX_VALUE;

            for(int j = 1; j <= k; j++) {
                if(i - j >= 0){
                    int preInd = (i - j) % k;
                    int jump = dp[preInd] + Math.abs(heights[i] - heights[i - j]);
                    minSteps = Math.min(minSteps, jump);
                }
            }
            dp[i % k] = minSteps;
        }

        return dp[(ind - 1) % k];
    }
}