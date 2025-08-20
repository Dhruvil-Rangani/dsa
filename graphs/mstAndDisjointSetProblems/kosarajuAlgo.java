import java.util.ArrayList;
import java.util.Stack;
// LeetCode Problem: Strongly Connected Components (Kosaraju's Algorithm)
// https://practice.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo-1587115620
// Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
// Space Complexity: O(V + E), for the adjacency list and auxiliary data structures.
// This code finds the number of strongly connected components in a directed graph using Kosaraju's algorithm.
// The algorithm involves three main steps: performing a DFS to determine the finishing order of nodes,
// reversing the graph, and performing DFS on the reversed graph in the order of decreasing finishing times.
class Solution {
    private void dfs(int node, boolean[] vis, ArrayList<ArrayList<Integer>> adj, Stack<Integer> st) {
        vis[node] = true;
        for(int it : adj.get(node)) {
            if(!vis[it]) {
                dfs(it, vis, adj, st);
            }
        }
        st.push(node);
    }

    private void helperDfs(int node, boolean[] vis, ArrayList<ArrayList<Integer>> adjT) {
        vis[node] = true;

        for(int it : adjT.get(node)) {
            if(!vis[it]) {
                helperDfs(it, vis, adjT);
            }
        }
    }

    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        // 1. sort all the components based on their finishing time
        boolean[] vis = new boolean[V];
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < V; i++) {
            if(!vis[i]) {
                dfs(i, vis, adj, st);
            }
        }

        // 2. reverse all the edges in the entire graph
        ArrayList<ArrayList<Integer>> adjT = new ArrayList<>();
        for(int i = 0; i < V; i++) {
            adjT.add(new ArrayList<>());
        }

        for(int i = 0; i < V; i++) {
            vis[i] = false;

            for(int it : adj.get(i)) {
                adjT.get(it).add(i);
            }
        }

        // 3. perform dfs and count the number of times different dfs call is made 
        // for strongly connected components
        int cnt = 0;
        while(!st.isEmpty()) {
            int node = st.pop();

            if(!vis[node]) {
                cnt++;
                helperDfs(node, vis, adjT);
            }
        }

        return cnt;
    }
}

