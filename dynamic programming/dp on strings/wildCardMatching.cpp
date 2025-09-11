#include <vector>
#include <string>
#include <algorithm>
using namespace std;
// recursive approach
// TC: O(2^(n+m)) SC: O(n+m) for recursion stack

class Solution {
private:
    bool isAllStars(string& s, int i) {
        for(int j = 0; j <= i; j++) {
            if(s[j] != '*') return false;
        }
        return true;
    }

    bool f(int i, int j, string& s, string& t) {
        if(i < 0 && j < 0) return true;
        if(i < 0 && j >= 0) return false;
        if(j < 0 && i >= 0) return isAllStars(s, i);

        if(s[i] == t[j] || s[i] == '?') {
            return f(i - 1, j - 1, s, t);
        }
        if(s[i] == '*') {
            return f(i - 1, j, s, t) || f(i, j - 1, s, t);
        }

        return false;
    }

public:
    bool wildCard(string str, string pat) {
        int n = str.size();
        int m = pat.size();

        return f(m - 1, n - 1, pat, str);
    }
};

// memoization approach
// TC: O(n*m) SC: O(n*m) + O(n+m) for recursion stack
class Solution {
private:
    bool isAllStars(string& s, int i) {
        for(int j = 0; j <= i; j++) {
            if(s[j] != '*') return false;
        }
        return true;
    }

    bool f(int i, int j, string& s, string& t, vector<vector<int>>& dp) {
        if(i < 0 && j < 0) return true;
        if(i < 0 && j >= 0) return false;
        if(j < 0 && i >= 0) return isAllStars(s, i);
        if(dp[i][j] != -1) return dp[i][j];

        if(s[i] == t[j] || s[i] == '?') {
            return dp[i][j] = f(i - 1, j - 1, s, t, dp);
        }
        if(s[i] == '*') {
            return dp[i][j] = f(i - 1, j, s, t, dp) || f(i, j - 1, s, t, dp);
        }

        return dp[i][j] = false;
    }

public:
    bool wildCard(string str, string pat) {
        int n = str.size();
        int m = pat.size();
        vector<vector<int>> dp(m, vector<int>(n, -1));
        return f(m - 1, n - 1, pat, str, dp);
    }
};

// tabulation approach
// TC: O(n*m) SC: O(n*m)
class Solution {
public:
    bool isMatch(string str, string pat) {
        int n = pat.size();
        int m = str.size();
        vector<vector<bool>> dp(n + 1, vector<bool>(m + 1, false));
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            int flag = true;
            for (int j = 0; j < i; j++) {
                if (pat[j] != '*') {
                    flag = false;
                    break;
                }
            }
            dp[i][0] = flag;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = false;
                if (pat[i - 1] == str[j - 1] || pat[i - 1] == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else if (pat[i - 1] == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }

        return dp[n][m];
    }
};

// space optimization
// TC: O(n*m) SC: O(2 * m)
class Solution {
public:
    bool isMatch(string str, string pat) {
        int n = pat.size();
        int m = str.size();
        vector<bool> dp(m + 1, false);
        vector<bool> cur(m + 1, false);
        dp[0] = true;
        cur[0] = true;

        for (int i = 1; i <= n; i++) {
            int flag = true;
            for (int j = 0; j < i; j++) {
                if (pat[j] != '*') {
                    flag = false;
                    break;
                }
            }
            cur[0] = flag;
            for (int j = 1; j <= m; j++) {
                cur[j] = false;
                if (pat[i - 1] == str[j - 1] || pat[i - 1] == '?') {
                    cur[j] = dp[j - 1];
                } else if (pat[i - 1] == '*') {
                    cur[j] = dp[j] || cur[j - 1];
                }
            }
            dp = cur;
        }

        return dp[m];
    }
};

// space optimization - using single array
// TC: O(n*m) SC: O(m)
class Solution {
public:
    bool isMatch(string str, string pat) {
        int n = pat.size();
        int m = str.size();
        vector<bool> dp(m + 1, false);
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            int prev = dp[0];
            int flag = true;
            for (int j = 0; j < i; j++) {
                if (pat[j] != '*') {
                    flag = false;
                    break;
                }
            }
            dp[0] = flag;
            for (int j = 1; j <= m; j++) {
                int temp = dp[j];
                if (pat[i - 1] == str[j - 1] || pat[i - 1] == '?') {
                    dp[j] = prev;
                } else if (pat[i - 1] == '*') {
                    dp[j] = dp[j] || dp[j - 1];
                } else dp[j] = false;
                prev = temp;
            }
        }

        return dp[m];
    }
};

// another space optimization - using pre computation of isAllStars
class Solution {
public:
    bool isMatch(string str, string pat) {
        int n = pat.size();
        int m = str.size();
        vector<bool> dp(m + 1, false);
        dp[0] = true;

        vector<bool> prefixStars(n + 1, true);
        for (int i = 1; i <= n; ++i) {
            prefixStars[i] = prefixStars[i - 1] && (pat[i - 1] == '*');
        }

        for (int i = 1; i <= n; i++) {
            bool prev = dp[0];
            dp[0] = prefixStars[i];
            for (int j = 1; j <= m; j++) {
                int temp = dp[j];
                if (pat[i - 1] == str[j - 1] || pat[i - 1] == '?') {
                    dp[j] = prev;
                } else if (pat[i - 1] == '*') {
                    dp[j] = dp[j] || dp[j - 1];
                } else
                    dp[j] = false;
                prev = temp;
            }
        }

        return dp[m];
    }
};
