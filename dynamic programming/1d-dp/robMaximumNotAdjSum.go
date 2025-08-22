package main
// robMaximumNotAdjSum calculates the maximum sum of non-adjacent elements in an array.
// This function uses a recursive approach to achieve the solution.
// leetcode: https://leetcode.com/problems/house-robber/
// Time Complexity: O(2^n) where n is the number of elements in the array
// Space Complexity: O(n) for recursion stack space

func funcR(nums []int, ind int) int {
    if ind == 0 {
        return nums[ind]
    }
    if ind < 0 {
        return 0
    }

    pick := nums[ind] + funcR(nums, ind - 2)
    notpick := 0 + funcR(nums, ind - 1)

    return max(pick, notpick)
}
func nonAdjacent(nums []int) int {
	n := len(nums) - 1;

    return funcR(nums, n);
}

// robMaximumNotAdjSum with memoization
// This function uses a recursive approach with memoization to achieve the solution.
// leetcode: https://leetcode.com/problems/house-robber/
// Time Complexity: O(n) where n is the number of elements in the array
// Space Complexity: O(n) for the memoization array

func funcR1(nums []int, dp []int, ind int) int {
    if ind == 0 {
        return nums[ind]
    }
    if ind < 0 {
        return 0
    }

    if dp[ind] != -1 {
        return dp[ind]
    }

    pick := nums[ind] + funcR1(nums, dp, ind - 2)
    notpick := 0 + funcR1(nums, dp, ind - 1)

    dp[ind] = max(pick, notpick)
    return dp[ind]
}

func nonAdjacent1(nums []int) int {
	n := len(nums) - 1;
    dp := make([]int, n + 1)
    for i := range dp {
        dp[i] = -1;
    }
    return funcR1(nums, dp, n);
}


// robMaximumNotAdjSum with tabulation
// This function uses a tabulation approach to achieve the solution.
// leetcode: https://leetcode.com/problems/house-robber/
// Time Complexity: O(n) where n is the number of elements in the array
// Space Complexity: O(n) for the dp array

func nonAdjacent2(nums []int) int {
	n := len(nums);
    dp := make([]int, n)
    dp[0] = nums[0];

    for i:= 1; i < n; i++ {
        pick := nums[i];
        if i > 1 {
            pick += dp[i - 2];
        }
        notPick := dp[i - 1];

        dp[i] = max(pick, notPick);
    }
    return dp[n - 1];
}


// robMaximumNotAdjSum with space optimization
// This function uses a space-optimized approach to achieve the solution.
// leetcode: https://leetcode.com/problems/house-robber/
// Time Complexity: O(n) where n is the number of elements in the array
// Space Complexity: O(1) for constant space

func nonAdjacent3(nums []int) int {
	n := len(nums);
    prev2 := 0
    prev := nums[0]

    for i:= 1; i < n; i++ {
        pick := nums[i];
        if i > 1 {
            pick += prev2
        }
        notpick := prev
        curr := max(pick, notpick)
        prev2 = prev
        prev = curr
    }
    return prev;
}