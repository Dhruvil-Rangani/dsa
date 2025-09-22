#include <bits/stdc++.h>
using namespace std;


class Solution {
private:
    int largestRectangleArea(vector<int>& heights) {
        int n = heights.size();
        stack<int> s;
        int largestArea = 0;
        int area;
        int nse, pse;

        for(int i = 0; i < n; i++) {
            while(!s.empty() && heights[s.top()] >= heights[i]) {
                int ind = s.top();
                s.pop();
                pse = s.empty() ? - 1 : s.top();
                nse = i;
                area = heights[ind] * (nse - pse - 1);
                largestArea = max(largestArea, area);
            }
            s.push(i);
        }

        while(!s.empty()) {
            nse = n;
            int ind = s.top();
            s.pop();

            pse = s.empty() ? -1 : s.top();
            area = heights[ind] * (nse - pse - 1);
            largestArea = max(largestArea, area);
        }
        return largestArea;
    }

public:
    int maximalRectangle(vector<vector<char>> &matrix) {
        int n = matrix.size();
        int m = matrix[0].size();

        vector<vector<int>> prefixSum(n, vector<int>(m));

        for(int j = 0; j < m; j++) {
            int sum =  0;
            for(int i = 0; i < n; i++) {
                if (matrix[i][j] == '1') {
                    sum++;
                } else {
                    sum = 0;
                }
                prefixSum[i][j] = sum;
            }
        }

        int maxArea = 0;
        for(int i = 0; i < n; i++) {
            int area = largestRectangleArea(prefixSum[i]);
            maxArea = max(area, maxArea);
        }
        return maxArea;
    }
};