#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> largestDivisibleSubset(vector<int>& nums) {
        int n = nums.size();
        sort(nums.begin(), nums.end());
        vector<int> ans;
        vector<int> dp(n, 1);
        vector<int> parent(n);

        int maxi = 0;
        int lastIndex = 0;

        for(int i = 0; i < n; i++){
            parent[i] = i;
            for(int prev = 0; prev < i; prev++) {
                if(nums[i] % nums[prev] == 0 && dp[i] < dp[prev] + 1) {
                    dp[i] = dp[prev] + 1;
                    parent[i] = prev;
                }
            }

            if(dp[i] > maxi) {
                maxi = dp[i];
                lastIndex = i;
            }
        }
        
        int i = lastIndex;
        while(parent[i] != i) {
            ans.push_back(nums[i]);
            i = parent[i];
        }
        ans.push_back(nums[i]);

        return ans;
    }
};