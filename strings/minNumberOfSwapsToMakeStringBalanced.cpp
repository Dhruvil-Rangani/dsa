#include <bits/stdc++.h>
using namespace std;

// TC: O(N) where N is length of string
// SC: O(1)

class Solution {
public:
    int minSwaps(string s) {
        int n = s.size();

        int open = 0, close = 0;
        for(char c : s) {
            if(c == '[') open++;
            else {
                if(open > 0) open--;
                else close++;
            }
        }

        int ans = ((open / 2) + (open % 2) + (close / 2) + (close % 2)) / 2;
        return ans;
    }
};