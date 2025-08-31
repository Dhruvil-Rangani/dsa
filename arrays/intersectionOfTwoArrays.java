import java.util.*;
// Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.
// Time Complexity: O(n + m)
// Space Complexity: O(n)
// is this the most solution possible? // Yes, because we need to check each element at least once.
// this is acceptable only because the constraints are small (0 <= nums1[i], nums2[i] <= 1000)
// if the constraints were larger, we would have to use a HashSet instead of a boolean array.
// this is not a good solution if the range of numbers is large (like -10^9 to 10^9) because it would require a lot of space.
// and this is not a very general solution, it only works for small range of numbers.
class Solution1 {
    public int[] intersection(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        boolean[] seen = new boolean[1001];
        for(int num : nums1) seen[num] = true;

        int k = 0;
        int[] ans = new int[Math.min(n, m)];
        for(int num : nums2) {
            if(seen[num]) {
                seen[num] = false;
                ans[k++] = num;
            }
        }

        return Arrays.copyOfRange(ans, 0, k);
    }
}

// second approach using HashSet
// this is a more general solution and works for larger range of numbers.
// TC: O(n + m)
// SC: O(n)
// this code will work for large constraints as well (-10^9 <= nums1[i], nums2[i] <= 10^9)
// well-written interview ready code
// this is the best solution possible for this problem.
// we need to use a HashSet to store the elements of one array and then check for each element of the other array if it is present in the HashSet or not
// we cannot do better than O(n + m) time complexity because we need to check each element at least once.
// we cannot do better than O(n) space complexity because in the worst case, all elements of one array are unique and we need to store them in the HashSet.
// so, this is the best solution possible for this problem.
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) set1.add(num);

        int k = 0;
        int[] ans = new int[Math.min(n, m)];
        for(int i = 0; i < m; i++) {
            if(set1.contains(nums2[i])) {
                ans[k++] = nums2[i];
                set1.remove(nums2[i]);
            }
        }

        return Arrays.copyOfRange(ans, 0, k);
    }
}