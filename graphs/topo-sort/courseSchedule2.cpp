#include <vector>
#include <queue>
using namespace std;
// Course schedule 2
// leetcode problem: https://leetcode.com/problems/course-schedule-ii/
// Time Complexity: O(V + E)
// Space Complexity: O(V + E)
class Solution {
  private:
    vector<int> topo(int N, vector<vector<int>> &adj) {
      vector<int> inDegree(N, 0);
      for(int i = 0; i < N; i++) {
        for(auto it: adj[i]) inDegree[it]++;
      }

      vector<int> ans;
      queue<int> q;

      for(int i = 0; i < N; i++) {
        if(inDegree[i] == 0) q.push(i);
      }

      while(!q.empty()) {
        int node = q.front();
        q.pop();
        ans.push_back(node);

        for(auto it: adj[node]) {
          inDegree[it]--;
          if(inDegree[it] == 0) q.push(it);
        }
      }

      return ans;
    }
   public:
    vector<int> findOrder(int N, vector<vector<int>> arr) {
        vector<vector<int>> adj(N);
        for (auto it : arr) {
            int u = it[0];
            int v = it[1];

            adj[v].push_back(u);
        }

        vector<int> order = topo(N, adj);
        if(N == order.size()) return order;
        return {};
    }
};
