#include <vector>
#include <queue>
using namespace std;
// LeetCode 542. 01 Matrix
// https://leetcode.com/problems/01-matrix/
// This solution uses BFS to find the distance of each cell from the nearest 0.
// The time complexity is O(n * m) where n is the number of rows and m is the number of columns in the matrix.

class Solution {
    private:
    vector<int> delRow = {-1, 0, 1, 0};
    vector<int> delCol = {0, 1, 0, -1};

    bool isValid(int row, int col, int n, int m) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }
    
public:
    vector<vector<int>> updateMatrix(vector<vector<int>>& mat) {
        int n = mat.size();
        int m = mat[0].size();

        vector<vector<int>> vis(n, vector<int>(m, 0));
        vector<vector<int>> dist(n, vector<int>(m, 0));
        queue<pair<pair<int,int>, int>> q;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++){
                if(mat[i][j] == 0) {
                    q.push({{i,j}, 0});
                    vis[i][j] = 1;
                }
            }
        }

        while(!q.empty()) {
            int row = q.front().first.first;
            int col = q.front().first.second;
            int steps = q.front().second;
            q.pop();
            dist[row][col] = steps;

            for(int i = 0; i < 4; i++) {
                int nRow = row + delRow[i];
                int nCol = col + delCol[i];

                if(isValid(nRow, nCol, n, m) && vis[nRow][nCol] == 0) {
                    vis[nRow][nCol] = 1;
                    q.push({{nRow, nCol}, steps + 1});
                }
            }
        }

        return dist;
    }
};