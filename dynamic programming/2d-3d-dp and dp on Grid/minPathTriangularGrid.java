// https://leetcode.com/problems/triangle/description/
// TC: O(2^(n^2)), SC: O(n)
// Given a triangular grid of integers, find the minimum path sum from top to bottom.
// Each step you may move to adjacent numbers on the row below.
import java.util.*;
class Solution1 {
    private int func(int row, int col, int[][] triangle) {
        if(row == 0 && col == 0) return triangle[row][col];
        if(row < 0 || col < 0) return Integer.MIN_VALUE;

        int topLeft = triangle[row][col] + func(row - 1, col - 1, triangle);
        int top = triangle[row][col] + func(row - 1, col, triangle);

        int minSum = Math.min(topLeft, top);
        return minSum == Integer.MIN_VALUE ? Integer.MIN_VALUE : minSum;
    }
    public int minTriangleSum(int[][] triangle) {
        int n = triangle.length;
        return func(n - 1, n - 1, triangle);
    }
}

// This is memoization approach to solve the problem.
// It uses a recursive function with memoization to avoid redundant calculations.
// The time complexity is reduced to O(n^2) where n is the number of rows in the triangle.
// The space complexity is O(n^2) for the DP table and O(n) for the memoization stack space.
// Overall space complexity is O(n^2).

class Solution2 {
    private int func(int row, int col, int[][] triangle, int[][] dp) {
        int n = triangle.length;
        if(dp[row][col] != -1) return dp[row][col];
        if(row == n - 1) return triangle[n - 1][col];

        int bottom = triangle[row][col] + func(row + 1, col, triangle, dp);
        int bottomRight = triangle[row][col] + func(row + 1, col + 1, triangle, dp);

        int minSum = Math.min(bottomRight, bottom);
        return dp[row][col] = minSum;
    }
    public int minTriangleSum(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];
        for(int[] row : dp) Arrays.fill(row, -1);
        return func(0, 0, triangle, dp);
    }
}

// This is tabulation approach to solve the problem.
// The time complexity is O(n^2) where n is the number of rows in the triangle.
// The space complexity is O(n^2) for the DP table.
class Solution {
    public int minTriangleSum(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];
        
        for(int k = 0; k < n; k++) {
            dp[n - 1][k] = triangle[n - 1][k];
            for(int i = n - 2; i >= 0; i--) {
                for(int j = i; j >= 0; j--) {
                    int d = triangle[i][j] + dp[i + 1][j];
                    int dg = triangle[i][j] + dp[i + 1][j + 1];

                    dp[i][j] = Math.min(d, dg);
                }
            }
        }

        return dp[0][0];
    }
}

// This is space optimized tabulation approach to solve the problem.
// The time complexity is O(n^2) where n is the number of rows in the triangle.
// The space complexity is O(n) for the DP table.

class Solution3 {
    public int minTriangleSum(int[][] triangle) {
        int n = triangle.length;
        int[] dp = new int[n];
        
        for(int k = 0; k < n; k++) {
            dp[k] = triangle[n - 1][k];
        }

        for(int i = n - 2; i >= 0; i--) {
            int[] temp = new int[i + 1];
            for(int j = i; j >= 0; j--) {
                int d = triangle[i][j] + dp[j];
                int dg = triangle[i][j] + dp[j + 1];

                temp[j] = Math.min(d, dg);
            }
            dp = temp;
        }

        return dp[0];
    }
}