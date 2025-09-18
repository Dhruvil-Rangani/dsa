#include <bits/stdc++.h>
using namespace std;

// MCM - Matrix Chain Multiplication
// TC: O(N^3)
// SC: O(N^2) + O(N^2) for recursion stack

class Solution{
    private:
        int f(int i, int j, vector<int>& nums, vector<vector<int>> dp) {
            if(i == j) return 0;
            if(dp[i][j] != -1) return dp[i][j];
            int mini = INT_MAX;
            for(int k = i; k < j; k++) {
                int ans = f(i, k, nums, dp) + f(k + 1, j, nums, dp) + nums[i - 1] * nums[k] * nums[j];
                mini = min(mini, ans);
            }

            return dp[i][j] = mini;
        }

	public:
		int matrixMultiplication(vector<int>& nums){
            int n = nums.size();
            int i = 1;
            int j = n - 1;
            vector<vector<int>> dp(n, vector<int>(n, -1));
            return f(i, j, nums, dp);
    	}
};

// tabulation method
// TC: O(N^3)
// SC: O(N^2)

class Solution{
	public:
		int matrixMultiplication(vector<int>& nums){
            int n = nums.size();
            vector<vector<int>> dp(n, vector<int>(n, 0));
            for(int i = n - 1; i >= 1; i--) {
                for(int j = i + 1; j < n; j++) {
                    int mini = 1e9;
                    for(int k = i; k < j; k++) {
                        int ans = dp[i][k] + dp[k + 1][j] + nums[i - 1] * nums[k] * nums[j];
                        mini = min(mini, ans);
                    }
                    dp[i][j] = mini;
                }
            }

            return dp[1][n - 1];
    	}
};