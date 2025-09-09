#include <vector>
#include <string>
#include <algorithm>
using namespace std;
// TC: O(n*m) SC: O(m)
// SC: O(m) can be reduced to O(min(n,m))

class Solution {
private:
    int lcs(string s1, string s2) {
        int n = s1.size();
        int m = s2.size();

        vector<int> dp(m + 1, 0);

        for(int i = 1; i <= n; i++) {
            int prev = 0;
            for(int j = 1; j <= m; j++) {
                int temp = dp[j];
                if(s1[i - 1] == s2[j - 1]) dp[j] = 1 + prev;
                else dp[j] = max(dp[j], dp[j - 1]);
                prev = temp;
            }
        }

        return dp[m];
    }

public : 
    int minDistance(string str1, string str2) {
        int common = lcs(str1, str2);
        int deleteOp = str1.size() - common;
        int insertOp = str2.size() - common;

        return insertOp + deleteOp;
    }
};