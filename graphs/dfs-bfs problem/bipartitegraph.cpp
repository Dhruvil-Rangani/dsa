// / This is a C++ solution for the problem of checking if a graph is bipartite using BFS.
#include <vector>
#include <queue>
using namespace std;
// LeetCode 785. Is Graph Bipartite?
// https://leetcode.com/problems/is-graph-bipartite/
// This solution uses BFS to color the graph and check if it is bipartite.
// The time complexity is O(V + E) where V is the number of vertices and E is the number of edges in the graph.

class Solution{
private:
    bool bfs(int start, int V, vector<int> adj[], int color[]) {
        queue<int> q;
        q.push(start);
        color[start] = 0;

        while(!q.empty()) {
            int node = q.front();
            q.pop();

            for(auto nodes : adj[node]) {
                if(color[nodes] == - 1) {
                    color[nodes] = !color[node];
                    q.push(nodes);
                } else if(color[nodes] == color[node]) return false;
            }
        }

        return true;
    }
public:
    bool isBipartite(int V, vector<int> adj[])  {
        int color[V];
        for(int i = 0; i < V; i++) color[i] = -1;

        for(int i = 0; i < V; i++) {
            if(color[i] == -1) {
                if(!bfs(i, V, adj, color)) return false;
            }
        }

        return true;
    }
};
