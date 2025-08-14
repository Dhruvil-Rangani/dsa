import java.util.*;
// Time Complexity: O(V * E), where V is the number of vertices and E is the number of edges.
// Space Complexity: O(V), for the distance array.
class Solution {
  static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
    int[] dist = new int[V];
    Arrays.fill(dist, (int) 1e9);

    dist[S] = 0;

    for(int i = 0; i < V - 1; i++) {
        for(List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int wt = edge.get(2);

            if(dist[u] != 1e9 && dist[u] + wt < dist[v]) dist[v] = dist[u] + wt;
        }
    }

    for(List<Integer> edge : edges) {
        int u = edge.get(0);
        int v = edge.get(1);
        int wt = edge.get(2);

        if(dist[u] != 1e9 && dist[u] + wt < dist[v]) return new int[]{-1};
    }

    return dist;
  }
}
