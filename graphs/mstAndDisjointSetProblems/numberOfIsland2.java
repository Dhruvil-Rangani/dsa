import java.util.*;
// LeetCode Problem: Number of Islands II
// This problem requires us to count the number of islands after each land addition.
// We can use a Disjoint Set Union (DSU) to efficiently manage and connect components
// Time Complexity: O(K * α(N)), where K is the number of land additions and α is the inverse Ackermann function.
// Space Complexity: O(N) for the DSU structure.

class Disjoint {
    int[] rank, parent, size;

    public Disjoint(int n) {
        rank = new int[n + 1];
        parent = new int[n + 1];
        size = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            parent[i] = i;
            size[i] = i;
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
        if(parent[ulPU] < parent[ulPV]) {
            parent[ulPU] = ulPV;
            size[ulPV] += size[ulPU];
        } else {
            parent[ulPV] = ulPU;
            size[ulPU] += size[ulPV];
        }
    }
}

class Solution {
    int[] delRow = {-1, 0, 1, 0};
    int[] delCol = {0, 1, 0, -1};

    boolean isValid(int i , int j , int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    public List<Integer> numOfIslands(int n, int m, int[][] A) {
        Disjoint ds = new Disjoint(n * m);
        boolean[][] vis = new boolean[n][m];
        for(boolean[] row : vis) Arrays.fill(row, false);

        List<Integer> ans = new ArrayList<>();
        int cnt = 0;

        for(int[] it : A) {
            int row = it[0];
            int col = it[1];

            if(vis[row][col]) {
                ans.add(cnt);
                continue;
            }

            vis[row][col] = true;
            cnt++;

            for(int ind = 0; ind < 4; ind++) {
                int newRow = row + delRow[ind];
                int newCol = col + delCol[ind];

                if(isValid(newRow, newCol, n, m) && vis[newRow][newCol]){
                    int nodeNo = row * m + col;
                    int adjNodeNo = newRow * m + newCol;

                    if(ds.findUPar(nodeNo) != ds.findUPar(adjNodeNo)) {
                        cnt--;
                        ds.unionBySize(nodeNo, adjNodeNo);
                    }
                }
            }

            ans.add(cnt);
        }

        return ans;
    }
}


