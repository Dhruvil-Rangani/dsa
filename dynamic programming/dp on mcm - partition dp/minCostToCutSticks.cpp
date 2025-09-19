#include <bits/stdc++.h>
using namespace std;
// recursive approach
// TC: O(2^N)
// SC: O(N) for recursion stack

class Solution {
    private:
        int f(int i, int j, vector<int>& cuts) {
            if(i > j) return 0;

            int mini = INT_MAX;
            for(int k = i; k <= j; k++) {
                int ans = cuts[j + 1] - cuts[i - 1] + f(i, k - 1, cuts) + f(k + 1, j, cuts);
                mini = min(mini, ans);
            }

            return mini;
        }

	public:
		int minCost(int n, vector<int>& cuts){
            int c = cuts.size();

            cuts.push_back(n);
            cuts.insert(cuts.begin(), 0);
            sort(cuts.begin(), cuts.end());

            return f(1, c, cuts);
		}
};

// memoization method
// TC: O(N^3)
// SC: O(N^2) + O(N) for recursion stack

class Solution {
    private:
        int f(int i, int j, vector<int>& cuts, vector<vector<int>>& dp) {
            if(i > j) return 0;
            if(dp[i][j] != -1) return dp[i][j];
            int mini = INT_MAX;
            for(int k = i; k <= j; k++) {
                int ans = cuts[j + 1] - cuts[i - 1] + f(i, k - 1, cuts, dp) + f(k + 1, j, cuts, dp);
                mini = min(mini, ans);
            }

            return dp[i][j] = mini;
        }

	public:
		int minCost(int n, vector<int>& cuts){
            int c = cuts.size();
            vector<vector<int>> dp(c + 1, vector<int>(c + 1, -1));
            cuts.push_back(n);
            cuts.insert(cuts.begin(), 0);
            sort(cuts.begin(), cuts.end());

            return f(1, c, cuts, dp);
		}
};

// tabulation method
// TC: O(N^3)
// SC: O(N^2)

class Solution {
   public:
    int minCost(int n, vector<int>& cuts) {
        int c = cuts.size();
        vector<vector<int>> dp(c + 2, vector<int>(c + 2, 0));
        cuts.push_back(n);
        cuts.insert(cuts.begin(), 0);
        sort(cuts.begin(), cuts.end());

        for (int i = c; i >= 1; i--) {
            for (int j = 1; j <= c; j++) {
                if (i > j) continue;
                int mini = 1e9;
                for (int k = i; k <= j; k++) {
                    int ans = cuts[j + 1] - cuts[i - 1] +
                              dp[i][k - 1] + dp[k + 1][j];
                    mini = min(mini, ans);
                }

                dp[i][j] = mini;
            }
        }

        return dp[1][c];
    }
};