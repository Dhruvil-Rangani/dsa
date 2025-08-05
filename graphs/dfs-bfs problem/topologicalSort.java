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

// Topological Sort using BFS (Kahn's Algorithm)
// Time Complexity: O(V + E)
// Space Complexity: O(V)

class Solution2 {
    public int[] topoSort(int V, List<List<Integer>> adj) {
        int[] indegree = new int[V];
        for(int i = 0; i < V; i++) {
            for(int n : adj.get(i)) indegree[n]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < V; i++) {
            if(indegree[i] == 0) q.add(i);
        }

        int[] ans = new int[V];
        int index = 0;

        while(!q.isEmpty()) {
            int node = q.poll();
            ans[index++] = node;

            for(int n : adj.get(node)) {
                indegree[n]--;
                if(indegree[n] == 0) q.add(n);
            }
        }

        return ans;
    }
}