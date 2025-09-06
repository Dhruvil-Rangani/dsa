import java.util.*;
// Leetcode 518. Coin Change 2 https://leetcode.com/problems/coin-change-2/
// recursive approach
// TC: >> O(2^N)
// SC: >> O(N) for recursion stack
// TC and SC are not exactly this, it is going to be more than this, but this is the upper bound.
// because we are making two calls at each step, so it is going to be more than this.
// but this is not the optimal solution, we can do better than this using memoization or tabulation.
// this is just to understand the problem and the recursive approach.
class Solution {
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


