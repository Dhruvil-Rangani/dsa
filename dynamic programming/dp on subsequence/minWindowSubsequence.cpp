#include <vector>
#include <string>
#include <climits>
#include <algorithm>
using namespace std;
// recursive approach
// TC: O(2^(n+m)) SC: O(n+m) for recursion stack

class Solution {
private:
    int findEnd(string& s1, string& s2, int i, int j) {
        if(j == s2.size()) return i;
        if(i == s1.size()) return -1;
        if(s1[i] == s2[j]) {
            return findEnd(s1, s2, i + 1, j + 1);
        } else {
            return findEnd(s1, s2, i + 1, j);
        }
    }
public:
    string minWindow(string s1, string s2) {
        int minLen = INT_MAX, startInd = -1;
        for(int i = 0; i < s1.size(); i++) {
            if(s1[i] == s2[0]) {
                int end = findEnd(s1, s2, i, 0);
                if(end != -1 && end - i < minLen) {
                    minLen = end - i;
                    startInd = i;
                }
            }
        }

        return (startInd == -1) ? "" : s1.substr(startInd, minLen);
    }
};

// tabulation approach
// TC: O(n*m) SC: O(n*m)

class Solution {
public:
    string minWindow(string s1, string s2) {
        int n = s1.size();
        int m = s2.size();
        vector<vector<int>> dp(n + 1, vector<int>(m + 1, 1e9));
        dp[0][0] = 0;

        int end = 0, length = n + 1;

        for(int i = 1; i <= n; i++) {
            dp[i][0] = 0;
            for(int j = 1; j <= m; j++) {
                dp[i][j] = 1 + (s1[i - 1] == s2[j - 1] ? dp[i - 1][j - 1] : dp[i - 1][j]);
            }

            if(dp[i][m] < length) {
                length = dp[i][m];
                end = i;
            }
        }

        return length > n ? "" : s1.substr(end - length, length);
    }
};

// space optimization
// TC: O(n*m) SC: O(2 * m)
class Solution {
public:
    string minWindow(string s1, string s2) {
        int n = s1.size();
        int m = s2.size();
        vector<int> dp(m + 1, 1e9), cur(m + 1);
        dp[0] = 0;

        int end = 0, length = n + 1;

        for(int i = 1; i <= n; i++) {
            cur[0] = 0;
            for(int j = 1; j <= m; j++) {
                cur[j] = 1 + (s1[i - 1] == s2[j - 1] ? dp[j - 1] : dp[j]);
            }

            dp = cur;

            if(dp[m] < length) {
                length = dp[m];
                end = i;
            }
        }

        return length > n ? "" : s1.substr(end - length, length);
    }
};


