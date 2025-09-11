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


