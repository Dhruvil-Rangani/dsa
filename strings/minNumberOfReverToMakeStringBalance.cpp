#include <bits/stdc++.h>
using namespace std;

// TC: O(N) where N is length of string
// SC: O(1)

class Solution {
public:
    int countRev (string s) {
        int n = s.size();

        if(n % 2) return -1;

        int open = 0, close = 0;

        for(int i = 0; i < n; i++) {
            if(s[i] == '(') open++;
            else {
                if(open > 0) open--;
                else close++;
            }
        }

        int ans = (open / 2) + (open % 2) + (close / 2) + (close % 2);
        return ans;
    }
};
