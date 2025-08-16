import java.util.*;
// LeetCode Problem: Most Stones Removed with Same Row or Column
// This problem requires us to find the maximum number of stones that can be removed
// while ensuring that at least one stone remains in each row and column.
// We can use a Disjoint Set Union (DSU) to efficiently manage and connect components
// Time Complexity: O(S * α(N)), where S is the number of stones and α is the inverse Ackermann function.
// Space Complexity: O(N) for the DSU structure.

class DisjointSet {
    int[] rank, parent, size;

    DisjointSet(int n) {
        rank = new int[n + 1];
        parent = new int[n + 1];
        size = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            parent[i] = i;
            size[i] = i;
        }
    }

    int findUPar(int node) {
        if(node == parent[node]) return node;
        return parent[node] = findUPar(parent[node]);
    }

    void unionByRank(int u, int v) {
        int ulPU = findUPar(u);
        int ulPV = findUPar(v);
        if(ulPU == ulPV) return;
        if(rank[ulPU] < rank[ulPV]){
            parent[ulPU] = ulPV;
        } else if(rank[ulPU] > rank[ulPV]) {
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
    public int maxRemove(int[][] stones, int n) {
        int maxRow = 0;
        int maxCol = 0;

        for(int[] stone: stones) {
            maxRow = Math.max(maxRow, stone[0]);
            maxCol = Math.max(maxCol, stone[1]);
        }

        DisjointSet ds = new DisjointSet(maxRow + maxCol + 1);

        Map<Integer, Integer> stoneNodes = new HashMap<>();

        for(int[] stone : stones) {
            int nodeRow = stone[0];
            int nodeCol = stone[1] + maxRow + 1;

            ds.unionBySize(nodeRow, nodeCol);
            stoneNodes.put(nodeRow, 1);
            stoneNodes.put(nodeCol, 1);
        }

        int k = 0;
        for(int key : stoneNodes.keySet()) {
            if(ds.findUPar(key) == key) k++;
        }

        return n - k;
    }
};
