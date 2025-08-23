// LeetCode Problem: Minimum Path Sum
// This problem requires us to find the minimum path sum from the top-left to the bottom-right
// of a grid. We can use dynamic programming to solve this problem.
// Without memoization, the time complexity is O(2^(M + N)), which is exponential and inefficient for larger grids.
// and the space complexity is O(M + N) for the recursion stack.
import java.util.Arrays;
class Solution {
    private int func(int row, int col, int[][] grid) {
        if(row == 0 && col == 0) return grid[0][0];
        if(row < 0 || col < 0) return Integer.MAX_VALUE;

        int up = func(row - 1, col, grid);
        int left = func(row, col - 1, grid);

        int minPrev = Math.min(up, left);
        if (minPrev == Integer.MAX_VALUE) return Integer.MAX_VALUE; // no valid path

        return grid[row][col] + minPrev;
    }

    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        return func(n - 1, m - 1, grid);
    }
}

// This is memoization approach to solve the problem.
// It uses a recursive function with memoization to avoid redundant calculations.
// The time complexity is reduced to O(M * N) where M is the number of rows and N is the number of columns in the grid.
// The space complexity is O(M * N) for the DP table and O(M + N) for the memoization stack space.
// Overall space complexity is O(M * N).
// The code below implements this approach.

class Solution2 {
    private int func(int row, int col, int[][] grid, int[][] dp) {
        if(row == 0 && col == 0) return grid[0][0];
        if(row < 0 || col < 0) return Integer.MAX_VALUE;
        if(dp[row][col] != -1) return dp[row][col];

        int up = func(row - 1, col, grid, dp);
        int left = func(row, col - 1, grid, dp);

        int minPrev = Math.min(up, left);
        if (minPrev == Integer.MAX_VALUE) return Integer.MAX_VALUE; // no valid path

        return  dp[row][col] = grid[row][col] + minPrev;
    }

    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for(int[] row : dp) Arrays.fill(row, -1);

        return func(n - 1, m - 1, grid, dp);
    }
}