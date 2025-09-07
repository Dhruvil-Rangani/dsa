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
