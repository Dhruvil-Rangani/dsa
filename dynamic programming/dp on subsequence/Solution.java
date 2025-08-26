import java.util.*;
// Subset Sum Problem - Dynamic Programming
// This is a solution for the Subset Sum problem using dynamic programming.
// The problem is to determine if there is a subset of the given set with a sum equal to a given target.
// The code below implements a recursive approach with memoization to solve the problem.
// Time Complexity: O(n * target) where n is the number of elements in the array and target is the given sum
// Space Complexity: O(n * target) for the memoization table
// The memoization table is of size n x (target + 1), where n is the number of elements in the array and target is the given sum.
// The recursive function calculates whether a subset with the given target sum can be formed using the first i elements of the array.
// The base case is when the target sum is 0, where there is always a subset (the empty subset).
// The edge case is when there are no elements left to consider (i < 0), where no subset can be formed unless the target is also 0.
// The recursive function explores two possibilities: including the current element in the subset or excluding it, 
// and returns true if either possibility leads to a valid subset sum.
// The memoization approach reduces the time complexity to O(n * target) by storing results of subproblems in a table,
// avoiding redundant calculations.
// without memoization, the time complexity would be O(2^n), as each element can either be included or excluded,
// leading to an exponential number of combinations.
// and space complexity without memoization is O(n) for the recursion stack space

class Solution {
    private boolean func(int i, int[] arr, int target, int[][] dp) {
        if(target == 0) return true;
        if(i < 0) return false;
        if(dp[i][target] != -1) return dp[i][target] == 1 ? true : false;

        // include in subseq
        boolean pick = false;
        if(target >= arr[i]) {
            pick = func(i - 1, arr, target - arr[i], dp);
        }
        // exclude in subseq
        boolean notpick = func(i - 1, arr, target, dp);
        dp[i][target] = pick || notpick ? 1 : 0;
        return pick || notpick;
    }

    public boolean isSubsetSum(int[] arr, int target) {
      int n = arr.length;
      int[][] dp = new int[n][target + 1];
      for(int[] row : dp) Arrays.fill(row, -1);

      return func(n - 1, arr, target, dp);
    }
}

// Tabulation Approach
// Time Complexity: O(n * target) where n is the number of elements in the array and target is the given sum
// Space Complexity: O(n * target) for the dp table

class Solution2 {
    public boolean isSubsetSum(int[] arr, int target) {
      int n = arr.length;
      boolean[][] dp = new boolean[n][target + 1];
      for(int i = 0; i < n; i++) dp[i][0] = true;
      if(arr[0] <= target) dp[0][arr[0]] = true;

      for(int i = 1; i < n; i++) {
        for(int j = 1; j <= target; j++) {
            boolean notTaken = dp[i - 1][j];
            boolean taken = false;
            if(arr[i] <= j) taken = dp[i - 1][j - arr[i]];
            dp[i][j] = notTaken || taken;
        }
      }

      return dp[n - 1][target];
    }
}

