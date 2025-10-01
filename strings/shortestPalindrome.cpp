#include <bits/stdc++.h>
using namespace std;

// Shortest Palindrome
// TC: O(N) where N is the length of string
// SC: O(N) where N is the length of string

class Solution {
private:
    vector<int> computeLPS(string s) {
        int n = s.size();
        vector<int> LPS(n, 0);

        int i = 1, j = 0;

        while (i < n) {
            if (s[i] == s[j]) {
                LPS[i] = j + 1;
                i++, j++;
            } else {
                while (j > 0 && s[i] != s[j]) {
                    j = LPS[j - 1];
                }
                if (s[i] == s[j]) {
                    LPS[i] = j + 1;
                    j++;
                }
                i++;
            }
        }
        return LPS;
    }

public:
    string shortestPalindrome(string s) {
        string newS = s;
        reverse(newS.begin(), newS.end());

        string str = s + '$' + newS;
        vector<int> lps = computeLPS(str);
        int ans = s.size() - lps.back();
        string toAdd = newS.substr(0, ans);
        return toAdd + s;
    }
};