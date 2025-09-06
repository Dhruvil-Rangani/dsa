import java.util.*;
// Leetcode 518. Coin Change 2 https://leetcode.com/problems/coin-change-2/
// recursive approach
// TC: >> O(2^N)
// SC: >> O(N) for recursion stack
// TC and SC are not exactly this, it is going to be more than this, but this is the upper bound.
// because we are making two calls at each step, so it is going to be more than this.
// but this is not the optimal solution, we can do better than this using memoization or tabulation.
// this is just to understand the problem and the recursive approach.
class Solution1 {
  private int f(int i, int target, int[] coins) {
    if(i == 0) {
      if(target % coins[0] == 0) return 1;
      return 0;
    }

    int notPick = f(i - 1, target, coins);
    int pick = 0;
    if(coins[i] <= target) pick = f(i, target - coins[i], coins);

    return notPick + pick;
  }

  public int count(int[] coins, int N, int amount) {
    return f(N - 1, amount, coins);
  }
}

// memoization approach
// TC: O(N * amount)
// SC: O(N * amount) + O(N) for recursion stack

class Solution2 {
  private static final int mod = 1000000007;
  private int f(int i, int target, int[][] dp, int[] coins) {
    if(i == 0) {
      if(target % coins[0] == 0) return 1;
      return 0;
    }
    if(dp[i][target] != -1) return dp[i][target];

    int notPick = f(i - 1, target, dp, coins);
    int pick = 0;
    if(coins[i] <= target) pick = f(i, target - coins[i], dp, coins);

    return dp[i][target] = (notPick + pick) % mod;
  }

  public int count(int[] coins, int N, int amount) {
    int[][] dp = new int[N][amount + 1];
    for(int[] row : dp) Arrays.fill(row, -1);
    return f(N - 1, amount, dp, coins);
  }
}

// tabulation approach
// TC: O(N * amount)
// SC: O(N * amount)
// by using tabulation we can avoid the recursion stack space.
class Solution3 {
  private static final int mod = 1000000007;

  public int count(int[] coins, int N, int amount) {
    int[][] dp = new int[N][amount + 1];
    for(int i = 0; i <= amount; i++) {
      if(i % coins[0] == 0) dp[0][i] = 1;
      else dp[0][i] = 0;
    }

    for(int i = 1; i < N; i++) {
      for(int j = 0; j <= amount; j++) {
        int notPick = dp[i - 1][j];
        int pick = 0;
        if(coins[i] <= j) pick = dp[i][j - coins[i]];
        dp[i][j] = (pick + notPick) % mod;
      }
    }

    return dp[N - 1][amount];
  }
}

