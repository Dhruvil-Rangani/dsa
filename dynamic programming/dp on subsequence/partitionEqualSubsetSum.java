// Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
// The problem can be reduced to finding a subset of the given array with a sum equal to half of the total sum of the array.
// If the total sum is odd, it is not possible to partition the array into two equal subsets, so we return false.
// If the total sum is even, we can use a dynamic programming approach to determine if there exists a subset with a sum equal to half of the total sum.
// Time Complexity: O(n * target) + O(n) where n is the number of elements in the array and target is half of the total sum
// Space Complexity: O(target + 1) for the dp array used in the space-optimized approach 
class Solution {
     public boolean isSubsetSum(int[] arr, int target) {
      int n = arr.length;
      boolean[] dp = new boolean[target + 1];
      dp[0] = true;

      if(arr[0] <= target) {
        dp[arr[0]] = true;
      }

      for(int i = 1; i < n; i++) {
        boolean[] temp = new boolean[target + 1];
        temp[0] = true;
        for(int j = 1; j <= target; j++) {
            boolean notPick = dp[j];
            boolean pick = false;
            if(arr[i] <= j) {
                pick = dp[j - arr[i]];
            }
            temp[j] = pick || notPick;
        }
        dp = temp;
      }
      return dp[target];
    }

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int totalSum = 0;
        for(int i = 0; i < n; i++) totalSum += nums[i];
        if((totalSum & 1) == 1) return false;
        totalSum = totalSum / 2;

        return isSubsetSum(nums, totalSum);
    }
}