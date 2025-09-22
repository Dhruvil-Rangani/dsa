#include <bits/stdc++.h>
using namespace std;

// tabulation method
// TC: O(N*M)
// SC: O(N*M)

class Solution {
public:
    int countSquares(vector<vector<int>>& matrix) {
        int n = matrix.size();
        int m = matrix[0].size();
        vector<vector<int>> dp(n, vector<int>(m, 0));

        for(int j  = 0; j < m; j++) dp[0][j] = matrix[0][j];
        for(int i  = 0; i < n; i++) dp[i][0] = matrix[i][0];

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                if(matrix[i][j] == 0) dp[i][j] = 0;
                else dp[i][j] = min({dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]}) + 1;
            }
        }

        int ans = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                ans += dp[i][j];
            }
        }

        return ans;
    }
};


// space optimized approach
// TC: O(N*M)
// SC: O(M)

class Solution {
public:
    int countSquares(vector<vector<int>>& matrix) {
        int n = matrix.size();
        int m = matrix[0].size();
        vector<int> dp(m, 0);

        int ans = 0;
        for(int j  = 0; j < m; j++) {
            dp[j] = matrix[0][j];
            ans += dp[j];
        }

        for(int i = 1; i < n; i++) {
            int prev = dp[0];
            dp[0] = matrix[i][0];
            ans += dp[0];

            for(int j = 1; j < m; j++) {
                int temp = dp[j];
                if(matrix[i][j] == 0) dp[j] = 0;
                else dp[j] = min({dp[j - 1], temp, prev}) + 1;
                ans += dp[j];
                prev = temp;
            }
        }

        return ans;
    }
};