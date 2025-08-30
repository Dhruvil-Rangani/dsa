package main

func maxChocolates(g [][]int) int {
	n := len(g)
	m := len(g[0])

	dp := make([][]int, m)
	for i := range dp {
		dp[i] = make([]int, m)
	}

	for j1 := 0; j1 < m; j1++ {
		for j2 := 0; j2 < m; j2++ {
			if j1 == j2 {
				dp[j1][j2] = g[n - 1][j1]
			} else {
				dp[j1][j2] = g[n - 1][j1] + g[n - 1][j2]
			}
		}
	}

	dir := []int{-1, 0, 1}

	for i := n - 2; i >= 0; i-- {
		cur := make([][]int, m)
		for i := range cur {
			cur[i] = make([]int, m)
		}
		for j1 := 0; j1 < m; j1++ {
			for j2 := 0; j2 < m; j2++ {
				maxV := int(-1e9)
				for _, d1 := range dir {
					for _, d2 := range dir {
						nj1 := j1 + d1
						nj2 := j2 + d2
						if nj1 >= 0 && nj1 < m && nj2 >= 0 && nj2 < m {
							sum := 0
							if j1 == j2 {
								sum = g[i][j1]
							} else {
								sum = g[i][j1] + g[i][j2]
							}
							sum += dp[nj1][nj2]
							if sum > maxV {
								maxV = sum
							}
						}
					}
				}
				cur[j1][j2] = maxV
			}
		}
		dp = cur
	}

	return dp[0][m - 1]

}