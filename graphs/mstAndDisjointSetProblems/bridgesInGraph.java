import java.util.*;
// LeetCode Problem: Critical Connections in a Network
// https://leetcode.com/problems/critical-connections-in-a-network/
// Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
// Space Complexity: O(V + E), for the adjacency list and visited array.

class Solution {
    private int timer = 1;
    private void dfs(int node, int parent, boolean[] vis, List<Integer>[] adj, int[] tin, int[] low, List<List<Integer>> bridges) {
        vis[node] = true;
        tin[node] = low[node] = timer;
        timer++;

        for(int adjNode : adj[node]) {
            if(adjNode == parent) continue;

            if(!vis[adjNode]) {
                dfs(adjNode, node, vis, adj, tin, low, bridges);
                low[node] = Math.min(low[adjNode], low[node]);

                if(low[adjNode] > tin[node]) {
                    bridges.add(Arrays.asList(adjNode, node));
                }
            } else {
                low[node] = Math.min(low[node], low[adjNode]);
            }
        }
    }

    public List<List<Integer>> criticalConnections(int V, List<List<Integer>> E) {
        int n = V;
      List<Integer>[] adj = new ArrayList[n];
      for(int i = 0; i < n; i++) {
        adj[i] = new ArrayList<>();
      }

      for(List<Integer> it : E) {
        int u = it.get(0), v = it.get(1);
        adj[u].add(v);
        adj[v].add(u);
      }

      boolean[] vis = new boolean[n];
      int[] tin = new int[n];
      int[] low = new int[n];

      List<List<Integer>> bridges = new ArrayList<>();
      dfs(0, -1, vis, adj, tin, low, bridges);
      return bridges;
    }
}

