// Time Complexity: O(V^3)
// Space Complexity: O(1), since we are modifying the input matrix in place.
// This implementation of the Floyd-Warshall algorithm finds the shortest paths between all pairs of vertices in a weighted graph.

class Solution {
    public void shortest_distance(int[][] matrix) {
        int n = matrix.length;

        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(matrix[i][k] == -1 || matrix[k][j] == -1) continue;

                    if(matrix[i][j] == -1) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    } else {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    }
                }
            }
        }
    }
}

