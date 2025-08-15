import java.util.*;
// Disjoint Set Data Structure
// This code implements a Disjoint Set (Union-Find) data structure with path compression and union by rank.
// The Disjoint Set is used to keep track of a partition of a set into disjoint subsets.
// It supports two main operations:
// 1. Find: Determine which subset a particular element is in.
// 2. Union: Join two subsets into a single subset.
// The find operation uses path compression to flatten the structure of the tree whenever find is called, making future queries faster.
// The union operation uses union by rank to attach the smaller tree under the root of the larger
// tree, keeping the overall structure balanced.
// Time Complexity:
// - Find: O(α(n)), where α is the inverse Ackermann function, which grows very slowly.
// - Union: O(α(n))
// Space Complexity: O(n), where n is the number of elements in the disjoint set.

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
        
    }
}
