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
class Solution3 {
    // Modulus value to avoid overflow in calculations.
    private static final int MOD = (int)1e9 + 7;

    /* Function to calculate the number of ways to
    achieve a target sum with the given numbers.*/
    private int findWays(int[] num, int tar) {
        int n = num.length;
        /* DP table where dp[i][j] represents the number
        of ways to get sum j using the first i elements.*/
        int[][] dp = new int[n][tar + 1];
    
        /* If the first element is 0, we have 2 
        ways to achieve sum 0: by either including
        or excluding the element.*/
        if (num[0] == 0) dp[0][0] = 2;  
        else dp[0][0] = 1;  
    
        /* If the first element is not 0 and is less
        than or equal to target, we have 1 way to 
        achieve the sum equal to that element.*/
        if (num[0] != 0 && num[0] <= tar) dp[0][num[0]] = 1;  
    
        // Fill the DP table for the rest of the elements.
        for (int ind = 1; ind < n; ind++) {
            for (int target = 0; target <= tar; target++) {
                
                /* Number of ways to achieve target sum 
                without using the current element.*/
                int notTaken = dp[ind - 1][target];
    
                /* Number of ways to achieve target
                sum using the current element.*/
                int taken = 0;
                if (num[ind] <= target)
                    taken = dp[ind - 1][target - num[ind]];
        
                /* Total ways to achieve target sum with
                or without including the current element.*/
                dp[ind][target] = (notTaken + taken) % MOD;
            }
        }
        /* Return the number of ways to achieve
        the target sum using all elements.*/
        return dp[n - 1][tar];
    }

    /* Function to count the number of
    subsets with a given difference.*/
    public int countPartitions(int n, int diff, int[] arr) {
        int totSum = 0;
        
        // Calculate the total sum of elements in the array.
        for (int i = 0; i < n; i++) {
            totSum += arr[i];
        }
    
        /* If the difference is more than the total sum or
        if the difference is not even (as (totalSum - diff)
        must be even to divide into two subsets), return 0.*/
        if (totSum - diff < 0 || (totSum - diff) % 2 != 0) return 0;
    
        /* Call the helper function to find the number 
        of subsets with sum (totSum - diff) / 2.*/
        return findWays(arr, (totSum - diff) / 2);
    }
}


// https://www.codingninjas.com/codestudio/problems/count-partitions-with-given-difference_3751503?leftPanelTab=0
// Time Complexity: O(n * K) where n is the number of elements in arr and   K is the target sum
// Space Complexity: O(K) where K is the target sum
// most optimal solution using space optimization approach
class Solution4 {
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
