// Infinite Knapsack
// recursive approach
// TC: >> O(2^N)
// SC: >> O(N) for recursion stack
// TC and SC are not exactly this, it is going to be more than this, but this is the upper bound.
// because we are making two calls at each step, so it is going to be more than this.
// but this is not the optimal solution, we can do better than this using memoization or tabulation.
// this is just to understand the problem and the recursive approach.
class Solution {
    public int f(int i, int target, int[] wt, int[] val) {
        if(i == 0) {
            return (target / wt[0]) * val[0];
        }
        int nope = f(i - 1, target, wt, val);
        int yes = (int)(-1e9);
        if(wt[i] <= target) yes = val[i] + f(i, target - wt[i], wt, val);
        
        return Math.max(nope, yes);
    }

    public int unboundedKnapsack(int[] wt, int[] val, int n, int W) {
        return f(n - 1, W, wt, val);
    }
}

// memoization approach

