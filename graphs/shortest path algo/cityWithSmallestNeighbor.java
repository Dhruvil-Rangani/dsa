import java.util.*;
// LeetCode Problem: Find the City With the Smallest Number of Neighbors at a Threshold Distance
// https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a
// Time Complexity: O(N^3), where N is the number of nodes.
// Space Complexity: O(N^2), for the distance matrix.
// This problem can be solved using the Floyd-Warshall algorithm to find the shortest paths between all pairs of nodes.
// The solution then counts the number of neighbors for each city within the given distance threshold.
// The city with the smallest number of neighbors is returned, and in case of a tie, the city with the largest index is chosen.

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dist = new int[n][n];
        for(int[] row : dist) Arrays.fill(row, (int)1e9);

        for(int[] edge : edges) {
            dist[edge[0]][edge[1]] = edge[2];
            dist[edge[1]][edge[0]] = edge[2];
        }

        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(dist[i][k] == (int)1e9 || dist[k][j] == (int)1e9) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int minCount = (int)1e9;
        int ans = -1;

        for(int i = 0; i < n; i++) {
            int count = 0;
            for(int j = 0; j < n; j++) {
                if(i != j && dist[i][j] <= distanceThreshold) count++;
            }

            if(count <= minCount) {
                minCount = count;
                ans = i;
            }
        }

        return ans;
    }
}

// We can also use a more efficient approach using Dijkstra's algorithm for each city,

// LeetCode Problem: City With Smallest Number of Neighbors at a Threshold Distance
// https://leetcode.com/problems/city-with-smallest-number-of-neighbors-at-a-threshold-distance/

// Dijkstra's algorithm can be used to find the shortest paths from each city to all other cities.
// This approach is more efficient than Floyd-Warshall for sparse graphs, as it runs in O((N + M) * log(N)) time complexity,
// where N is the number of nodes and M is the number of edges.
// below is the implementation of solving the problem using Dijkstra's algorithm:

class Solution2 {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        List<int[]>[] adj = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
            adj[edge[1]].add(new int[]{edge[0], edge[2]});
        }

        int minCount = Integer.MAX_VALUE;
        int ans = -1;

        for(int i = 0; i < n; i++) {
            int count = 0;
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[i] = 0;

            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            pq.offer(new int[]{i, 0});

            while(!pq.isEmpty()) {
                int[] p = pq.poll();
                int node = p[0];
                int time = p[1];

                if(time > distanceThreshold) continue;

                for(int[] neighbor : adj[node]) {
                    int nextNode = neighbor[0];
                    int weight = neighbor[1];

                    if(dist[nextNode] > time + weight) {
                        dist[nextNode] = time + weight;
                        pq.offer(new int[]{nextNode, dist[nextNode]});
                    }
                }
            }

            for(int j = 0; j < n; j++) {
                if(i != j && dist[j] <= distanceThreshold) count++;
            }

            if(count < minCount || (count == minCount && i > ans)) {
                minCount = count;
                ans = i;
            }
        }

        return ans;
    }
}
