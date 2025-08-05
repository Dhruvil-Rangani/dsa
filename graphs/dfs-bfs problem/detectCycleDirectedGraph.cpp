#include <vector>
#include <queue>
#include <iostream>
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

// Detect Cycle in a Directed Graph using Kahn's Algorithm (BFS)
// Time Complexity: O(V + E)   
// Space Complexity: O(V)

class Solution {
public:
    bool isCyclic(int V, const std::vector<std::vector<int>>& adj) {
        std::vector<int> inDegree(V, 0);

        // Calculate in-degree of each vertex
        for (int i = 0; i < V; i++) {
            for (int n : adj[i]) {
                inDegree[n]++;
            }
        }

        queue<int> q;
        // Enqueue vertices with in-degree 0
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.push(i);
            }
        }

        int countProcessed = 0;
        while (!q.empty()) {
            int node = q.front();
            q.pop();
            countProcessed++;

            // Reduce the in-degree of neighbors
            for (int n : adj[node]) {
                inDegree[n]--;
                if (inDegree[n] == 0) {
                    q.push(n);
                }
            }
        }

        // If countProcessed != V, there is a cycle
        return countProcessed != V;
    }
};
