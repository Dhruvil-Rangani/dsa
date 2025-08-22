import java.util.*;
// Unique Paths - Dynamic Programming
// This is a solution for the Unique Paths problem using dynamic programming.
// The problem is to find the number of unique paths from the top-left corner to the bottom-right corner of a grid,
// where you can only move either down or right at any point in time.
// The code below implements a recursive approach with memoization to solve the problem.
// Time Complexity: O(m * n) where m is the number of rows and n is the number of columns
// Space Complexity: O(m * n) for the memoization table
// The memoization table is of size m x n, where m is the number of rows and n is the number of columns.
// The recursive function calculates the number of unique paths to reach the cell (row, col) from the top-left corner (0, 0).
// The base case is when the cell is at the top-left corner (0, 0), where there is only one unique path.
// The edge case is when the cell is out of bounds (row < 0 or col < 0), where there are no unique paths.
// The recursive function explores two possible moves: moving down (to the next row) or moving
// right (to the next column) and sums the unique paths from both moves.
// The memoization approach reduces the time complexity to O(m * n) by storing results of subproblems in a table,
// avoiding redundant calculations.
// without memoization, the time complexity would be O(2^(m+n)), as each cell can lead to two recursive calls (down and right),
// leading to an exponential number of combinations.
// and space complexity without memoization is O(m * n) for the recursion stack space
class Solution1 {
     public int func(int row, int col, int[][] dp) {
        // base case
        if(row == 0 && col == 0) return 1;
        // edge case
        if(row < 0 || col < 0) return 0;

        if(dp[row][col] != -1) return dp[row][col];

        int top = func(row - 1, col, dp);
        int left = func(row, col - 1, dp);

        return dp[row][col] = top + left;
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int[] row : dp) Arrays.fill(row, -1);

        return func(m - 1, n - 1, dp);
    }
}

