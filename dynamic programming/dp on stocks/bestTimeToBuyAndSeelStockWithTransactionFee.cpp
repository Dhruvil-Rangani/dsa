#include <bits/stdc++.h>
using namespace std;

// TC: O(n)
// SC: O(1)
class Solution {
public:
    int maxProfit(vector<int>& arr, int fee) {
        int n = arr.size();
        vector<int> dp(2, 0);
        vector<int> cur(2, 0);

        for(int i = n - 1; i >= 0; i--) {
            cur[0] = max(dp[0], dp[1] - arr[i] - fee);
            cur[1] = max(dp[1], dp[0] + arr[i]);
            dp = cur;
        }

        return dp[0];
    }
};