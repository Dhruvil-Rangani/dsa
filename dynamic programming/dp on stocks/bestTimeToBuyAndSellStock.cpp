#include <bits/stdc++.h>
using namespace std;
// best time to buy and sell stock - 1
// TC: O(n) SC: O(1)

class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        int profit = 0;
        int min = prices[0];
        for(int i = 1; i < n; i++) {
            if(min > prices[i]) min = prices[i];
            profit = max(profit, prices[i] - min);
        }

        return profit;
    }
};