// This code is a solution for finding the shortest path in a Directed Acyclic Graph (DAG) with weighted edges.
import java.util.*;
// LeetCode Problem: Shortest Path in a Directed Acyclic Graph (DAG) with Weighted Edges

class Solution {
    static class Pair {
        int node, distance;

        Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    private void topoSort(int node, List<List<Pair>> adj, boolean[] vis, Stack<Integer> st) {
        vis[node] = true;
        for(Pair pair : adj.get(node)) {
            int v = pair.node;
            if(!vis[v]) {
                topoSort(v, adj, vis, st);
            }
        }
        st.push(node);
    }

  public int[] shortestPath(int N, int M, int[][] edges) {
    List<List<Pair>> adj = new ArrayList<>();
    for(int i = 0; i < N; i++) {
        adj.add(new ArrayList<>());
    }

    for(int i = 0; i < M; i++) {
        int u = edges[i][0];
        int v = edges[i][1];
        int wt = edges[i][2];

        adj.get(u).add(new Pair(v, wt));
    }

    boolean[] vis = new boolean[N];
    Stack<Integer> st = new Stack<>();

    for(int i = 0; i < N; i++) {
        if(!vis[i]) {
            topoSort(i, adj, vis, st);
        }
    }

    int[] dist = new int[N];
    Arrays.fill(dist, (int)1e9);
    dist[0] = 0;

    while(!st.isEmpty()) {
        int node = st.pop();

        for(Pair pair : adj.get(node)) {
            int v = pair.node;
            int wt = pair.distance;
            // if(dist[node] == Integer.MAX_VALUE) continue;
            if(dist[node] + wt < dist[v]) {
                dist[v] = wt + dist[node];
            }
        }
    }

    for(int i = 0; i < N; i++) {
        if(dist[i] == (int)1e9) dist[i] = -1;
    }
    return dist;
  }
}