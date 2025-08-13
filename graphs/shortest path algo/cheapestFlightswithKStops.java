import java.util.*;
// LeetCode Problem: Cheapest Flights Within K Stops
// https://leetcode.com/problems/cheapest-flights-within-k-stops/
// Time Complexity: O(N * K + M), where N is the number of nodes, K is the maximum number of stops, and M is the number of edges.
// Space Complexity: O(N * K), for the cost array and the adjacency list.

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] flight : flights) {
            adj.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }

        // cost[node][stops] = min cost to reach node with stops
        int[][] cost = new int[n][k + 2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }
        cost[src][0] = 0;

        // Queue state: node, totalCost, stops
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{src, 0, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0];
            int curCost = cur[1];
            int stops = cur[2];

            if (stops > k) continue;

            for (int[] nei : adj.get(node)) {
                int next = nei[0];
                int price = nei[1];
                int newCost = curCost + price;

                if (newCost < cost[next][stops + 1]) {
                    cost[next][stops + 1] = newCost;
                    q.offer(new int[]{next, newCost, stops + 1});
                }
            }
        }

        // Find the cheapest cost among all stop counts up to K+1
        int ans = Integer.MAX_VALUE;
        for (int s = 0; s <= k + 1; s++) {
            ans = Math.min(ans, cost[dst][s]);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}