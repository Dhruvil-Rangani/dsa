import java.util.ArrayList;
import java.util.Arrays;
// LeetCode Problem: Articulation Point in a Graph
// https://practice.geeksforgeeks.org/problems/articulation-point-1587115620
// Time Complexity: O(N + E), where N is the number of nodes and E is the number of edges.
// Space Complexity: O(N), for the adjacency list and other auxiliary arrays.
// This code finds all articulation points in an undirected graph using DFS and low-link values.    

class Solution {
    private int timer = 0;

    private void dfs(int node, int parent, boolean[] vis, int[] tin, int[] low, boolean[] mark, ArrayList<ArrayList<Integer>> adj) {
        vis[node] = true;
        tin[node] = low[node] = timer;
        timer++;

        int child = 0;

        for(int it : adj.get(node)) {
            if(it == parent) continue;

            if(!vis[it]) {
                dfs(it, node, vis, tin, low, mark, adj);
                low[node] = Math.min(low[node], low[it]);

                if(low[it] >= tin[node] && parent != -1) mark[node] = true;
                child++;
            } else {
                low[node] = Math.min(low[node], tin[it]);
            }
        }

        if(child > 1 && parent == -1) mark[node] = true;
    }
    public ArrayList<Integer> articulationPoints(int n,
            ArrayList<ArrayList<Integer>> adj) {
       boolean[] vis = new boolean[n];
       int[] tin = new int[n];
       int[] low = new int[n];
       boolean[] mark = new boolean[n];

       for(int i = 0; i < n; i++) {
        if(!vis[i]) {
            dfs(i, -1, vis, tin, low, mark, adj);
        }
       }

       ArrayList<Integer> ans = new ArrayList<>();
       for(int i = 0; i < n; i++) {
        if(mark[i]) {
            ans.add(i);
        }
       }

       if(ans.size() == 0) {
        return new ArrayList<>(Arrays.asList(-1));
       }

       return ans;
    }
}

