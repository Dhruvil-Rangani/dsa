import java.util.*;
// House Robber II - Not Adjacent in Circular Manner
// This is a solution for the House Robber II problem using dynamic programming.
// The problem is to find the maximum amount of money that can be robbed from a series of houses arranged in a circle,
// where adjacent houses cannot be robbed on the same night.
// The code below implements a space-optimized approach to solve the problem.
// Time Complexity: O(n) where n is the number of houses
// Space Optimized Approach
// space complexity is not O(1) because we are creating two extra arrays of size n-1
// so overall space complexity is O(n)
class Solution {
     public int func(int[] money) {
        int n = money.length;
        int prev2 = 0, prev = money[0];

        for(int i = 1; i < n; i++) {
            int steal = money[i];
            if(i > 1) steal += prev2;
            int notSteal = prev;
            int curr = Math.max(steal, notSteal);
            prev2 = prev;
            prev = curr;
        }

        return prev;
    }

    public int rob(int[] money) {
        int n = money.length;
        if(n == 0) return 0;
        if(n == 1) return money[0];

        int[] arr1 = new int[n - 1];
        int[] arr2 = new int[n - 1];

        for(int i = 0; i < n; i++) {
            if(i != 0) arr1[i - 1] = money[i];
            if(i != n - 1) arr2[i] = money[i];
        }

        int ans1 = func(arr1);
        int ans2 = func(arr2);

        return Math.max(ans1, ans2);
    }
}