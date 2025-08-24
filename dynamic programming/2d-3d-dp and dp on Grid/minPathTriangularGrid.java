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

