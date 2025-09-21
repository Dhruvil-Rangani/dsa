#include <bits/stdc++.h>
using namespace std;

// recursive memoization approach
// TC: O(n^3)
// SC: O(n^2) + O(n) for recursion stack

class Solution {
private:
    int mod = 1e9 + 7;
    long long f(int i, int j, int isTrue, string& s, vector<vector<vector<long long>>>& dp) {
        if(i > j) return 0;
        if(i == j) {
            if(isTrue == 1) return s[i] == 'T';
            else return s[i] == 'F';
        }
        if(dp[i][j][isTrue] != -1) return dp[i][j][isTrue];
        
        long long ways = 0;
        for(int k = i + 1; k < j; k += 2) {
            long long lT = f(i, k - 1, 1, s, dp);
            long long lF = f(i, k - 1, 0, s, dp);
            long long rT = f(k + 1, j, 1, s, dp);
            long long rF = f(k + 1, j, 0, s, dp);
            if(s[k] == '&') {
                if(isTrue) ways = (ways + (rT * lT) % mod) % mod;
                else ways = (ways + (lF * rT) % mod + (lT * rF) % mod + (lF * rF) % mod) % mod;
            } else if(s[k] == '|') {
                if(isTrue) ways = (ways + (lT * rT) % mod + (lT * rF) % mod + (lF * rT) % mod) % mod;
                else ways = (ways + (rF * lF) % mod) % mod;
            } else {
                if(isTrue) ways = (ways + (rF * lT) % mod + (lF * rT) % mod) % mod;
                else ways = (ways + (rT * lT) % mod + (lF * rF) % mod) % mod;
            }
        }

        return dp[i][j][isTrue] = ways;
    }

public:
    int countTrue(string s) {
        int n = s.size();
        vector<vector<vector<long long>>> dp(n, vector<vector<long long>>(n, vector<long long>(2, -1)));

        return f(0, n - 1, 1, s, dp);
    }
};

// tabulation approach
// TC: O(n^3)
// SC: O(n^2)

class Solution {
   private:
    int mod = 1e9 + 7;

   public:
    int countTrue(string s) {
        int n = s.size();
        vector<vector<vector<long long>>> dp(
            n, vector<vector<long long>>(n, vector<long long>(2, 0)));

        for (int i = 0; i < n;
             i += 2) {  // Only process T/F characters, skip operators
            if (s[i] == 'T') {
                dp[i][i][1] = 1;  // True case
                dp[i][i][0] = 0;  // False case
            } else {              // s[i] == 'F'
                dp[i][i][1] = 0;  // True case
                dp[i][i][0] = 1;  // False case
            }
        }

        for (int len = 3; len <= n; len += 2) {
            for (int i = 0; i <= n - len; i += 2) {  // Start positions
                int j = i + len - 1;

                // Try all possible split points (operators)
                for (int k = i + 1; k < j; k += 2) {
                    long long lT = dp[i][k - 1][1];  // Left side true
                    long long lF = dp[i][k - 1][0];  // Left side false
                    long long rT = dp[k + 1][j][1];  // Right side true
                    long long rF = dp[k + 1][j][0];  // Right side false

                    if (s[k] == '&') {
                        // AND operation
                        dp[i][j][1] = (dp[i][j][1] + (lT * rT) % mod) % mod;
                        dp[i][j][0] = (dp[i][j][0] + (lF * rT) % mod +
                                       (lT * rF) % mod + (lF * rF) % mod) %
                                      mod;
                    } else if (s[k] == '|') {
                        // OR operation
                        dp[i][j][1] = (dp[i][j][1] + (lT * rT) % mod +
                                       (lT * rF) % mod + (lF * rT) % mod) %
                                      mod;
                        dp[i][j][0] = (dp[i][j][0] + (lF * rF) % mod) % mod;
                    } else {
                        // XOR operation
                        dp[i][j][1] =
                            (dp[i][j][1] + (lT * rF) % mod + (lF * rT) % mod) %
                            mod;
                        dp[i][j][0] =
                            (dp[i][j][0] + (lT * rT) % mod + (lF * rF) % mod) %
                            mod;
                    }
                }
            }
        }

        return dp[0][n - 1][1];
    }
};
