#include <bits/stdc++.h>
using namespace std;
// recursive memoization approach
// TC: O(n* k)
// SC: O(n) + O(n) for recursion stack

class Solution {
private:
    int f(int i, int n, vector<int>& arr, int k, vector<int>& dp) {
        if(i == n) return 0;
        if(dp[i] != -1) return dp[i];
        int len = 0;
        int maxi = INT_MIN;
        int maxAns = INT_MIN;
        for(int j = i; j < min(n, i + k); j++) {
            len++;
            maxi = max(maxi, arr[j]);
            int sum = (len * maxi) + f(j + 1, n, arr, k, dp);
            maxAns = max(maxAns, sum);
        }

        return dp[i] = maxAns;
    }

public:
    int maxSumAfterPartitioning(vector<int>& arr, int k) {
        int n = arr.size();
        vector<int> dp(n, -1);
        return f(0, n, arr, k, dp);
    }
};

// tabulation approach
// TC: O(n* k)
// SC: O(n)
class Solution {
public:
    int maxSumAfterPartitioning(vector<int>& arr, int k) {
        int n = arr.size();
        vector<int> dp(n + 1, 0);

        for (int i = n - 1; i >= 0; i--) {
            int len = 0;
            int maxi = -1e8;
            int maxAns = -1e8;
            for (int j = i; j < min(n, i + k); j++) {
                len++;
                maxi = max(maxi, arr[j]);
                int sum = (len * maxi) + dp[j + 1];
                maxAns = max(maxAns, sum);
            }

            dp[i] = maxAns;
        }
        return dp[0];
    }
};