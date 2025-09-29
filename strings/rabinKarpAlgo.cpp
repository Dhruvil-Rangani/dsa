#include <bits/stdc++.h>
using namespace std;

// TC: O(N * M) where N is the length of text and M is the length of pattern
// SC: O(K) where K is the number of occurrences of pattern in text

class Solution {
   public:
    vector<int> search(string pat, string txt) {
        int n = pat.length();
        int m = txt.length();

        vector<int> ans;

        for(int i = 0; i <= m - n; i++) {
            bool flag = true;
            for(int j = 0; j < n; j++) {
                if(txt[i + j] != pat[j]) {
                    flag = false;
                    break;
                }
            }
            if(flag) ans.push_back(i);
        }

        return ans;
    }
};

// Rabin Karp Algorithm
// TC: O(N) where N is the length of text
// SC: O(K) where K is the number of occurrences of pattern in text

class Solution {
   public:
    vector<int> search(string pat, string txt) {
        int n = pat.length();
        int m = txt.length();

        int p = 7, mod = 101;

        int hashPat = 0, hashText = 0;
        int pRight = 1, pLeft = 1;

        for(int i = 0; i < n; i++) {
            hashPat += ((pat[i] - 'a' + 1) * pRight) % mod;
            hashText += ((txt[i] - 'a' + 1) * pRight) % mod;
            pRight = (pRight * p) % mod;
        }

        vector<int> ans;

        for(int i = 0; i <= m - n; i++) {
            if(hashPat == hashText) {
                if(txt.substr(i, n) == pat) ans.push_back(i);
            }

            hashText = (hashText - ((txt[i] - 'a' + 1) * pLeft) % mod + mod) % mod;
            hashText = (hashText + ((txt[i + n] - 'a' + 1) * pRight) % mod) % mod;
            hashPat = (hashPat * p) % mod;

            pLeft = (pLeft * p) % mod;
            pRight = (pRight * p) % mod;
        }

        return ans;
    }
};