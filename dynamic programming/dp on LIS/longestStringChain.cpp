#include <bits/stdc++.h>
using namespace std;

class Solution {
private:
    bool static compare(string &s, string &t) {
        return s.size() < t.size();
    }

    bool checkPossible(string &s, string &t) {
        if(s.size() != t.size() + 1) return false;

        int i = 0, j = 0;

        while(i < s.size()) {
            if(j < t.size() && s[i] == t[j]) {
                i++, j++;
            } else i++;
        }

        if(i == s.size() && j == t.size()) return true;
        return false;
    }

public:
    int longestStringChain(vector<string>& words) {
        int n = words.size();
        sort(words.begin(), words.end(), compare);
        vector<int> dp(n, 1);

        int maxi = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(checkPossible(words[i], words[j]) && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }

            if(dp[i] > maxi) maxi = dp[i];
        }

        return maxi;
    }
};
