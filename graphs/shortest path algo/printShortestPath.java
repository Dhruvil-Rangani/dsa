import java.util.*;
// LeetCode Problem: Shortest Path in Undirected Graph with Unit Weights
// https://leetcode.com/problems/shortest-path-in-undirected-graph-with-unit-weights/
// Time Complexity: O(N + M), where N is the number of nodes and M is the number of edges.
// Space Complexity: O(N), for the adjacency list and distance array.
class Solution {
    public List<Integer> shortestPath(int n, int m, int[][] edges) {
     List<List<int[]>> adj = new ArrayList<>();
     for(int i = 0; i <=n; i++) {
        adj.add(new ArrayList<>());
     }

     for(int[] edge : edges) {
        adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
        adj.get(edge[1]).add(new int[]{edge[0], edge[2]});
     }

     PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
     int[] dist = new int[n + 1];
     Arrays.fill(dist, (int)1e9);

     int[] parent = new int[n + 1];
     for(int i = 0; i <= n; i++) {
        parent[i] = i;
     }
     
     dist[1] = 0;

     pq.add(new int[]{0, 1});
     while(!pq.isEmpty()) {
        int[] curr = pq.poll();
        int node = curr[1];
        int dis = curr[0];

        for(int[] adjNodes : adj.get(node)) {
            int curNode = adjNodes[0];
            int edWt = adjNodes[1];

            if(dis + edWt < dist[curNode]) {
                dist[curNode] = dis + edWt;
                pq.add(new int[]{dis + edWt, curNode});
                parent[curNode] = node;
            }
        }
     }

     if(dist[n] == (int)1e9) return Arrays.asList(-1);

     List<Integer> path = new ArrayList<>();
     int node = n;

     while(parent[node] != node) {
        path.add(node);
        node = parent[node];
     }

     path.add(1);
     Collections.reverse(path);
     path.add(0, dist[n]);
     return path;
    }
}



