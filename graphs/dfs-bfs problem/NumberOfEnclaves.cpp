#include <vector>
#include <set>
using namespace std;
#include <queue>
// LeetCode 1020. Number of Enclaves
// https://leetcode.com/problems/number-of-enclaves/
// This solution uses BFS to explore the grid and count the number of enclaves.
// The time complexity is O(n * m) where n is the number of rows and m is the number of columns in the grid.

class Solution {
private:
    vector<int> delRow = {-1, 0, 1, 0};
    vector<int> delCol = {0, 1, 0, -1};
    
    bool isValid(int row, int col, int n, int m) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }

    void dfs(int row, int col, vector<vector<bool>> &vis, vector<vector<int>> &grid, int n, int m) {
        vis[row][col] = true;

        for(int i = 0; i < 4; i++) {
            int nRow = row + delRow[i];
            int nCol = col + delCol[i];

            if(isValid(nRow, nCol, n, m) && !vis[nRow][nCol] && grid[nRow][nCol] == 1){
                dfs(nRow, nCol, vis, grid, n, m);
            }
        }
    }

    void bfs(int row, int col, vector<vector<bool>> &vis, vector<vector<int>> &grid, int n, int m) {
        queue<pair<int, int>> q;
        q.push({row, col});
        vis[row][col] = true;

        while(!q.empty()) {
            auto cell = q.front();
            q.pop();
            for(int i = 0; i < 4; i++) {
                int nRow = cell.first + delRow[i];
                int nCol = cell.second + delCol[i];
                if(isValid(nRow, nCol, n, m) && !vis[nRow][nCol] && grid[nRow][nCol] == 1) {
                    vis[nRow][nCol] = true;
                    q.push({nRow, nCol});
                }
            }
        }
    }
public:
    int numEnclaves(vector<vector<int>>& grid) {
        int n = grid.size();
        int m = grid[0].size();

        vector<vector<bool>> vis(n, vector<bool>(m, false));

        for(int i = 0; i < m; i++) {
            if(!vis[0][i] && grid[0][i] == 1) {
                bfs(0, i, vis, grid, n, m);
            }
            if(!vis[n - 1][i] && grid[n - 1][i] == 1) {
                bfs(n - 1, i, vis, grid, n, m);
            }
        }

        for(int i = 0; i < n; i++) {
            if(!vis[i][0] && grid[i][0] == 1) {
                bfs(i, 0, vis, grid, n, m);
            }
            if(!vis[i][m - 1] && grid[i][m - 1] == 1) {
                bfs(i, m - 1, vis, grid, n, m);
            }
        }

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!vis[i][j] &&  grid[i][j] == 1) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
};