#include <vector>
using namespace std;
// Detect Cycle in a Directed Graph using DFS
// Time Complexity: O(V + E)
// Space Complexity: O(V)
class Solution{
private:
    bool dfs(int node, vector<int> adj[], vector<bool> &visited, vector<bool> &pathVisited) {
        visited[node] = true;
        pathVisited[node] = true;

        for(auto adjNodes : adj[node]) {
            if(pathVisited[adjNodes]) return true;
            else if(!visited[adjNodes]) {
                if(dfs(adjNodes, adj, visited, pathVisited)) return true;
            }
        }

        pathVisited[node] = false;
        return false;
    }
public:
    bool isCyclic(int V, vector<int> adj[]) {
        vector<bool> visited(V, false);
        vector<bool> pathVisited(V, false);

        for(int i = 0; i < V; i++) {
            if(!visited[i]) {
                if(dfs(i, adj, visited, pathVisited)) return true;
            }
        }

        return false;
    }
};
