import java.util.Arrays;
// recursive approach
// Time Complexity: O(2^n) where n is the number of elements in arr
// Space Complexity: O(n) for the recursion stack
class Solution1 {
    // Modulus value to avoid overflow in calculations.
    int mod = (int)1e9 + 7;

    /* Recursive function to count the number of subsets
       with a given target sum. Uses memoization to store results
       of subproblems to avoid redundant computations. */
    int countPartitionsUtil(int ind, int target, int[] arr) {
        // Base case: If we are at the first element.
        if (ind == 0) {
            /* If target is 0 and the element is also 0, there 
            are 2 ways to achieve this (include or exclude). */
            if (target == 0 && arr[0] == 0)
                return 2;

            /* If target is 0 or the element is equal to 
            target, there is 1 way to achieve this. */
            if (target == 0 || target == arr[0])
                return 1;

            return 0;
        }

        // Calculate number of ways not including current element.
        int notTaken = countPartitionsUtil(ind - 1, target, arr);

        /* Calculate the number of ways including the
        current element (if it can be included). */
        int taken = 0;
        if (arr[ind] <= target)
            taken = countPartitionsUtil(ind - 1, target - arr[ind], arr);

        // Return the result 
        return (notTaken + taken) % mod;
    }

    /* Function to count number of subsets with a given
    difference.Uses the helper function `countPartitionsUtil`
    to find the number of subsets with a specific target sum. */
    int countPartitions(int n, int diff, int[] arr) {
        int totSum = 0;

        // Calculate the total sum of elements in the array.
        for (int i = 0; i < arr.length; i++) {
            totSum += arr[i];
        }

        /* If the total sum minus the difference is negative,
           or if it is not even, it's not possible to divide 
           the array into two subsets with the given difference. */
        if (totSum - diff < 0) return 0;
        if ((totSum - diff) % 2 == 1) return 0;

        // Calculate the target sum for one of the subsets.
        int s2 = (totSum - diff) / 2;

        /* Call the helper function to count
        the number of subsets with sum s2. */
        return countPartitionsUtil(n - 1, s2, arr);
    }
}

// memoization approach
// Time Complexity: O(n * K) where n is the number of elements in arr and   K is the target sum
// Space Complexity: O(n * K) + O(n) for the recursion stack

class Solution2 {
    // Modulus value to avoid overflow in calculations.
    int mod = (int)1e9 + 7;

    /* Recursive function to count the number of subsets
       with a given target sum. Uses memoization to store results
       of subproblems to avoid redundant computations. */
    int countPartitionsUtil(int ind, int target, int[] arr, int[][] dp) {
        // Base case: If we are at the first element.
        if (ind == 0) {
            /* If target is 0 and the element is also 0, there 
            are 2 ways to achieve this (include or exclude). */
            if (target == 0 && arr[0] == 0)
                return 2;

            /* If target is 0 or the element is equal to 
            target, there is 1 way to achieve this. */
            if (target == 0 || target == arr[0])
                return 1;

            return 0;
        }

        // Return the result if it has already been computed.
        if (dp[ind][target] != -1)
            return dp[ind][target];

        // Calculate number of ways not including current element.
        int notTaken = countPartitionsUtil(ind - 1, target, arr, dp);

        /* Calculate the number of ways including the
        current element (if it can be included). */
        int taken = 0;
        if (arr[ind] <= target)
            taken = countPartitionsUtil(ind - 1, target - arr[ind], arr, dp);

        // Store and return the result for current subproblem.
        return dp[ind][target] = (notTaken + taken) % mod;
    }

    /* Function to count number of subsets with a given
    difference.Uses the helper function `countPartitionsUtil`
    to find the number of subsets with a specific target sum. */
    int countPartitions(int n, int diff, int[] arr) {
        int totSum = 0;

        // Calculate the total sum of elements in the array.
        for (int i = 0; i < arr.length; i++) {
            totSum += arr[i];
        }

        /* If the total sum minus the difference is negative,
           or if it is not even, it's not possible to divide 
           the array into two subsets with the given difference. */
        if (totSum - diff < 0) return 0;
        if ((totSum - diff) % 2 == 1) return 0;

        // Calculate the target sum for one of the subsets.
        int s2 = (totSum - diff) / 2;

        // Initialize the DP table with -1 for memoization.
        int[][] dp = new int[n][s2 + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        /* Call the helper function to count
        the number of subsets with sum s2. */
        return countPartitionsUtil(n - 1, s2, arr, dp);
    }
}

// tabulation approach
// Time Complexity: O(n * K) where n is the number of elements in arr and  K is the target sum
// Space Complexity: O(n * K) where K is the target sum



// https://www.codingninjas.com/codestudio/problems/count-partitions-with-given-difference_3751503?leftPanelTab=0
// Time Complexity: O(n * K) where n is the number of elements in arr and   K is the target sum
// Space Complexity: O(K) where K is the target sum
// most optimal solution using space optimization approach
class Solution {
    private static final int mod = 1000000007;

    public int perfectSum(int[] arr, int K) {
      int n = arr.length;
      int[] dp = new int[K + 1];
      dp[0] = 1;
      if (arr[0] == 0) {
        dp[arr[0]] = 2; 
      } else if (arr[0] <= K) {
        dp[arr[0]] = 1;
      }

      for (int i = 1; i < n; i++) {
        int[] temp = new int[K + 1];
        for (int j = 0; j <= K; j++) {
          int notPick = dp[j];
          int pick = 0;
          if (arr[i] <= j) pick = dp[j - arr[i]];
          temp[j] = (pick + notPick) % mod;
        }
        dp = temp;
      }

      return dp[K];
    }

    public int countPartitions(int n, int diff, int[] arr) {
      int sum = 0;
      for (int num : arr) sum += num;
      if (((sum - diff) < 0) || (((sum - diff) & 1) == 1)) return 0;
      return perfectSum(arr, (sum - diff) / 2);
    }
}
