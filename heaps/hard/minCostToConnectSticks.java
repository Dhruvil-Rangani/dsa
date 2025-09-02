package heaps.hard;
import java.util.*;

// You have some sticks with positive integer lengths. You can connect any two sticks of lengths x and y into one stick by paying a cost of x + y. You want to connect all the sticks until there is only one stick remaining.
// Return the minimum cost of connecting all the given sticks into one stick in this way.
// Time Complexity: O(n log n) where n is the number of sticks
// Space Complexity: O(n)

class Solution {
    public int connectSticks(List<Integer> sticks) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i = 0; i < sticks.size(); i++) {
            minHeap.offer(sticks.get(i));
        }

        int cost = 0;
        while(!minHeap.isEmpty() && minHeap.size() > 1) {
            int first = minHeap.poll(), second = minHeap.poll();
            int merge = first + second;
            cost += merge;
            minHeap.offer(merge);
        }

        return cost;
    }
}
