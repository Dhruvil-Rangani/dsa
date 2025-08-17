import java.util.*;
// LeetCode Problem: Making A Large Island
// This problem requires us to find the largest island size possible by converting at most one 0
// to 1 in a binary grid. We can use a Disjoint Set Union (DSU) to efficiently manage and connect components.
// Time Complexity: O(N * N * α(N^2)), where N is the grid dimension and α is the inverse Ackermann function.
// Space Complexity: O(N^2) for the DSU structure.

class Disjoint {
    int[] rank, parent, size;

    public Disjoint(int n) {
        rank = new int[n + 1];
        parent = new int[n + 1];
        size = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    boolean find(int u, int v) {
        return findUPar(u) == findUPar(v);
    }

    int findUPar(int node) {
        if(node == parent[node]) return node;
        return parent[node] = findUPar(parent[node]);
    }

    void unionByRank(int u, int v) {
        int ulPU = findUPar(u);
        int ulPV = findUPar(v);
        if(ulPU == ulPV) return;
        if(rank[ulPU] < rank[ulPV]) {
            parent[ulPU] = ulPV;
        } else if(rank[ulPV] < rank[ulPU]) {
            parent[ulPV] = ulPU;
        } else {
            parent[ulPV] = ulPU;
            rank[ulPU]++;
        }
    }

    void unionBySize(int u, int v) {
        int ulPU = findUPar(u);
        int ulPV = findUPar(v);
        if(ulPU == ulPV) return;
        if(size[ulPU] < size[ulPV]) {
            parent[ulPU] = ulPV;
            size[ulPV] += size[ulPU];
        } else {
            parent[ulPV] = ulPU;
            size[ulPU] += size[ulPV];
        }
    }
}

class Solution {
    private int[] delRow = {-1, 0, 1, 0};
    private int[] delCol = {0, 1, 0, -1};

    private boolean isValid(int i, int j, int n) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }

    private void addInitialIslands(int[][] grid, Disjoint ds, int n) {
        for(int row = 0; row < n; row++) {
            for(int col = 0; col < n; col++) {
                if(grid[row][col] == 0) continue;

                for(int ind = 0; ind < 4; ind++) {
                    int newRow = row + delRow[ind];
                    int newCol = col + delCol[ind];

                    if(isValid(newRow, newCol, n) && grid[newRow][newCol] == 1) {
                        int nodeNo = row * n + col;
                        int adjNodeNo = newRow * n + newCol;

                        ds.unionBySize(nodeNo, adjNodeNo);
                    }
                }
            }
        }
    }

    public int largestIsland(int[][] grid) {
      int n = grid.length;
      Disjoint ds = new Disjoint(n * n);
      addInitialIslands(grid, ds, n);

      int ans = 0;

      for(int row = 0; row < n; row++) {
        for(int col = 0; col < n; col++) {
            if(grid[row][col] == 1) continue;
            Set<Integer> components = new HashSet<>();
            for(int ind = 0; ind < 4; ind++) {
                int newRow = row + delRow[ind];
                int newCol = col + delCol[ind];

                if(isValid(newRow, newCol, n) && grid[newRow][newCol] == 1) {
                    int nodeNumber = newRow * n + newCol;
                    components.add(ds.findUPar(nodeNumber));
                }
            }

            int sizeTotal = 0;
            for(int parent : components) {
                sizeTotal += ds.size[ds.findUPar(parent)];
            }

            ans = Math.max(ans, sizeTotal + 1);
        }
      }

      for(int cellNo = 0; cellNo < n * n; cellNo++) {
        ans = Math.max(ans, ds.size[ds.findUPar(cellNo)]);
      }
      return ans;
    }
}


