package heaps.hard;
import java.util.*;

// The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.
// For example, for arr = [2,3,4], the median is 3.
// For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
// Implement the MedianFinder class:
// MedianFinder() initializes the MedianFinder object.
// void addNum(int num) adds the integer num from the data stream to the data structure.
// double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
// Time Complexity: O(log n) for addNum and O(1) for findMedian
// Space Complexity: O(n) where n is the number of elements added

class MedianFinder {
    private PriorityQueue<Integer> smallHeap; // small elements - maxHeap
    private PriorityQueue<Integer> largeHeap; // large elements - minHeap

    public MedianFinder() {
        smallHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        largeHeap = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
    }
    
    public void addNum(int num) {
        smallHeap.add(num);
        if(
            smallHeap.size() - largeHeap.size() > 1 || 
            !largeHeap.isEmpty() && 
            smallHeap.peek() > largeHeap.peek()
        ) {
            largeHeap.add(smallHeap.poll());
        }
        if(largeHeap.size() - smallHeap.size() > 1) {
            smallHeap.add(largeHeap.poll());
        }
    }
    
    public double findMedian() {
        if(smallHeap.size() == largeHeap.size()) {
            return (double) (largeHeap.peek() + smallHeap.peek()) / 2;
        } else {
            return smallHeap.size() > largeHeap.size() ? (double) smallHeap.peek() : largeHeap.peek();
        }
    }
}

