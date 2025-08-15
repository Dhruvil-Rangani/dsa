import java.util.*;
// LeetCode Problem: Number of Provinces
// https://leetcode.com/problems/number-of-provinces/
// Time Complexity: O(N^2), where N is the number of nodes.
// Space Complexity: O(N), for the disjoint set data structure.
// This code uses a disjoint set to find the number of connected components in an undirected graph.
// It counts the number of provinces (connected components) in a graph represented by an adjacency matrix.
// The disjoint set (Union-Find) is used to efficiently manage and merge connected components.
// The findCircleNum method initializes the disjoint set and iterates through the adjacency matrix to union connected nodes.

class DisjointSet {
    private int[] rank;
    private int[] size;
    private int[] parent;

    public DisjointSet(int n) {
      rank = new int[n + 1];
      parent = new int[n + 1];
      size = new int[n + 1];
      Arrays.fill(size, 1);

      for(int i = 0; i <= n; i++) parent[i] = i;
    }

    public int findUPar(int node) {
        if(node == parent[node]) return node;
        return parent[node] = findUPar(parent[node]);
    }

    public boolean find(int u, int v) {
        return (findUPar(u) == findUPar(v));
    }

    public void unionByRank(int u, int v) {
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

    public void unionBySize(int u, int v) {
        int ulPU = findUPar(u);
        int ulPV = findUPar(v);

        if(ulPU == ulPV) return;

        if(size[ulPU] < size[ulPV]){
            parent[ulPU] = ulPV;
            size[ulPV] += size[ulPU];
        } else {
            parent[ulPV] = ulPU;
            size[ulPU] += size[ulPV];
        }
    }
}

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        List<Integer>[] adj = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        DisjointSet ds = new DisjointSet(n);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(isConnected[i][j] == 1) ds.unionBySize(i, j);
            }
        }

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(ds.findUPar(i) == i) cnt++;
        }

        return cnt;
    }
}