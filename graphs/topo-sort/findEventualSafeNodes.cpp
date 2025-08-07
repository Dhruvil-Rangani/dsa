#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
// Function to find all eventual safe nodes in a directed graph
// Leetcode: https://leetcode.com/problems/find-eventual-safe-states/
// Time Complexity: O(V + E)
// Space Complexity: O(V + E) + O(V) + O(V)


class Solution {
    private:
        vector<int> topoSort(int V, vector<vector<int>> adjRev, vector<int>& inDegree) {
            vector<int> ans;
            queue<int> q;

            for(int i = 0; i < V; i++) {
                if(inDegree[i] == 0) q.push(i);
            }

            while(!q.empty()) {
                int node = q.front();
                ans.push_back(node);
                q.pop();

                for(auto it : adjRev[node]) {
                    inDegree[it]--;
                    if(inDegree[it] == 0) q.push(it);
                }
            }

            return ans;
        }
   public:
    vector<int> eventualSafeNodes(int V, vector<int> adj[]) {
        vector<vector<int>> adjRev(V);
        vector<int> inDegree(V, 0);
        for(int i = 0; i < V; i++) {
            for(auto it: adj[i]) {
                adjRev[it].push_back(i);
                inDegree[i]++;
            }
        }

        vector<int> result = topoSort(V, adjRev, inDegree);
        sort(result.begin(), result.end());
        return result;
    }
};