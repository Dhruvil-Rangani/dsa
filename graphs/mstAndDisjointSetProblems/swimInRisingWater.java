import java.util.*;
// LeetCode Problem: Swim in Rising Water
// https://leetcode.com/problems/swim-in-rising-water/
// Time Complexity: O(N^2 log N), where N is the number of nodes.
// Space Complexity: O(N^2), for the priority queue and visited array.

class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{grid[0][0], 0, 0});

        boolean[][] vis = new boolean[n][n];

        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            int elevation = current[0], row = current[1], col = current[2];

            if(vis[row][col]) continue;
            vis[row][col] = true;

            if(row == n - 1 && col == n - 1) return elevation;

            for(int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if(newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && !vis[newRow][newCol]) {
                    pq.offer(new int[]{Math.max(elevation, grid[newRow][newCol]), newRow, newCol});
                }
            }
        }

        return -1;
    }
}