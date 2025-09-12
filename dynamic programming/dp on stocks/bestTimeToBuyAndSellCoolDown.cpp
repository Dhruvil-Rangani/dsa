#include <bits/stdc++.h>
using namespace std;
// TC: O(n * 2)
// SC: O(n * 2)
class Solution {
public:
    int maxProfit(const vector<int>& arr) {
        int n = arr.size();
       vector<vector<int>> dp(n + 2, vector<int>(2, 0));

        int profit;

        for(int i = n - 1; i >= 0; i--) {
            for(int buy = 0; buy <= 1; buy++) {
                if(buy == 0) {
                    profit = max(dp[i + 1][0], dp[i + 1][1] - arr[i]);
                } else {
                    profit = max(dp[i + 1][1], dp[i + 2][0] + arr[i]);
                }
                dp[i][buy] = profit;
            }
        }

        return dp[0][0];
    }
};

// space optimization
// TC: O(n)
// SC: O(1)
class Solution {
public:
    int maxProfit(const vector<int>& arr) {
        int n = arr.size();
       vector<int> front1(2, 0);
       vector<int> front2(2,0);
       vector<int> cur(2,0);

        int profit;

        for(int i = n - 1; i >= 0; i--) {
            cur[0] = max(front1[0], front1[1] - arr[i]);
            cur[1] = max(front1[1], front2[0] + arr[i]);
            front2 = front1;
            front1 = cur;
        }

        return front1[0];
    }
};