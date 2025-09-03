// https://practice.geeksforgeeks.org/problems/minimum-sum-partition3317/1
// Time Complexity: O(n * target) where n is the number of elements in arr and target is the total sum of elements in arr
// Space Complexity: O(target) where target is the total sum of elements in arr

class Solution {
  public int minDifference(int[] arr, int n) {
    int totalSum = 0;
    for (int num : arr) totalSum += num;

    int target = totalSum;
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;

    if (arr[0] <= target) {
      dp[arr[0]] = true;
    }

    for (int i = 1; i < n; i++) {
      boolean[] temp = new boolean[target + 1];
      temp[0] = true;
      for (int j = 1; j <= target; j++) {
        boolean notPick = dp[j];
        boolean pick = false;
        if (arr[i] <= j) {
          pick = dp[j - arr[i]];
        }
        temp[j] = pick || notPick;
      }
      dp = temp;
    }

    int minSum = (int)(1e9);
    for(int s1 = 0; s1 <= totalSum / 2; s1++){
        if(dp[s1]) {
            minSum = Math.min(minSum, Math.abs((totalSum - s1) - s1));
        }
    }

    return minSum;
  }
}

