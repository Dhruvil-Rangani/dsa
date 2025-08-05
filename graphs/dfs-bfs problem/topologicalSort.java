// Topological Sort using DFS
import java.util.*;
class Solution {
    private void dfs(int node, List<List<Integer>> adj, boolean[] vis, Stack<Integer> st) {
        vis[node] = true;

        for(int adjNodes : adj.get(node)) {
            if(!vis[adjNodes]) dfs(adjNodes, adj, vis, st);
        }
        st.push(node);
    }
    public int[] topoSort(int V, List<List<Integer>> adj) {
        boolean[] vis = new boolean[V];
        int[] ans = new int[V];

        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < V; i++) {
            if(!vis[i]) {
                dfs(i, adj, vis, st);
            }
        }

        int i = 0;
        while(!st.isEmpty()) {
            ans[i++] = st.pop();
        }

        return ans;
    }
}