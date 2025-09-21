#include <bits/stdc++.h>
using namespace std;
// recursive memoization approach
// TC: O(n^2)
// SC: O(n) + O(n) for recursion stack

class Solution {
    private:
        bool isPalindrome(int i, int j, string& s) {
            while(i < j) {
                if(s[i] != s[j]) return false;
                i++;
                j--;
            }
            return true;
        }

        int f(int i, int n, string& s, vector<int>& dp) {
            if(i == n) return 0;
            if(dp[i] != -1) return dp[i];
            int minCost = INT_MAX;

            for(int j = i; j < n; j++) {
                if(isPalindrome(i, j, s)) {
                    int cost = 1 + f(j + 1, n, s, dp);
                    minCost = min(minCost, cost);
                }
            }

            return dp[i] = minCost;
        }

	public:
    	int minCut(string s){
    		int n = s.size();
            vector<int> dp(n, -1);

            return f(0, n, s, dp) - 1;
	    }
};

// tabulation approach
// TC: O(n^2)
// SC: O(n)
class Solution {
   private:
    bool isPalindrome(int i, int j, string& s) {
        while (i < j) {
            if (s[i] != s[j]) return false;
            i++;
            j--;
        }
        return true;
    }

   public:
    int minCut(string s) {
        int n = s.size();
        vector<int> dp(n + 1, -1);

        for (int i = n - 1; i >= 0; i--) {
            int minCost = 1e9;
            for (int j = i; j < n; j++) {
                if (isPalindrome(i, j, s)) {
                    int cost = 1 + dp[j + 1];
                    minCost = min(minCost, cost);
                }
            }
            dp[i] = minCost;
        }

        return dp[0];
    }
};