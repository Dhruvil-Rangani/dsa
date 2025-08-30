import java.util.PriorityQueue;
// Given an array of n elements, where each element is at most k away from its target
// position, design a class to efficiently find the kth largest element in a stream of numbers.
// Note that it is the kth largest element in the sorted order, not the kth distinct element
// Time Complexity: O(n log k) for constructor, O(log k) for add method
// Space Complexity: O(k)

class KthLargest {
    private int k;
    private PriorityQueue<Integer> pq;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>();
        for(int i = 0; i < nums.length; i++) {
            if(pq.size() < k) pq.add(nums[i]);
            else if(nums[i] > pq.peek()) {
                pq.poll();
                pq.add(nums[i]);
            }
        }
    }

    public int add(int val) {
        if(pq.size() < k) {
            pq.add(val);
            return pq.peek();
        }

        if(val > pq.peek()) {
            pq.poll();
            pq.offer(val);
        }

        return pq.peek();
    }
}
