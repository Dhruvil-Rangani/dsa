import java.util.*;
// You are given an array of stones where stones[i] is the weight of the ith stone.
// We are playing a game with the stones. On each turn, we choose the two heaviest stones and smash them together.
// Suppose the stones have weights x and y with x <= y. The result of this smash is:
// If x == y, both stones are destroyed, and
// If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
// At the end of the game, there is at most one stone left.
// Return the weight of the last remaining stone. If there are no stones left, return 0.
// Time Complexity: O(n log n)
// Space Complexity: O(n)
// is this the most solution possible? // Yes, because we need to process each stone at least once, and each operation on the heap takes O(log n) time.

class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for(int stone : stones) maxHeap.add(stone);

        while(!maxHeap.isEmpty()) {
            int size = maxHeap.size();
            if(size == 1) return maxHeap.poll();

            int x = maxHeap.poll();
            int y = maxHeap.poll();
            if(x < y) maxHeap.add(y - x);
            else if(x > y) maxHeap.add(x - y);
        }

        return 0;
    }
}
