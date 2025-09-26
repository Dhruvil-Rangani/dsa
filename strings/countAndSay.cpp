#include <bits/stdc++.h>
using namespace std;

// TC: O(N * 2^N) where N is the number
// SC: O(N) for recursion stack

class Solution {
  public:
    string countAndSay(int n) {
      if(n == 1) return "1";
      string prev = countAndSay(n - 1);
      int cnt = 1;
      string res = "";

      for(int i = 1; i < prev.size(); i++) {
        if(prev[i] == prev[i - 1]) cnt++;
        else {
          res.push_back('0' + cnt);
          res.push_back(prev[i - 1]);
          cnt = 1;
        }
      }

      res.push_back('0' + cnt);
      res.push_back(prev[prev.size() - 1]);
      return res;
    }   
};