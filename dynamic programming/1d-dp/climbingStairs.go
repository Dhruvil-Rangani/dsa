package main
// climbStairs calculates the number of distinct ways to climb to the top of a staircase with n steps.
// You can either take 1 step or 2 steps at a time.
// This function uses an iterative approach to achieve O(n) time complexity and O(1) space complexity.
// leetcode: https://leetcode.com/problems/climbing-stairs/

func climbStairs(n int) int {
	prev2 := 1
    prev1 := 1

    for i := 2; i <= n; i++ {
        curr := prev1 + prev2
        prev2 = prev1
        prev1 = curr
    }

    return prev1
}
