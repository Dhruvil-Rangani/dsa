import java.util.*;
// This is the Dijkstra's algorithm implementation using a TreeSet for priority queue functionality.
// It finds the shortest path from a source node to all other nodes in a weighted graph.
// The graph is represented as an adjacency list where each node points to a list of pairs (neighbor, weight).
// The algorithm initializes distances to all nodes as infinity (1e9) except for the source node, which is set to 0.
// It then processes nodes in order of their current shortest distance, updating the distances to neighboring nodes as shorter paths are found.
// The final distances are returned as an array.
// Time Complexity: O((V + E) log V), where V is the number of vertices and E is the number of edges.
// Space Complexity: O(V), for the distance array and the TreeSet.

class Solution
{
    public  int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
    {
       TreeSet<int[]> set = new TreeSet<>(Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a -> a[1]));
       int[] dist = new int[V];
       Arrays.fill(dist, (int)1e9);
       dist[S] = 0;

       set.add(new int[]{0, S});

       while(!set.isEmpty()) {
        int[] current = set.pollFirst();
        int dis = current[0];
        int node = current[1];

        for(List<Integer> adjNodes : adj.get(node)) {
            int curNode = adjNodes.get(0);
            int edgeWt = adjNodes.get(1);

            if(dis + edgeWt < dist[curNode]) {
                set.remove(new int[]{dist[curNode], curNode});
                dist[curNode] = dis + edgeWt;
                set.add(new int[]{dist[curNode], curNode});
            }
        }
       }
       return dist;
    }
}
