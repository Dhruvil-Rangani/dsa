package heaps.hard;
import java.util.*;

// Given two integer arrays nums1 and nums2 of size n and m respectively, return the maximum k valid combinations from nums1 and nums2.
// A valid combination is a sum of one element from nums1 and one element from nums2.
// The result can be returned in any order.
// Time Complexity: O(n * m * log k) where n and m are the sizes of nums1 and nums2 respectively
// Space Complexity: O(k)

class Solution1 {
    public int[] maxSumCombinations(int[] nums1, int[] nums2, int k) {
      // init min-heap
      PriorityQueue<Integer> minHeap = new PriorityQueue<>();
      
      // two nested loops 
      for(int i = 0; i < nums1.length; i++) {
        for(int j = 0; j < nums2.length; j++) {
          int sum = nums1[i] + nums2[j];
          if(minHeap.size() < k) {
            minHeap.offer(sum);
          } else {
            if(sum > minHeap.peek())  {
              minHeap.poll();
              minHeap.offer(sum);
            } 
          }
        }
      }


      int[] ans = new int[k];
      for(int i = k - 1; i >= 0; i--) {
        ans[i] = minHeap.poll();
      }

      return ans;
    }
}

// Given two integer arrays nums1 and nums2 of size n and m respectively, return the maximum k valid combinations from nums1 and nums2.
// A valid combination is a sum of one element from nums1 and one element from nums2.
// The result can be returned in any order.
// Time Complexity: O(k log k) + O(n log n) + O(m log m) + O(n) + O(m) where n and m are the sizes of nums1 and nums2 respectively 
// Space Complexity: O(k)

// (most optimal approach)
class Solution2 {
  private void reverse(int[] arr) {
    for (int i = 0; i < arr.length / 2; i++) {
      int temp = arr[i];
      arr[i] = arr[arr.length - 1 - i];
      arr[arr.length - 1 - i] = temp;
    }
  }

  public int[] maxSumCombinations(int[] nums1, int[] nums2, int k) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    reverse(nums1); // Descending
    reverse(nums2); // Descending
    // init min-heap
    PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
    Set<String> visited = new HashSet<>();
    maxHeap.offer(new int[] {nums1[0] + nums2[0], 0, 0});
    visited.add("0:0");

    int[] ans = new int[k];
    for (int i = 0; i < k; i++) {
      int[] top = maxHeap.poll();
      ans[i] = top[0];
      int x = top[1], y = top[2];

      if(x + 1 < nums1.length && !visited.contains((x + 1) + ":" + y)) {
        visited.add((x + 1) + ":" + y);
        maxHeap.offer(new int[]{nums1[x + 1] + nums2[y], x + 1, y});
      }
      if(y + 1 < nums2.length && !visited.contains(x + ":" + (y + 1))){
        visited.add(x + ":" + (y + 1));
        maxHeap.offer(new int[]{nums1[x] + nums2[y + 1], x, y + 1});
      }
    }

    return ans;
  }
}
