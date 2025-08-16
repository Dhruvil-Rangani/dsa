import java.util.*;
// LeetCode Problem: Accounts Merge
// This problem requires us to merge accounts based on shared email addresses.
// We can use a Disjoint Set Union (DSU) to efficiently manage and connect components
// Time Complexity: O(M * α(N)), where M is the total number of emails and α is the inverse Ackermann function.
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
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        Disjoint ds = new Disjoint(n);
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            for(int j = 1; j < accounts.get(i).size(); j++) {
                String mail = accounts.get(i).get(j);
                if(!map.containsKey(mail)){
                    map.put(mail, i);
                } else {
                    ds.unionBySize(i, map.get(mail));
                }
            }
        }

        List<String>[] mergedMail = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            mergedMail[i] = new ArrayList<>();
        }

        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            String mail = entry.getKey();
            int node = ds.findUPar(entry.getValue());
            mergedMail[node].add(mail);
        }

        List<List<String>> ans = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(mergedMail[i].isEmpty()) continue;
            Collections.sort(mergedMail[i]);
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0));
            temp.addAll(mergedMail[i]);
            ans.add(temp);
        }

        return ans;
    }
}