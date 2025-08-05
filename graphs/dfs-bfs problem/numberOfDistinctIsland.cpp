#include <vector>
#include <set>
using namespace std;
// LeetCode 694. Number of Distinct Islands
// https://leetcode.com/problems/number-of-distinct-islands/
// This solution uses DFS to explore the grid and find distinct islands.
// It stores the relative coordinates of each island in a set to ensure uniqueness.
// The time complexity is O(n * m * log(n * m)) where n is the number of rows and m is the number of columns in the grid.

class Solution
{
private:
    vector<int> delRow = {-1, -0, 1, 0};
    vector<int> delCol = {0, 1, 0, -1};

    bool isValid(int i, int j, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    void dfs(int row, int col, vector<vector<int>> &grid, vector<vector<bool>> &visited, vector<pair<int,int>> &path, int &baseRow, int &baseCol) {
        int n = grid.size();
        int m = grid[0].size();
        visited[row][col] = true;
        path.push_back({row - baseRow, col - baseCol});
        for(int i = 0; i < 4; i++) {
            int nRow = row + delRow[i];
            int nCol = col + delCol[i];

            if(isValid(nRow, nCol, n, m) && grid[nRow][nCol] == 1 && !visited[nRow][nCol]){
                dfs(nRow, nCol, grid, visited, path, baseRow, baseCol);
            }
        }
    }
public:
    int countDistinctIslands(vector<vector<int>> &grid){
        int n = grid.size();
        int m = grid[0].size();
        vector<vector<bool>> visited(n, vector<bool>(m, false));
        
        set<vector<pair<int,int>>> st;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    vector<pair<int,int>> path;
                    dfs(i, j, grid, visited, path, i, j);
                    st.insert(path);
                }
            }
        }

        // n x m x log(n x m) + (n x m x 4)
        return st.size();
        
    }
};
