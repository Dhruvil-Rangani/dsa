import java.util.*;
// Longest Common Subsequence
// TC: >> O(2^(N+M))
// SC: >> O(N+M) for recursion stack
// TC and SC are not exactly this, it is going to be more than this, but this is the upper bound.
// because we are making two calls at each step, so it is going to be more than this.
// but this is not the optimal solution, we can do better than this using memoization or tabulation.
// this is just to understand the problem and the recursive approach.

class Solution {
  private int f(String str1, String str2, int i, int j) {
    if(i < 0 || j < 0) return 0;

    if(str1.charAt(i) == str2.charAt(j))
      return 1 + f(str1, str2, i - 1, j - 1);
    else
      return Math.max(f(str1, str2, i, j - 1), f(str1, str2, i - 1, j));
  }

  public int lcs(String str1, String str2) {
    int n = str1.length();
    int m = str2.length();
    return f(str1, str2, n - 1, m - 1);
  }
}

// memoization approach
// TC: O(N * M)
// SC: O(N * M) + O(N + M) for recursion stack

class Solution2 {
  private int f(int[][] dp, String str1, String str2, int i, int j) {
    if(i < 0 || j < 0) return 0;
    if(dp[i][j] != -1) return dp[i][j];

    if(str1.charAt(i) == str2.charAt(j))
      return dp[i][j] = 1 + f(dp, str1, str2, i - 1, j - 1);
    else
      return dp[i][j] = Math.max(f(dp, str1, str2, i, j - 1), f(dp, str1, str2, i - 1, j));
  }

  public int lcs(String str1, String str2) {
    int n = str1.length();
    int m = str2.length();
    int[][] dp = new int[n][m];
    for (int[] row : dp) Arrays.fill(row, -1);
    return f(dp, str1, str2, n - 1, m - 1);
  }
}

// tabulation approach
// TC: O(N * M)
// SC: O(N * M)

class Solution3 {
  public int lcs(String str1, String str2) {
    int n = str1.length();
    int m = str2.length();
    int[][] dp = new int[n + 1][m + 1];

    for(int i = 1; i <= n; i++) {
      for(int j = 1; j <= m; j++){
        if(str1.charAt(i - 1) == str2.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
        else dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
      }
    }

    return dp[n][m];
  }
}

// space optimization
// TC: O(N * M)
// SC: O(M)
class Solution4 {
  public int lcs(String str1, String str2) {
    int n = str1.length();
    int m = str2.length();
    int[] dp = new int[m + 1];

    for(int i = 1; i <= n; i++) {
      int prev = 0;
      for(int j = 1; j <= m; j++){
        int temp = dp[j];
        if(str1.charAt(i - 1) == str2.charAt(j - 1)) dp[j] = 1 + prev;
        else dp[j] = Math.max(dp[j - 1], dp[j]);
        prev = temp;
      }
    }

    return dp[m];
  }
}



