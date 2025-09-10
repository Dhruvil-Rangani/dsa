#include <vector>
#include <string>
#include <algorithm>
using namespace std;
// recursive approach
// TC: O(2^n) SC: O(n+m) for recursion stack

class Solution{
private:
  const int mod = 1e9 + 7;
  int f(int i, int j, string s, string t) {
    if(j < 0) return 1;
    if(i < 0) return 0;

    int result = 0;
    if(s[i] == t[j]) {
      int pick = f(i - 1, j - 1, s, t);
      int notPick = f(i - 1, j, s, t);
      result = (pick + notPick) % mod;
    } else {
      result = f(i - 1, j, s, t);
    }

    return result;
  }
public: 
  int distinctSubsequences(string s, string t){
     int n = s.size();
     int m = t.size();

     return f(n - 1, m - 1, s, t);
  }
};

// memoization approach
// TC: O(n*m) SC: O(n*m) + O(n+m) for recursion stack

class Solution2{
private:
  const int mod = 1e9 + 7;
  int f(int i, int j, string s, string t, vector<vector<int>>& dp) {
    if(j < 0) return 1;
    if(i < 0) return 0;

    if(dp[i][j] != -1) return dp[i][j];
    int result = 0;
    if(s[i] == t[j]) {
      int pick = f(i - 1, j - 1, s, t, dp);
      int notPick = f(i - 1, j, s, t, dp);
      result = (pick + notPick) % mod;
    } else {
      result = f(i - 1, j, s, t, dp);
    }

    return dp[i][j] = result;
  }
public: 
  int distinctSubsequences(string s, string t){
     int n = s.size();
     int m = t.size();
     vector<vector<int>> dp(n, vector<int>(m, -1));
     return f(n - 1, m - 1, s, t, dp);
  }
};

// tabulation approach
// TC: O(n*m) SC: O(n*m)

class Solution3 {
private:
    const int mod = 1e9 + 7;
public:
    int distinctSubsequences(string s, string t) {
        int n = s.size();
        int m = t.size();
        vector<vector<int>> dp(n + 1, vector<int>(m + 1, 0));
        for(int i = 0; i <= n; i++) dp[i][0] = 1;

       for(int i = 1; i <= n; i++) {
        for(int j = 1; j <= m; j++) {
          if(s[i - 1] == t[j - 1]) {
            int pick = dp[i -1][j - 1];
            int notPick = dp[i - 1][j];
            dp[i][j] = (pick + notPick) % mod;
          } else {
            dp[i][j] = dp[i - 1][j];
          }
        }
       }

        return dp[n][m];
    }
};

// space optimized approach
// TC: O(n*m) SC: O(m)
// 1D array can be used instead of 2D array because we are only using the previous row values
class Solution4 {
private:
    const int mod = 1e9 + 7;

public:
    int numDistinct(string s, string t) {
        int n = s.size();
        int m = t.size();
        vector<int> dp(m + 1, 0);
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 1; j--) {
                if (s[i - 1] == t[j - 1]) dp[j] = (dp[j - 1] + dp[j]) % mod;
            }
        }

        return dp[m];
    }
};