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

