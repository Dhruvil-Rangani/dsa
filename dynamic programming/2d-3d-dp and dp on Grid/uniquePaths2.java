import java.util.Arrays;
// LeetCode Problem: Unique Paths II
// This problem requires us to find the number of unique paths from the top-left to the bottom-right
// of a grid with obstacles. We can use dynamic programming to solve this problem.
// Time Complexity: O(M * N), where M is the number of rows and N is the number of columns in the grid.
// Space Complexity: O(M * N) for the DP table and O(M + N) for the memoization stack space.
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
// It uses a bottom-up dynamic programming approach to fill the DP table iteratively.
// The DP table is filled based on the number of unique paths to each cell, considering obstacles.
// The first cell is initialized to 1 if it is not an obstacle, and the rest are filled based on the values from the top and left cells.
// The final result is found in the bottom-right cell of the DP table.
// Time Complexity: O(M * N), where M is the number of rows and N is the number of columns in the grid.
// Space Complexity: O(M * N) for the DP table.
// By implementing the tabulation approach, we avoid the overhead of recursion and memoization, making it more efficient for larger grids.
// This approach is iterative and fills the DP table in a bottom-up manner.

class Solution2 {
    public int uniquePathsWithObstacles(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // edge case if 1st cell is 1
        if(matrix[0][0] == 1) return 0;

        int[][] dp = new int[m][n];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    if(i == 0 && j == 0) {
                        dp[i][j] = 1;
                        continue;
                    }
                    int up = i > 0 ? dp[i - 1][j] : 0;
                    int left = j > 0 ? dp[i][j - 1] : 0;

                    dp[i][j] = up + left;
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}


// This is space optimized approach to solve the problem.
// It reduces the space complexity by using a single array to store the current row's results.
// The previous row's results are stored in the same array, which is updated iteratively.
// Time Complexity: O(M * N), where M is the number of rows and N is the number of columns in the grid.
// Space Complexity: O(N) for the DP array.
// This approach is particularly useful for large grids where space efficiency is crucial.
// It maintains the same time complexity while significantly reducing the space used.
// The final result is found in the last element of the DP array after processing all rows.
// This approach is iterative and fills the DP array in a bottom-up manner.
// By implementing the space-optimized approach, we achieve better space efficiency without sacrificing performance.
// In large scale applications, for example in robotics or game development, where memory usage is critical, this approach is highly beneficial.
// It is also useful in scenarios where the grid size is dynamic and can grow significantly, making traditional DP approaches less feasible due to memory constraints.
// Example of large scale applications and companies that might benefit from this approach include robotics, game development, and real-time simulations where memory efficiency is crucial.
// companies like Google, Amazon, and Microsoft often deal with large datasets and require efficient algorithms to handle grid-based problems in their applications.
// even growing startups in the filed of AI and machine learning, where large datasets are common, can benefit from such space-optimized algorithms.
// For exmaple: OpenAI, DeepMind, and other AI research organizations where sudden spikes in data processing can occur, making space efficiency a priority.
class Solution3 {
    public int uniquePathsWithObstacles(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // edge case if 1st cell is 1
        if(matrix[0][0] == 1) return 0;

        int[] dp = new int[n];
        
        for(int i = 0; i < m; i++) {
            int[] temp = new int[n];
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 1) {
                    temp[j] = 0;
                } else {
                    if(i == 0 && j == 0) {
                        temp[j] = 1;
                        continue;
                    }
                    int up = i > 0 ? dp[j] : 0;
                    int left = j > 0 ? temp[j - 1] : 0;

                    temp[j] = up + left;
                }
            }
            dp = temp;
        }

        return dp[n - 1];
    }
}