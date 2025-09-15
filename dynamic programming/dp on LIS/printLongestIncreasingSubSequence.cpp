#include <bits/stdc++.h>
using namespace std;

class Solution {
  public:
    vector<int> longestIncreasingSubsequence(vector<int>& arr) {
      int n = arr.size();
      vector<int> ans;
      vector<int> dp(n, 1);
      vector<int> parent(n);

      int lastIndex = 0;
      int maxi = 1;

      for(int i = 0; i < n; i++) {
        parent[i] = i;

        for(int prev = 0; prev < i; prev++) {
          if(arr[prev] < arr[i] && dp[i] < dp[prev] + 1){
            dp[i] = dp[prev] + 1;
            parent[i] = prev;
          }
        }

        if(dp[i] > maxi) {
          lastIndex = i;
          maxi = dp[i];
        }
      }

      int i = lastIndex;

      while(parent[i] != i) {
        ans.push_back(arr[i]);
        i = parent[i];
      }
      ans.push_back(arr[i]);
      reverse(ans.begin(), ans.end());
      return ans;
    }
};