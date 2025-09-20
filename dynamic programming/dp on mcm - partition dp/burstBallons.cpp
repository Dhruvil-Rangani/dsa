#include <bits/stdc++.h>
using namespace std;
// recursive memoization approach
// TC: O(n^3)
// SC: O(n^2) + O(n) for recursion stack

class Solution {
    private:
        int f(int i, int j, vector<int>& nums, vector<vector<int>>& dp) {
            if(i > j) return 0;
            if(dp[i][j] != -1) return dp[i][j];
            int maxi = INT_MIN;
            for(int k = i; k <= j; k++) {
                int coins = nums[i - 1] * nums[k] * nums[j + 1];
                int remainingCoins = f(i, k - 1, nums, dp) + f(k + 1, j, nums, dp);
                maxi = max(maxi, coins + remainingCoins);
            }

            return dp[i][j] = maxi;
        }
    public:
        int maxCoins(vector<int>& nums){
    	    int n = nums.size();
            vector<vector<int>> dp(n + 1, vector<int>(n + 1, -1));
            nums.insert(nums.begin(), 1);
            nums.push_back(1);
            return f(1, n, nums, dp);
        }
};

// tabulation approach
// TC: O(n^3)
// SC: O(n^2)
class Solution {
public:
    int maxCoins(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> dp(n + 2, vector<int>(n + 2, 0));
        nums.insert(nums.begin(), 1);
        nums.push_back(1);

        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= n; j++) {
                if (i > j) continue;
                int maxi = INT_MIN;

                for (int k = i; k <= j; k++) {
                    int coins = nums[i - 1] * nums[k] * nums[j + 1];
                    int remainingCoins =
                        dp[i][k - 1] + dp[k + 1][j];
                    maxi = max(maxi, coins + remainingCoins);
                }

                dp[i][j] = maxi;
            }
        }

        return dp[1][n];
    }
};