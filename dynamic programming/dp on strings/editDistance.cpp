#include <vector>
#include <string>
#include <algorithm>
using namespace std;
// recursive approach
// TC: O(3^(n+m)) SC: O(n+m) for recursion stack

class Solution {
  private:
    int f(int i, int j, string start, string target) {
      if(i < 0) return j + 1;
      if(j < 0) return i + 1;
      
      if(start[i] == target[j]) return 0 + f(i - 1, j - 1, start, target);
      else {
        return 1 + min(f(i, j - 1, start, target), 
                         min(f(i - 1, j, start, target),
                              f(i -1, j - 1, start, target)));
      }
    }

  public:
    int editDistance(string start, string target) {
      int n = start.size();
      int m = target.size();

      return f(n - 1, m - 1, start, target); 
    }
};

// memoization approach
// TC: O(n*m) SC: O(n*m) + O(n+m) for recursion stack
class Solution {
  private:
    int f(int i, int j, string& start, string& target, vector<vector<int>>& dp) {
      if(i < 0) return j + 1;
      if(j < 0) return i + 1;
      if(dp[i][j] != -1) return dp[i][j];
      if(start[i] == target[j]) return dp[i][j] = 0 + f(i - 1, j - 1, start, target, dp);
      else {
        // Insert
        return dp[i][j] = 1 + min(f(i, j - 1, start, target, dp), 
                         min(f(i - 1, j, start, target, dp),
                              f(i -1, j - 1, start, target, dp)));
      }
    }

  public:
    int editDistance(string start, string target) {
      int n = start.size();
      int m = target.size();
      vector<vector<int>> dp(n, vector<int>(m, -1));
      return f(n - 1, m - 1, start, target, dp); 
    }
};

// tabulation approach
// TC: O(n*m) SC: O(n*m)

class Solution {
public:
    int minDistance(string start, string target) {
        int n = start.size();
        int m = target.size();
        vector<vector<int>> dp(n + 1, vector<int>(m + 1, 0));
        for (int i = 0; i <= n; i++)
            dp[i][0] = i;
        for (int i = 0; i <= m; i++)
            dp[0][i] = i;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (start[i - 1] == target[j - 1])
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    dp[i][j] = 1 + min(dp[i][j - 1],
                                       min(dp[i - 1][j], dp[i - 1][j - 1]));
                }
            }
        }

        return dp[n][m];
    }
};

// space optimization
// TC: O(n*m) SC: O(m)
class Solution {
public:
    int minDistance(string start, string target) {
        int n = start.size();
        int m = target.size();
        vector<int> dp(m + 1, 0);
        vector<int> temp(m + 1, 0);
        for (int i = 0; i <= m; i++)
            dp[i] = i;

        for (int i = 1; i <= n; i++) {
            temp[0] = i;
            for (int j = 1; j <= m; j++) {
                if (start[i - 1] == target[j - 1])
                    temp[j] = dp[j - 1];
                else {
                    temp[j] = 1 + min(temp[j - 1], min(dp[j], dp[j - 1]));
                }
            }
            dp = temp;
        }

        return dp[m];
    }
};

// space optimization 2 with just one array
// TC: O(n*m) SC: O(m)
// this is the most optimized approach and only using single array we optimize space complexity to O(m) and not O(2 * m);
// this is hyper optimized approach
class Solution {
public:
    int minDistance(string start, string target) {
        int n = start.size();
        int m = target.size();
        vector<int> dp(m + 1, 0);
        for (int i = 0; i <= m; i++)
            dp[i] = i;

        for (int i = 1; i <= n; i++) {
            int prev = dp[0];
            dp[0] = i;
            for (int j = 1; j <= m; j++) {
                int temp = dp[j];
                if (start[i - 1] == target[j - 1])
                    dp[j] = prev;
                else {
                    dp[j] = 1 + min(dp[j - 1], min(dp[j], prev));
                }
                prev = temp;
            }
        }

        return dp[m];
    }
};




