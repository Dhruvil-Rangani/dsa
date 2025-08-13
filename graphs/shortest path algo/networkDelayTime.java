import java.util.*;
// LeetCode Problem: Network Delay Time
// https://leetcode.com/problems/network-delay-time/
// Time Complexity: O((N + M) * log(N)), where N is the number of nodes and M is the number of edges.
// Space Complexity: O(N + M), for the adjacency list and distance array.

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] time : times) {
            graph.get(time[0]).add(new int[]{time[1], time[2]});
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, k});

        boolean[] visited = new boolean[n + 1];

        while(!pq.isEmpty()) {
            int[] p = pq.poll();
            int time = p[0], node = p[1];

            if(visited[node]) continue;
            visited[node] = true;

            for(int[] neighbor : graph.get(node)) {
                int next = neighbor[0];
                int weight = neighbor[1];

                if(!visited[next] && time + weight < dist[next]) {
                    dist[next] = time + weight;
                    pq.offer(new int[]{time + weight, next});
                }
            }
        }

        int maxTime = 0;
        for(int i = 1; i <= n; i++) {
            if(dist[i] == Integer.MAX_VALUE) return -1;
            maxTime = Math.max(maxTime, dist[i]);
        }

        return maxTime;
    }
}