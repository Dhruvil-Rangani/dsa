import java.util.*;
// finding the weight of the minimum spanning tree using Prim's algorithm
// This code implements Prim's algorithm to find the weight of the minimum spanning tree (MST) of a graph represented as an adjacency list.
// The algorithm uses a priority queue to efficiently select the edge with the minimum weight that connects a vertex in the MST to a vertex outside the MST.
// The input is a number of vertices and an adjacency list where each entry contains a list of edges with their weights.
// Time Complexity: O(E log V), where E is the number of edges and V is the number of vertices.
// Space Complexity: O(V + E), where V is the number of vertices and E is the number of edges.

class Solution {
    public int spanningTree(int V, List<List<List<Integer>>> adj) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        boolean[] visited = new boolean[V];

        pq.add(new int[]{0, 0});
        int sum = 0;

        while(!pq.isEmpty()) {
            int[] p = pq.poll();

            int node = p[1];
            int wt = p[0];

            if(visited[node]) continue;

            visited[node] = true;

            sum += wt;

            for(List<Integer> adjNode : adj.get(node)) {
                int nextNode = adjNode.get(0);
                int edgeWt = adjNode.get(1);

                if(!visited[nextNode]) {
                    pq.add(new int[]{edgeWt, nextNode});
                }
            }
        }

        return sum;
    }
}

// Solution 2 implements Kruskal's algorithm to find the weight of the minimum spanning tree (MST) of a graph represented as an edge list.
// The algorithm sorts the edges by weight and uses a disjoint set data structure to efficiently check for cycles while adding edges to the MST.
// The input is a number of vertices and an edge list where each edge is represented by a list containing two vertices and the weight of the edge.
// Time Complexity: O(E log E + E log V), where E is the number of edges and V is the number of vertices.
// Space Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
// This code is a part of the Disjoint Set Data Structure implementation.

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

class Solution2 {
    public int spanningTree(int V, List<List<List<Integer>>> adj) {
        List<int[]> edges = new ArrayList<>();

        for(int i = 0; i < V; i++) {
            for(List<Integer> it : adj.get(i)) {
                int v = it.get(0);
                int wt = it.get(1);
                int u = i;

                edges.add(new int[]{wt, u, v});
            }
        }

        DisjointSet ds = new DisjointSet(V);
        edges.sort(Comparator.comparingInt(o -> o[0]));

        int sum = 0;

        for(int[] edge : edges) {
            int wt = edge[0];
            int u = edge[1];
            int v = edge[2];

            if(ds.findUPar(u) != ds.findUPar(v)) {
                sum += wt;
                ds.unionBySize(u, v);
            }
        }

        return sum;
    }
}



