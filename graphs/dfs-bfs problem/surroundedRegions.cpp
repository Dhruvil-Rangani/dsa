#include <vector>
#include <set>
using namespace std;
// LeetCode 130. Surrounded Regions
// https://leetcode.com/problems/surrounded-regions/
// This solution uses DFS to mark all 'O's connected to the border as safe.
// The time complexity is O(n * m) where n is the number of rows and m is the number of columns in the grid.

class Solution {
private:
    vector<int> delRow = {-1, 0, 1, 0};
    vector<int> delCol = {0, 1, 0, -1};
    
    bool isValid(int row, int col, int n, int m) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }

    void dfs(int row, int col, vector<vector<bool>> &vis, vector<vector<char>> &board, int n, int m) {
        vis[row][col] = true;

        for(int i = 0; i < 4; i++) {
            int nRow = row + delRow[i];
            int nCol = col + delCol[i];

            if(isValid(nRow, nCol, n, m) && !vis[nRow][nCol] && board[nRow][nCol] == 'O'){
                dfs(nRow, nCol, vis, board, n, m);
            }
        }
    }
public:
    void solve(vector<vector<char>>& board) {
        int n = board.size();
        int m = board[0].size();

        vector<vector<bool>> vis(n, vector<bool>(m, false));

        for(int i = 0; i < m; i++) {
            if(!vis[0][i] && board[0][i] == 'O') {
                dfs(0, i, vis, board, n, m);
            }
            if(!vis[n - 1][i] && board[n - 1][i] == 'O') {
                dfs(n - 1, i, vis, board, n, m);
            }
        }

        for(int i = 0; i < n; i++) {
            if(!vis[i][0] && board[i][0] == 'O') {
                dfs(i, 0, vis, board, n, m);
            }
            if(!vis[i][m - 1] && board[i][m - 1] == 'O') {
                dfs(i, m - 1, vis, board, n, m);
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!vis[i][j] &&  board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
};