import java.util.*;
// LeetCode Problem: Redundant Connection
// https://leetcode.com/problems/redundant-connection/
// Time Complexity: O(N + E), where N is the number of nodes and E is the number of edges.
// Space Complexity: O(N), for the adjacency list and other auxiliary arrays.
// This code finds a redundant connection in an undirected graph using DFS and bridge-finding logic.
// It identifies the last edge that, if removed, would still keep the graph connected.
// The graph is represented as an adjacency list, and a DFS is performed to find bridges.
// The last edge that is not a bridge is considered the redundant connection.
// The findRedundantConnection method initializes the adjacency list and runs DFS to find bridges, returning the last non-bridge edge.
// The graph is assumed to be connected, and the input edges are given in a specific format.
class Solution {
    private int timer = 1;
    private void dfs(int node, int parent, boolean[] vis, List<int[]>[] adj, int[] tin, int[] low, boolean[] isBridge) {
        vis[node] = true;
        tin[node] = low[node] = timer;
        timer++;

        for(int[] adjNode : adj[node]) {
            int v = adjNode[0], id = adjNode[1];
            if(id == parent) continue;

            if(!vis[v]) {
                dfs(v, id, vis, adj, tin, low, isBridge);
                low[node] = Math.min(low[v], low[node]);

                if(low[v] > tin[node]) {
                    isBridge[id] = true;
                }
            } else {
                low[node] = Math.min(low[node], tin[v]);
            }
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
    int n = edges.length;
    // adjacency with [neighbor, edgeId]
        List<int[]>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int u = edges[i][0], v = edges[i][1];
            adj[u].add(new int[]{v, i});
            adj[v].add(new int[]{u, i});
        }

      boolean[] vis = new boolean[n + 1];
      int[] tin = new int[n + 1];
      int[] low = new int[n + 1];
      boolean[] isBridge = new boolean[n];

      // Graph may be disconnected in theory; run DFS from all components
        for (int u = 1; u <= n; u++) {
            if (!vis[u]) {
                dfs(u, -1, vis, adj, tin, low, isBridge);
            }
        }
      

      // The redundant edge is the last edge that is NOT a bridge
        for (int i = n - 1; i >= 0; i--) {
            if (!isBridge[i]) return edges[i];
        }

      return new int[]{-1, -1};
    }
}