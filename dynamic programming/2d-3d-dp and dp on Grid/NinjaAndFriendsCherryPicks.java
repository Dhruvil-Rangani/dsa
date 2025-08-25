import java.util.*;
// Ninja has to collect maximum number of chocolates while moving from top to bottom in a grid.
// He starts from (0,0) and his friend starts from (0,m-1).
// Both can move to (i+1,j-1), (i+1,j), (i+1,j+1) from (i,j).
// If both land on the same cell, only one of them can pick the chocolates from that cell.
// Find the maximum number of chocolates they can collect.
// Note: Both friends cannot go out of the grid.
// Time complexity is O(n*m*m*9) where n is the number of rows and m is the number of columns in the grid.
// Space complexity is O(n) for the recursion stack.
class Solution {
 private int func(int i, int j1, int j2, int[][] g, int n, int m) {
    if(j1 < 0 || j1 >= m || j2 < 0 || j2 >= m) return (int)(-1e9);
    if(i == n - 1) {
        return j1 == j2 ? g[i][j1] : g[i][j1] + g[i][j2];
    }
    int max = (int)(-1e9);
    int[] dir = {-1 , 0, 1};
    for(int d1 = 0; d1 < 3; d1++) {
        for(int d2 = 0; d2 < 3; d2++) {
             int sum = j1 == j2 ? g[i][j1] : g[i][j1] + g[i][j2];
             sum += func(i + 1, j1 + dir[d1], j2 + dir[d2], g, n, m);
             max = Math.max(max, sum);
        }
    }
    return max;
 }
    public int maxChocolates(int[][] g) {
        int n = g.length;
        int m = g[0].length;


        return func(0, 0, m - 1, g, n, m);
    }
}

// This is memoization approach to solve the problem.
// It uses a recursive function with memoization to avoid redundant calculations.
// The time complexity is reduced to O(n*m*m) where n is the number of rows and m is the number of columns in the grid.
// The space complexity is O(n*m*m) for the DP table and O(n) for the memoization stack space.
// Overall space complexity is O(n*m*m).
// We need a 3D DP table because we have three changing variables: i, j1, and j2.
// i represents the current row, j1 represents the column of the first friend, and j2 represents the column of the second friend.
class Solution2 {
  private int func(int i, int j1, int j2, int[][] g, int n, int m, int[][][] dp) {
    if (j1 < 0 || j1 >= m || j2 < 0 || j2 >= m) return (int) (-1e9);
    if (i == n - 1) {
      return j1 == j2 ? g[i][j1] : g[i][j1] + g[i][j2];
    }
    if (dp[i][j1][j2] != -1) return dp[i][j1][j2];

    int max = (int) (-1e9);
    int[] dir = {-1, 0, 1};
    for (int d1 = 0; d1 < 3; d1++) {
      for (int d2 = 0; d2 < 3; d2++) {
        int sum = j1 == j2 ? g[i][j1] : g[i][j1] + g[i][j2];
        sum += func(i + 1, j1 + dir[d1], j2 + dir[d2], g, n, m, dp);
        max = Math.max(max, sum);
      }
    }

    return dp[i][j1][j2] = max;
  }

  public int maxChocolates(int[][] g) {
    int n = g.length;
    int m = g[0].length;
    int[][][] dp = new int[n][m][m];
    for (int[][] mat : dp) {
      for (int[] row : mat) Arrays.fill(row, -1);
    }

    return func(0, 0, m - 1, g, n, m, dp);
  }
}

// This is tabulation approach to solve the problem.
// The time complexity is O(n*m*m*9) where n is the number of rows and m is the number of columns in the grid.
// The space complexity is O(n*m*m) for the DP table.

class Solution3 {
  public int maxChocolates(int[][] g) {
    int n = g.length;
    int m = g[0].length;
    int[][][] dp = new int[n][m][m];
    for (int j1 = 0; j1 < m; j1++) {
      for (int j2 = 0; j2 < m; j2++) {
        dp[n - 1][j1][j2] = j1 == j2 ? g[n - 1][j1] : g[n - 1][j1] + g[n - 1][j2];
      }
    }

    for (int i = n - 2; i >= 0; i--) {
      for (int j1 = 0; j1 < m; j1++) {
        for (int j2 = 0; j2 < m; j2++) {
          int max = (int) (-1e9);
          int[] dir = {-1, 0, 1};
          for (int d1 = 0; d1 < 3; d1++) {
            for (int d2 = 0; d2 < 3; d2++) {
              int sum = j1 == j2 ? g[i][j1] : g[i][j1] + g[i][j2];
              if(j1 + dir[d1] >= 0 && j1 + dir[d1] < m && j2 + dir[d2] >= 0 && j2 + dir[d2] < m) {
                sum += dp[i + 1][j1 + dir[d1]][j2 + dir[d2]];
              } else sum = (int)(-1e9);
              max = Math.max(max, sum);
            }
          }

          dp[i][j1][j2] = max;
        }
      }
    }

    return dp[0][0][m - 1];
  }
}

// This is space optimized tabulation approach to solve the problem.
// The time complexity is O(n*m*m*9) where n is the number of rows and m is the number of columns in the grid.
// The space complexity is O(m*m) for the DP table.

class Solution4 {
  public int maxChocolates(int[][] g) {
    int n = g.length;
    int m = g[0].length;
    int[][] dp = new int[m][m];
    for (int j1 = 0; j1 < m; j1++) {
      for (int j2 = 0; j2 < m; j2++) {
        dp[j1][j2] = j1 == j2 ? g[n - 1][j1] : g[n - 1][j1] + g[n - 1][j2];
      }
    }

    for (int i = n - 2; i >= 0; i--) {
        int[][] temp = new int[m][m];
      for (int j1 = 0; j1 < m; j1++) {
        for (int j2 = 0; j2 < m; j2++) {
          int max = (int) (-1e9);
          int[] dir = {-1, 0, 1};
          for (int d1 = 0; d1 < 3; d1++) {
            for (int d2 = 0; d2 < 3; d2++) {
              int sum = j1 == j2 ? g[i][j1] : g[i][j1] + g[i][j2];
              if(j1 + dir[d1] >= 0 && j1 + dir[d1] < m && j2 + dir[d2] >= 0 && j2 + dir[d2] < m) {
                sum += dp[j1 + dir[d1]][j2 + dir[d2]];
              } else sum = (int)(-1e9);
              max = Math.max(max, sum);
            }
          }

          temp[j1][j2] = max;
        }
      }
      dp = temp;
    }

    return dp[0][m - 1];
  }
}


