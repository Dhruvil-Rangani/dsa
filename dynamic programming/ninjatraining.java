import java.util.*;
// Ninja Training - Dynamic Programming
// This is a solution for the Ninja Training problem using dynamic programming.
// The problem is to maximize the points a ninja can earn by training on different activities each day,
// ensuring that the same activity is not performed on consecutive days.
// The code below implements a recursive approach with memoization to solve the problem.
// Time Complexity: O(n * 4) where n is the number of days
// Space Complexity: O(n * 4) for the memoization table
// Note: The last parameter in the recursion function is used to track the last activity performed.
// The ninja can choose from three activities each day, and the last activity is represented by an integer (0, 1, or 2).
// The ninja can also skip a day, represented by the integer 3.
class Solution {
    private int recursion(int day, int last, int[][] matrix, int[][] dp) {
        // memoization
        if(dp[day][last] != -1) return dp[day][last];

        // base case
        if(day == 0) {
            int max = 0;
            for(int i = 0; i < 3; i++) {
                if(i != last) max = Math.max(max, matrix[0][i]);
            }
            return dp[day][last] = max;
        }

        int max = 0;
        for(int i = 0; i < 3; i++) {
            if(i != last) {
                int points = matrix[day][i] + recursion(day - 1, i, matrix, dp);
                max = Math.max(points, max);
            }
        }

        return dp[day][last] = max;
    }

    public int ninjaTraining(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][4];
        for(int[] row : dp) Arrays.fill(row, -1);

        return recursion(n - 1, 3, matrix, dp);
    }
}

// tabulation approach
// Time Complexity: O(n * 4) where n is the number of days
// Space Complexity: O(n * 4) for the dp table
class Solution2 {
    public int ninjaTraining(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][4];
        for(int[] row : dp) Arrays.fill(row, -1);

        dp[0][0] = Math.max(matrix[0][1], matrix[0][2]);
        dp[0][1] = Math.max(matrix[0][0], matrix[0][2]);
        dp[0][2] = Math.max(matrix[0][0], matrix[0][1]);
        dp[0][3] = Math.max(matrix[0][0], Math.max(matrix[0][1], matrix[0][2]));
        
        for(int i = 1; i < n; i++) {
            for(int last = 0; last < 4; last++) {
                dp[i][last] = 0;
                for(int task = 0; task <= 2; task++) {
                    if(task != last) {
                        int points = matrix[i][task] + dp[i - 1][task];
                        dp[i][last] = Math.max(dp[i][last], points);
                    }
                }
            }
        }

        return dp[n - 1][3];
    }
}

// Space Optimized Approach
// Time Complexity: O(n * 4) where n is the number of days
// Space Complexity: O(4) for the dp array
class Solution3 {
  public int ninjaTraining(int[][] matrix) {
    int n = matrix.length;
    int[] dp = new int[4];
    Arrays.fill(dp, -1);

    for(int last = 0; last < 4; last++) {
        dp[last] = 0;
        for(int task = 0; task < 3; task++) {
            if(last != task) {
                dp[last] = Math.max(dp[last], matrix[0][task]);
            }
        }
    }

    for(int day = 1; day < n; day++) {
        int[] temp = new int[4];
        for(int last = 0; last < 4; last++) {
            temp[last] = 0;
            for(int task = 0; task < 3; task++) {
                if(last != task) {
                    int points = matrix[day][task] + dp[task];
                    temp[last] = Math.max(temp[last], points);
                }
            }
        }
        dp = temp;
    }

    return dp[3];
  }
}


