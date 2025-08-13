import java.util.*;
// LeetCode Problem: Number of Ways to Arrive at Destination
// https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/
// Time Complexity: O((N + M) * log(N)), where N is the number
// of nodes and M is the number of edges.
// Space Complexity: O(N + M), for the adjacency list and distance array.
// This code finds the number of ways to arrive at the destination node in a weighted undirected graph.

class Solution {
    public int countPaths(int n, int[][] roads) {
        int mod = (int)(1e9 + 7);

        List<int[]>[] adj = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] road : roads) {
            adj[road[0]].add(new int[]{road[1], road[2]});
            adj[road[1]].add(new int[]{road[0], road[2]});
        }

        long[] minTime = new long[n];
        Arrays.fill(minTime, Long.MAX_VALUE);
        int[] ways = new int[n];

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, 0});
        ways[0] = 1;
        minTime[0] = 0;

        while(!pq.isEmpty()) {
            long[] p = pq.poll();
            long time = p[0];
            int node = (int)p[1];

            if(time > minTime[node]) continue;
            for(int[] adjNodes : adj[node]) {
                int adjNode = adjNodes[0];
                int travelTime = adjNodes[1];

                if(minTime[adjNode] > time + travelTime) {
                    minTime[adjNode] = time + travelTime;
                    ways[adjNode] = ways[node];
                    pq.offer(new long[]{time + travelTime, adjNode});
                } else if(minTime[adjNode] == time + travelTime) {
                    ways[adjNode] = (ways[node] + ways[adjNode])%mod;
                }
            }
        }

        return ways[n - 1]%mod;
    }
}