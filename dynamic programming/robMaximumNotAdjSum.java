// robMaximumNotAdjSum.java
// leetcode: https://leetcode.com/problems/house-robber/
// Time Complexity: O(2^n) where n is the number of elements in the array
// Space Complexity: O(n) for recursion stack space
import java.util.Arrays;
class Solution1 {
    private int func(int[] nums, int ind) {
        if(ind == 0) return nums[ind];
        if(ind < 0) return 0;

        int pick = nums[ind] + func(nums, ind - 2);
        int notPick = 0 + func(nums, ind - 1);

        return Math.max(pick, notPick);
    }
    
    public int nonAdjacent(int[] nums) {
        int n = nums.length - 1;

        return func(nums, n);
    }
}


// robMaximumNotAdjSum.java
// leetcode: https://leetcode.com/problems/house-robber/
// Time Complexity: O(n) where n is the number of elements in the array
// Space Complexity: O(n) for memoization array

class Solution2 {
    private int func(int[] nums, int[] dp, int ind) {
        if(ind == 0) return nums[ind];
        if(ind < 0) return 0;

        if(dp[ind] != -1) return dp[ind];

        int pick = nums[ind] + func(nums, dp, ind - 2);
        int notPick = func(nums, dp, ind - 1);

        return dp[ind] = Math.max(pick, notPick);
    }
    
    public int nonAdjacent(int[] nums) {
        int n = nums.length - 1;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return func(nums, dp, n);
    }
}

// Space Optimized Approach
// robMaximumNotAdjSum.java
class Solution3 {
    public int nonAdjacent(int[] nums) {
        int n = nums.length;
        int prev = nums[0], prev2 = 0;;
        
        for(int i = 1; i < n; i++) {
            int take = nums[i];
            if (i > 1) {
                take += prev2;
            }
            int notTake = prev;
            int curr = Math.max(take, notTake);
            prev2 = prev;
            prev = curr;
        }

        return prev;
    }
}
