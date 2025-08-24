import java.util.Arrays;
// This is memoization approach to solve the problem.
// It uses a recursive function with memoization to avoid redundant calculations.
// The time complexity is reduced to O(n*m) where n is the number of rows and m is the number of columns in the matrix.
// The space complexity is O(n*m) for the DP table and O(n) for the memoization stack space.
// Overall space complexity is O(n*m).
// This problem is similar to the triangle minimum path sum problem.
// The only difference is that in triangle we can only go to the next row and either same column or next column.
// In this problem we can go to the next row and either same column, left column or right column.
// Hence we need to check for all three directions.
// The base case is when we reach the last row, we return the value of that cell.
// We start from the first row and try to reach the last row by moving in all three directions and take the minimum path sum.
// We need to call the recursive function for all columns in the first row and take the minimum path sum.
// The final answer is the minimum path sum from the first row to the last row.

class Solution {
  private int func(int i, int j, int n, int m, int[][] matrix, int[][] dp) {
    if (j >= m || j < 0) return (int) (1e9);
    if (i == n - 1) return matrix[i][j];
    if (dp[i][j] != -1) return dp[i][j];

    int min = (int)(1e9);
    int[] dirs = {-1, 0, 1};
    for (int d : dirs) {
      int next = func(i + 1, j + d, n, m, matrix, dp);
      if (next != (int)(1e9)) {
        min = Math.min(min, matrix[i][j] + next);
      }
    }
    return dp[i][j] = min;
  }

  public int minFallingPathSum(int[][] matrix) {
    int n = matrix.length;
    int m = matrix[0].length;
    int minSum = (int) (1e9);
    int[][] dp = new int[n][m];
    for (int[] row : dp) Arrays.fill(row, -1);
    for (int j = 0; j < m; j++) {
      minSum = Math.min(minSum, func(0, j, n, m, matrix, dp));
    }
    return minSum;
  }
}

// This is tabulation approach to solve the problem.
// The time complexity is O(n*m) where n is the number of rows and m is the number of columns in the matrix.
// The space complexity is O(n*m) for the DP table.
class Solution2 {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int minSum = (int) (1e9);
        int[][] dp = new int[n][m];
        for (int j = 0; j < m; j++) {
            dp[0][j] = matrix[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int bottom = dp[i - 1][j];
                int bottomRight = j < m - 1 ? dp[i - 1][j + 1] : (int) (1e9);
                int bottomLeft = j > 0 ? dp[i - 1][j - 1] : (int) (1e9);

                dp[i][j] = matrix[i][j] + Math.min(bottom, Math.min(bottomLeft, bottomRight));
            }
        }

        for (int j = 0; j < m; j++) {
            minSum = Math.min(minSum, dp[n - 1][j]);
        }

        return minSum;
    }
}

