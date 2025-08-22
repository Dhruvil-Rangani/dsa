import java.util.Arrays;
// LeetCode Problem: Unique Paths II
// This problem requires us to find the number of unique paths from the top-left to the bottom-right
// of a grid with obstacles. We can use dynamic programming to solve this problem.
// Time Complexity: O(M * N), where M is the number of rows and N is the number of columns in the grid.
// Space Complexity: O(M * N) for the DP table.
// Note: The grid is represented as a 2D array where 1 represents an obstacle and 0 represents a free cell. 

// This is memoization approach to solve the problem.
class Solution {
    private int func(int row, int col, int[][] dp, int[][] matrix) {
        if(row < 0 || col < 0) return 0;
        if(matrix[row][col] == 1) return 0;
        if(row == 0 && col == 0) return 1;
        if(dp[row][col] != -1) return dp[row][col];

        int top = func(row - 1, col, dp, matrix);
        int left = func(row, col - 1, dp, matrix);

        return dp[row][col] = top + left;
    }
    public int uniquePathsWithObstacles(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for(int[] row: dp) Arrays.fill(row, -1);
        return func(m - 1, n - 1, dp, matrix);
    }
}

// This is tabulation approach to solve the problem.
