package main
// frogJump calculates the minimum cost for a frog to jump from the first stone to the last stone.
// The cost of jumping from stone i to stone j is given by the absolute difference in their heights.
// The frog can jump up to k stones at a time.
// This function uses a recursive approach to achieve the solution.
// leetcode: https://leetcode.com/problems/frog-jump/
// Time Complexity: O(n * k) where n is the number of stones and k is the maximum jump length
// Space Complexity: O(n) for recursion stack space
// Recursive Approach
func funcRec(heights []int, ind int, k int) int {
    if ind == 0 {
        return 0
    }

    minStep := int(^uint(0) >> 1)

    for j := 1; j <= k; j++ {
        if ind - j >= 0 {
            diff := heights[ind] - heights[ind-j]
            if diff < 0 {
                diff = -diff
            }

            jump := funcRec(heights, ind - j, k) + diff

            if jump < minStep {
                minStep = jump
            }
        }
    }

    return minStep
}

func frogJump(heights []int, k int) int {
   ind := len(heights) - 1;

   return funcRec(heights, ind, k)
}

// memoization Approach
func funcRec2(heights []int, dp []int, ind int, k int) int {
    if ind == 0 {
        return 0
    }

    if dp[ind] != -1 {
        return dp[ind]
    }

    minStep := int(1e9)

    for j := 1; j <= k; j++ {
        if ind - j >= 0 {
            diff := heights[ind] - heights[ind-j]
            if diff < 0 {
                diff = -diff
            }

            jump := funcRec2(heights, dp, ind - j, k) + diff

            if jump < minStep {
                minStep = jump
            }
        }
    }

    dp[ind] = minStep
    return minStep
}

func frogJump2(heights []int, k int) int {
   ind := len(heights) - 1;
   dp := make([]int, ind + 1)
   for i := range dp {
    dp[i] = -1
   }

   return funcRec2(heights, dp, ind, k)
}

// tabulation Approach
func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func frogJump3(heights []int, k int) int {
	ind := len(heights)
	dp := make([]int, ind)
	for i := range dp {
		dp[i] = -1
	}
	dp[0] = 0

	for i := 1; i < ind; i++ {
		minSteps := int(1e9)

		for j := 1; j <= k; j++ {
			if i-j >= 0 {
				jump := dp[i-j] + abs(heights[i]-heights[i-j])
                if jump < minSteps {
				minSteps = jump
			}
			}
		}
        dp[i] = minSteps
	}
	return dp[ind-1]
}

// space optimized Approach
// Time Complexity: O(n * k)
// Space Complexity: O(1)
func abs2(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func frogJump4(heights []int, k int) int {
	ind := len(heights)
	dp := make([]int, k)

	for i := 1; i < ind; i++ {
		minSteps := int(1e9)

		for j := 1; j <= k; j++ {
			if i-j >= 0 {
                prevInd := (i - j) % k;
				jump := dp[prevInd] + abs2(heights[i]-heights[i-j])
                if jump < minSteps {
				minSteps = jump
			}
			}
		}
        dp[i % k] = minSteps
	}
	return dp[(ind-1) % k]
}



