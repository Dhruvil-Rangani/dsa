// recursive approach
// Time Complexity: O(2^n) where n is the number of elements in coins
// Space Complexity: O(n) for the recursion stack
import java.util.Arrays;
class Solution1 {
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

// tabulation approach
// Time Complexity: O(n * target) where n is the number of elements in arr and target is the target sum
// Space Complexity: O(n * target)
class Solution3 {
    private int f(int i, int target, int[][] dp, int[] coins) {
        if(i == 0) {
            if(target % coins[i] == 0) return target/coins[i];
            return (int)(1e9);
        }

        if(dp[i][target] != -1) return dp[i][target];

        int notTake = 0 + f(i - 1, target, dp, coins);
        int take = (int)(1e9);
        if(coins[i] <= target) take = 1 + f(i, target - coins[i], dp, coins);

        return dp[i][target] = Math.min(notTake, take);
    }

    public int MinimumCoins(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0)
                dp[0][i] = i / coins[0];
            else
                dp[0][i] = (int)1e9;
        }
        

        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= amount; j++) {
                int notTake = 0 + dp[i - 1][j];
                int take = (int)(1e9);
                if(coins[i] <= j) take = 1 + dp[i][j - coins[i]];
                dp[i][j] = Math.min(notTake, take);
            }
        }

        return dp[n - 1][amount] == (int)(1e9) ? -1 : dp[n - 1][amount];
    }
}

// space optimization approach
// Time Complexity: O(n * target) where n is the number of elements in arr and target is the target sum
// Space Complexity: O(target)

class Solution {
    public int MinimumCoins(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0)
                dp[i] = i / coins[0];
            else
                dp[i] = (int)1e9;
        }
        

        for(int i = 1; i < n; i++) {
            int[] temp = new int[amount + 1];
            for(int j = 0; j <= amount; j++) {
                int notTake = 0 + dp[j];
                int take = (int)(1e9);
                if(coins[i] <= j) take = 1 + temp[j - coins[i]];
                temp[j] = Math.min(notTake, take);
            }
            dp = temp;
        }

        return dp[amount] == (int)(1e9) ? -1 : dp[amount];
    }
}




