package heaps.medium;
import java.util.PriorityQueue;
// Given an array of n elements, where each element is at most k away from its target position,
// devise an algorithm that sorts in O(n log k) time.   
// Time Complexity: O(n log k)
// Space Complexity: O(k)

class Solution {
    public void nearlySorted(int[] arr, int k) {
        // code here
        int n = arr.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i <= k; i++) {
            pq.add(arr[i]);
        }
        
        int j = 0;
        for(int i = k + 1; i < n; i++) {
            arr[j++] = pq.poll();
            pq.add(arr[i]);
        }
        
        while(!pq.isEmpty()) {
            arr[j++] = pq.poll();
        }
    }
}
