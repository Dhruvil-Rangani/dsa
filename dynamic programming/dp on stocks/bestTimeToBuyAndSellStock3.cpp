#include <bits/stdc++.h>
using namespace std;
// recursive approach
// TC: O(2 ^ n)
// SC: O(n)

class Solution{
private:
    int f(int i, int buy, int cap, int n, vector<int>& arr) {
        if(i == n || cap == 0) return 0;

        int profit = 0;
        if(buy == 0) {
            profit = max(f(i + 1, 0, cap, n, arr), f(i + 1, 1, cap, n, arr) - arr[i]);
        } else {
            profit = max(f(i + 1, 1, cap, n, arr), f(i + 1, 0, cap - 1, n, arr) + arr[i]);
        }

        return profit;
    }

public:
    int stockBuySell(vector<int> arr, int n){
        return f(0, 0, 2, n, arr);
    }
};

// memoization approach
// TC: O(n * 2 * 3)
// SC: O(n * 2 * 3) + O(n)
class Solution{
private:
    int f(int i, int buy, int cap, int n, vector<int>& arr, vector<vector<vector<int>>>& dp) {
        if(i == n || cap == 0) return 0;
        if(dp[i][buy][cap] != -1) return dp[i][buy][cap];
        int profit = 0;
        if(buy == 0) {
            profit = max(f(i + 1, 0, cap, n, arr, dp), f(i + 1, 1, cap, n, arr, dp) - arr[i]);
        } else {
            profit = max(f(i + 1, 1, cap, n, arr, dp), f(i + 1, 0, cap - 1, n, arr, dp) + arr[i]);
        }

        return dp[i][buy][cap] = profit;
    }

public:
    int stockBuySell(vector<int> arr, int n){
        vector<vector<vector<int>>> dp(n, vector<vector<int>>(2, vector<int>(3, -1)));
        return f(0, 0, 2, n, arr, dp);
    }
};

// tabulation approach
// TC: O(n * 2 * 3)
// SC: O(n * 2 * 3)

class Solution{
public:
    int stockBuySell(vector<int> arr, int n){
        vector<vector<vector<int>>> dp(n + 1, vector<vector<int>>(2, vector<int>(3, 0)));

        for(int i = n - 1; i >= 0; i--) {
            for(int buy = 0; buy <= 1; buy++) {
                for(int cap = 1; cap <= 2; cap++) {
                    if(buy == 0) {
                        dp[i][buy][cap] = max(dp[i + 1][0][cap], dp[i + 1][1][cap] - arr[i]);
                    } else {
                        dp[i][buy][cap] = max(dp[i + 1][0][cap - 1] + arr[i], dp[i + 1][1][cap]);
                    }
                }
            }
        }
        return dp[0][0][2];
    }
};

// space optimization approach
// TC: O(n * 2 * 3)
// SC: O(1)

class Solution {
public:
    int maxProfit(vector<int>& arr) {
        int n = arr.size();
        vector<vector<int>> dp(2, vector<int>(3, 0));
        vector<vector<int>> cur(2, vector<int>(3, 0));
        for(int i = n - 1; i >= 0; i--) {
            for(int buy = 0; buy <= 1; buy++) {
                for(int cap = 1; cap <= 2; cap++) {
                    if(buy == 0) {
                        cur[buy][cap] = max(dp[0][cap], dp[1][cap] - arr[i]);
                    } else {
                        cur[buy][cap] = max(dp[0][cap - 1] + arr[i], dp[1][cap]);
                    }
                }
            }
            dp = cur;
        }
        return dp[0][2];
    }
};




