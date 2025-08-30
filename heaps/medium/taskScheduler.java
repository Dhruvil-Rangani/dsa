import java.util.*;
// https://leetcode.com/problems/task-scheduler/description/
// Time: O(T + P) where T is the number of tasks and P is the number of idle periods
// Space: O(1) since the frequency array and maxHeap will have at most 26 elements

// Approach: Use a max-heap to always execute the most frequent task available. Use a queue to manage the cooldown period for tasks.
// Increment time for each unit of work done, and if no tasks are available, jump to the next available task's time.
// This ensures that we minimize idle time while respecting the cooldown constraint.
// The frequency array is of fixed size (26 for uppercase letters), so space complexity is O(1).
// The time complexity is O(T + P) because we process each task once and may have to account for idle periods.
// The priority queue operations are logarithmic in the number of unique tasks, which is at most 26, thus considered constant time.
// This approach efficiently handles the scheduling of tasks with cooldowns.
// is this optimal? yes, because we are always executing the most frequent task available, minimizing idle time.
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for(char c : tasks) {
            freq[c - 'A']++;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for(int cnt : freq) {
            if(cnt > 0) maxHeap.add(cnt);
        }

        int time = 0;
        Queue<int[]> q = new LinkedList<>();
        while(!maxHeap.isEmpty() || !q.isEmpty()) {
            time++;
            if(maxHeap.isEmpty()) {
                time = q.peek()[1];
            } else {
                int cnt = maxHeap.poll() - 1;
                if(cnt > 0) q.add(new int[]{cnt, time + n});
            }

            if(!q.isEmpty() && q.peek()[1] == time) maxHeap.add(q.poll()[0]);
        }

        return time;
    }
}
