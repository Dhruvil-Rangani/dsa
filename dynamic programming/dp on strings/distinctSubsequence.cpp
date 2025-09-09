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