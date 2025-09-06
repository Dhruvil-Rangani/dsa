import java.util.*;
// Infinite Knapsack
// recursive approach
// TC: >> O(2^N)
// SC: >> O(N) for recursion stack
// TC and SC are not exactly this, it is going to be more than this, but this is the upper bound.
// because we are making two calls at each step, so it is going to be more than this.
// but this is not the optimal solution, we can do better than this using memoization or tabulation.
// this is just to understand the problem and the recursive approach.
class Solution1 {
    public int f(int i, int target, int[] wt, int[] val) {
        if(i == 0) {
            return (target / wt[0]) * val[0];
        }
        int nope = f(i - 1, target, wt, val);
        int yes = (int)(-1e9);
        if(wt[i] <= target) yes = val[i] + f(i, target - wt[i], wt, val);
        
        return Math.max(nope, yes);
    }

    public int unboundedKnapsack(int[] wt, int[] val, int n, int W) {
        return f(n - 1, W, wt, val);
    }
}

// memoization approach
// TC: O(N * W)
// SC: O(N * W) + O(N) for recursion stack
class Solution2 {
    public int f(int i, int target, int[] wt, int[] val, int[][] dp) {
        if(i == 0) {
            return (target / wt[0]) * val[0];
        }
        if(dp[i][target] != -1) return dp[i][target];
        int nope = f(i - 1, target, wt, val, dp);
        int yes = (int)(-1e9);
        if(wt[i] <= target) yes = val[i] + f(i, target - wt[i], wt, val, dp);
        
        return dp[i][target] = Math.max(nope, yes);
    }

    public int unboundedKnapsack(int[] wt, int[] val, int n, int W) {
        int[][] dp = new int[n][W + 1];
        for(int[] row : dp) Arrays.fill(row, -1);
        return f(n - 1, W, wt, val, dp);
    }
}

// tabulation approach
// TC: O(N * W)
// SC: O(N * W)
// by using tabulation we can avoid the recursion stack space.
class Solution3 {
    public int unboundedKnapsack(int[] wt, int[] val, int n, int W) {
        int[][] dp = new int[n][W + 1];
        for(int i = 0; i <= W; i++) {
            dp[0][i] = (i / wt[0]) * val[0];
        }

        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= W; j++) {
                int nope = dp[i - 1][j];
                int yes = (int)(-1e9);
                if(wt[i] <= j) yes = val[i] + dp[i][j - wt[i]];
                dp[i][j] = Math.max(nope, yes);
            }
        }

        return dp[n - 1][W];
    }
}

// space optimization
// TC: O(N * W)
// SC: O(W)

class Solution {
    public int unboundedKnapsack(int[] wt, int[] val, int n, int W) {
        int[] dp = new int[W + 1];
        for(int i = 0; i <= W; i++) {
            dp[i] = (i / wt[0]) * val[0];
        }

        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= W; j++) {
                int nope = dp[j];
                int yes = (int)(-1e9);
                if(wt[i] <= j) yes = val[i] + dp[j - wt[i]];
                dp[j] = Math.max(nope, yes);
            }
        }

        return dp[W];
    }
}



