import java.util.*;
// Rod cutting problem
// TC: >> O(2^N)
// SC: >> O(N) for recursion stack
// TC and SC are not exactly this, it is going to be more than this, but this is the upper bound.
// because we are making two calls at each step, so it is going to be more than this.
// but this is not the optimal solution, we can do better than this using memoization or tabulation.
// this is just to understand the problem and the recursive approach.
class Solution1{
    public int f(int i, int target, int[] price) {
      if(i == 0) {
        return target * price[0];
      }

      int nope = f(i - 1, target, price);
      int yes = (int)(-1e9);
      if((i + 1) <= target) yes = price[i] + f(i, target - (i + 1), price);
      
      return Math.max(nope, yes);
    }

    public int RodCutting(int price[], int n) {
      return f(n - 1, n, price);
    }
}

// memoization approach
// TC: O(N * N)
// SC: O(N * N) + O(N) for recursion stack

class Solution2{
    public int f(int i, int target, int[] price, int[][] dp) {
      if(i == 0) {
        return target * price[0];
      }
      if(dp[i][target] != -1) return dp[i][target];

      int nope = f(i - 1, target, price, dp);
      int yes = (int)(-1e9);
      if((i + 1) <= target) yes = price[i] + f(i, target - (i + 1), price, dp);
      
      return dp[i][target] = Math.max(nope, yes);
    }

    public int RodCutting(int price[], int n) {
      int[][] dp = new int[n][n + 1];
      for(int[] row : dp) Arrays.fill(row, -1);
      return f(n - 1, n, price, dp);
    }
}

// tabulation approach
// TC: O(N * N)
// SC: O(N * N)

class Solution{
    public int RodCutting(int price[], int n) {
      int[][] dp = new int[n][n + 1];
      for(int i = 0; i <= n; i++) dp[0][i] = i * price[0];

      for(int i = 1; i < n; i++) {
        for(int j = 0; j <= n; j++) {
          int nope = dp[i - 1][j];
          int yes = (int)(-1e9);
          if((i + 1) <= j) yes = price[i] + dp[i][j - (i + 1)];
          dp[i][j] = Math.max(nope, yes);
        }
      }
      return dp[n - 1][n];
    }
}