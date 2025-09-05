// space optimization approach
// Time Complexity: O(n * K) where n is the number of elements in arr and K is the target sum
// Space Complexity: O(K) where K is the target sum

class Solution {
    public int perfectSum(int[] arr, int K) {
      int n = arr.length;
      int[] dp = new int[K + 1];
      dp[0] = 1;
      if(arr[0] <= K) {
        if(arr[0] == 0) dp[arr[0]] = 2;
        else dp[arr[0]] = 1;
      }

      for(int i = 1; i < n; i++) {
        int[] temp = new int[K + 1];
        for(int j = 0; j <= K; j++) {
          int notPick = dp[j];
          int pick = 0;
          if(arr[i] <= j) pick = dp[j - arr[i]];
          temp[j] = (pick + notPick);
        }
        dp = temp;
      }      
      return dp[K];
    }

    public int countPartitions(int n, int diff, int[] arr) {
      int sum = 0;
      for (int num : arr) sum += num;
      if (((sum - diff) < 0) || (((sum - diff) & 1) == 1)) return 0;
      return perfectSum(arr, (sum - diff) / 2);
    }

    public int findTargetSumWays(int[] nums, int target) {
        return countPartitions(nums.length, target, nums);
    }
}