#include <bits/stdc++.h>
using namespace std;

// recursive memoization approach
class Solution {
private:
    int f(int i, int prev, vector<int>& arr, vector<vector<int>> &dp) {
        // base case
        if(i == arr.size() - 1) {
            if(prev == -1 || arr[prev] < arr[i]) return 1;
            return 0;
        }

        if(dp[i][prev + 1] != -1) return dp[i][prev + 1];

        // not take
        int notTake = f(i + 1, prev, arr, dp);
        // take
        int take = 0;
        if(prev == -1 || arr[i] > arr[prev]) 
            take = f(i + 1, i, arr, dp) + 1;
        return dp[i][prev + 1] = max(take, notTake);
    }

public:
    int LIS(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> dp(n, vector<int>(n + 1, -1));
        return f(0, -1, nums, dp);
    }    
};