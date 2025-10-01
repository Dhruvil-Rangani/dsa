#include <bits/stdc++.h>
using namespace std;

// Naive Approach
// TC: O(N * M) where N is the length of text and M is the length of pattern
// SC: O(K) where K is the number of occurrences of pattern in text

class Solution{
    private:
      vector<int> computeZarray(string s) {
        int n = s.size();

        vector<int> Z(n, 0);
        for(int i = 1; i < n; i++) {
          while(i + Z[i] < n && s[i + Z[i]] == s[Z[i]]) Z[i]++;
        }
        return Z;
      }

    public:
       vector<int> search(string text , string pattern){
        string s = pattern + '$' + text;
        vector<int> Z = computeZarray(s);

        int n = text.size(), m = pattern.size();

        vector<int> ans;
        for(int i = m + 1; i < s.size(); i++) {
          if(Z[i] == m) ans.push_back(i - (m + 1));
        }
        return ans;
     }
};

// Z-Algorithm
// TC: O(N) where N is the length of text
// SC: O(K) where K is the number of occurrences of pattern in text

class Solution{
    private:
      vector<int> computeZarray(string s) {
        int n = s.size();
        vector<int> Z(n, 0);

        int left = 0, right = 0;

        for(int i = 1; i < n; i++) {
          if(i > right)  {
            while(i + Z[i] < n && s[i + Z[i]] == s[Z[i]]) Z[i]++;
          } else {
            if(i + Z[i - left] <= right) Z[i] = Z[i - left];
            else {
              Z[i] = right - i + 1;
              while(i + Z[i] < n && s[i + Z[i]] == s[Z[i]]) Z[i]++;
            }
          }
          left = i, right = i + Z[i] - 1;
        }

        return Z;
      }

    public:
       vector<int> search(string text , string pattern){
        string s = pattern + '$' + text;
        vector<int> Z = computeZarray(s);

        int n = text.size(), m = pattern.size();

        vector<int> ans;
        for(int i = m + 1; i < s.size(); i++) {
          if(Z[i] == m) ans.push_back(i - (m + 1));
        }
        return ans;
     }
};



