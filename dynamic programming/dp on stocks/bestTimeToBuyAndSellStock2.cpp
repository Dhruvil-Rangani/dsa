#include <bits/stdc++.h>
using namespace std;
// recursive memoization approach
// TC: O(n * 2) 
// SC: O(n * 2) + O(n)

class Solution{
private:
    int f(int i, int buy, int n, vector<int>& arr, vector<vector<int>>& dp) {
        if(i == n) return 0;
        if(dp[i][buy] != -1) return dp[i][buy];
        int profit = 0;
        if(buy == 0) {
            profit = max(f(i + 1, 0, n, arr, dp), f(i + 1, 1, n, arr, dp) - arr[i]);
        } else {
            profit = max(f(i + 1, 1, n, arr, dp), arr[i] + f(i + 1, 0, n, arr, dp));
        }
        return dp[i][buy] = profit;
    }

public:
    int stockBuySell(vector<int> arr, int n){
        vector<vector<int>> dp(n, vector<int>(2, -1));
        return f(0, 0, n, arr, dp);
    }
};

// tabulation approach
// TC: O(n * 2)
// SC: O(n * 2)

class Solution{
public:
    int stockBuySell(vector<int> arr, int n){
        vector<vector<int>> dp(n + 1, vector<int>(2, 0));
        dp[n][0] = dp[n][1] = 0;

        int profit;

        for(int i = n - 1; i >= 0; i--) {
            for(int buy = 0; buy <= 1; buy++) {
                if(buy == 0) {
                    profit = max(dp[i + 1][0], dp[i + 1][1] - arr[i]);
                } else {
                    profit = max(dp[i + 1][1], dp[i + 1][0] + arr[i]);
                }
                dp[i][buy] = profit;
            }
        }

        return dp[0][0];
    }
};

// space optimization approach
// TC: O(n * 2)
// SC: O(1)

class Solution {
public:
    int maxProfit(vector<int>& arr) {
        int n = arr.size();
        vector<int> dp(2, 0);
        vector<int> cur(2, 0);
        dp[0] = dp[1] = 0;

        int profit;

        for(int i = n - 1; i >= 0; i--) {
            for(int buy = 0; buy <= 1; buy++) {
                if(buy == 0) {
                    profit = max(dp[0], dp[1] - arr[i]);
                } else {
                    profit = max(dp[1], dp[0] + arr[i]);
                }
                cur[buy] = profit;
            }
            dp = cur;
        }

        return dp[0];
    }
};


