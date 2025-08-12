import java.util.*;
// LeetCode Problem: Minimum Effort Path
// https://leetcode.com/problems/path-with-minimum-effort/
// Time Complexity: O(N * M * log(N * M)), where N is the number
// of rows and M is the number of columns.
// Space Complexity: O(N * M), for the maxDiff array and the priority queue.

class Solution {
    private int[] delRow = {-1, 0, 1, 0};
    private int[] delCol = {0, 1, 0, -1};

    private boolean isValid(int row, int col, int n, int m) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }

    public int MinimumEffort(List<List<Integer>> heights) {
        int n = heights.size();
        int m = heights.get(0).size();

        int[][] maxDiff = new int[n][m];
        for(int[] row : maxDiff) {
            Arrays.fill(row, (int)1e9);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        maxDiff[0][0] = 0;
        pq.add(new int[]{0, 0, 0});

        while(!pq.isEmpty()) {
            int[] p = pq.poll();
            int diff = p[0];

            int row = p[1];
            int col = p[2];

            if(row == n - 1 && col == m - 1) return diff;

            for(int i = 0; i < 4; i++) {
                int nRow = row + delRow[i];
                int nCol = col + delCol[i];

                if(isValid(nRow, nCol, n, m)) {
                    int currDiff = Math.abs(heights.get(nRow).get(nCol) - heights.get(row).get(col));
                    if(Math.max(currDiff, diff) < maxDiff[nRow][nCol]) {
                        maxDiff[nRow][nCol] = Math.max(currDiff, diff);
                        pq.add(new int[]{Math.max(currDiff, diff), nRow, nCol});
                    }
                }
            }
        }

        return -1;
    }
}
