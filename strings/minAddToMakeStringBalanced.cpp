#include <bits/stdc++.h>
using namespace std;
// TC: O(N) where N is length of string
// SC: O(1)

class Solution {
public:
    int minAddToMakeValid(string s) {
        int n = s.size();

        int open = 0, close = 0;
        for(int i = 0; i < n; i++) {
            if(s[i] == '(') open++;
            else {
                if(open > 0) open--;
                else close++;
            }
        }

        return open + close;
    }
};