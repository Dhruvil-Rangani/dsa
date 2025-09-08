#include <vector>
#include <string>
using namespace std;

class Solution{
    public: int longestCommonSubstr (string s, string t){
      int n = s.size();
      int m = t.size();

      vector<int> dp(m + 1, 0);
      int ans = 0;

      for(int i = 1; i <= n; i++) {
        int prev = 0;
        for(int j = 1; j <= m; j++) {
            int temp = dp[j];
            if(s[i - 1] == t[j - 1]) {
                dp[j] = 1 + prev;
                ans = max(ans, dp[j]);
            } else dp[j] = 0;
            prev = temp;
        }
      }

      return ans;
    }
};