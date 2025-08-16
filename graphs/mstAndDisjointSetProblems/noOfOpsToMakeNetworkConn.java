import java.util.*;
// LeetCode Problem: Number of Operations to Make Network Connected
// This problem requires us to connect all computers in a network with the minimum number of operations.
// We can use a Disjoint Set Union (DSU) to efficiently manage and connect components
// Time Complexity: O(M * α(N)), where M is the number of connections and α is the inverse Ackermann function.
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
    public int makeConnected(int n, int[][] connections) {
        int size = connections.length;
      if(size < n - 1) return -1;
      DisjointSet ds = new DisjointSet(n);
      for(int i = 0; i < size; i++) {
        ds.unionBySize(connections[i][0], connections[i][1]);
      }

      int count = 0;
      for(int i = 0; i < n; i++) {
        if(ds.parent[i] == i) count++;
      }

      return count -1;
    }
}