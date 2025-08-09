import java.util.*;
// LeetCode Problem: Shortest Path in Undirected Graph with Unit Weights
// https://leetcode.com/problems/shortest-path-in-undirected-graph-with-unit-weights/

class Solution {
  private void bfs(int src, List<Integer>[] adj, int[] dist) {
    dist[src] = 0;
    Queue<Integer> q = new LinkedList<>();
    q.add(src);
    while(!q.isEmpty()) {
      int node = q.poll();
      for(int adjNode: adj[node]) {
        if(dist[node] + 1 < dist[adjNode]) {
          dist[adjNode] = 1 + dist[node];
          q.add(adjNode);
        }
      }
    }
  }

  public int[] shortestPath(int[][] edges, int N, int M) {
    List<Integer>[] adj = new ArrayList[N];
    for (int i = 0; i < N; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      adj[u].add(v);
      adj[v].add(u);
    }

    int[] dist = new int[N];
    Arrays.fill(dist, Integer.MAX_VALUE);
    bfs(0, adj, dist);

    for (int i = 0; i < N; i++) {
      if (dist[i] == Integer.MAX_VALUE) dist[i] = -1;
    }

    return dist;
  }
}
