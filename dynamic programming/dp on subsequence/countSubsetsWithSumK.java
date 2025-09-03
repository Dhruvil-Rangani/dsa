// https://www.codingninjas.com/codestudio/problems/count-of-subsets_3952530?leftPanelTab=0
// Time Complexity: O(n * K) where n is the number of elements in arr and K is the target sum
// Space Complexity: O(K) where K is the target sum

class Solution {
    private static final int MODULO = 1000000007;
    public int perfectSum(int[] arr, int K) {
        int n = arr.length;
        int[] dp = new int[K + 1];
        dp[0] = 1;

        if(arr[0] <= K) dp[arr[0]] = 1;

        for(int i = 1; i < n; i++) {
            int[] temp = new int[K + 1];
            temp[0] = 1;
            for(int j = 1; j <= K; j++) {
                int notPick = dp[j];
                int pick = 0;
                if(arr[i] <= j) {
                    pick = dp[j - arr[i]]; 
                }
                temp[j] = (pick + notPick) % MODULO;
            }
            dp = temp;
        }

        return dp[K];
    }
}

