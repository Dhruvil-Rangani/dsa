// recursive approach
// Time Complexity: O(2^n) where n is the number of elements in coins
// Space Complexity: O(n) for the recursion stack
import java.util.Arrays;
class Solution {
    private int f(int i, int target, int[] coins) {
        if(i == 0) {
            if(target % coins[i] == 0) return target/coins[i];
            return (int)(1e9);
        }

        int notTake = 0 + f(i - 1, target, coins);
        int take = (int)(1e9);
        if(coins[i] <= target) take = 1 + f(i, target - coins[i], coins);

        return Math.min(notTake, take);
    }

    public int MinimumCoins(int[] coins, int amount) {
        int n = coins.length;
        int ans = f(n - 1, amount, coins);
        return ans == (int)(1e9) ? -1 : ans;
    }
}

// memoization approach
// Time Complexity: O(n * target) where n is the number of elements in arr and target is the target sum
// Space Complexity: O(n * target) + O(n) for the recursion stack
class Solution2 {
    private int f(int i, int target, int[] coins, int[][] dp) {
        if(i == 0) {
            if(target % coins[i] == 0) return target/coins[i];
            return (int)(1e9);
        }

        if(dp[i][target] != -1) return dp[i][target];

        int notTake = 0 + f(i - 1, target, coins, dp);
        int take = (int)(1e9);
        if(coins[i] <= target) take = 1 + f(i, target - coins[i], coins, dp);

        return dp[i][target] = Math.min(notTake, take);
    }

    public int MinimumCoins(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for(int[] row : dp) Arrays.fill(row, -1);
        int ans = f(n - 1, amount, coins, dp);
        return ans == (int)(1e9) ? -1 : ans;
    }
}

