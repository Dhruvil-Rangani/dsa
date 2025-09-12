#include <bits/stdc++.h>
using namespace std;

// this is the another variant of besttimetobuyandsellstock 3 where instead of at most 2 transactions we have to compute algo with k transactions.
// TC: O(n * 2 * k)
// SC: O(2 * k)

class Solution{
public:
    int stockBuySell(vector<int> arr, int n, int k){
        vector<vector<int>> dp(2, vector<int>(k + 1, 0));
        vector<vector<int>> cur(2, vector<int>(k + 1, 0));
        for(int i = n - 1; i >= 0; i--) {
            for(int buy = 0; buy <= 1; buy++) {
                for(int cap = 1; cap <= k; cap++) {
                    if(buy == 0) {
                        cur[buy][cap] = max(dp[0][cap], dp[1][cap] - arr[i]);
                    } else {
                        cur[buy][cap] = max(dp[0][cap - 1] + arr[i], dp[1][cap]);
                    }
                }
            }
            dp = cur;
        }
        return dp[0][k];
    }
};
