#include <vector>
#include <string>
#include <algorithm>
using namespace std;
// TC: O(n*m) SC: O(n*m)
// SC: O(n*m) can be reduced to O(min(n,m))

class Solution {
public:
    string shortestCommonSupersequence(string str1, string str2) {
        int n = str1.size();
        int m = str2.size();

        // Create a DP table with dimensions (n+1) x (m+1) initialized to 0
        vector<vector<int>> dp(n + 1, vector<int>(m + 1, 0));

        // Initialize the first row and column of the DP table
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1[i - 1] == str2[j - 1])
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = max(dp[i][j - 1], dp[i - 1][j]);
            }
        }

        string ans = "";
        int i = n, j = m;
        while(i > 0 && j > 0) {
            if(str1[i - 1] == str2[j - 1]){
                ans += str1[i - 1];
                i--, j--;
            } else if(dp[i - 1][j] > dp[i][j - 1]) {
                ans += str1[i - 1];
                i--;
            } else {
                ans += str2[j - 1];
                j--;
            }
        }

        while(i > 0) {
            ans += str1[i - 1];
            i--;
        }
        while(j > 0) {
            ans += str2[j - 1];
            j--;
        }

        reverse(ans.begin(), ans.end());
        return ans;
    }
};