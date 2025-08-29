package heaps.medium;
import java.util.*;
// You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
// Merge all the linked-lists into one sorted linked-list and return it.
// Time Complexity: O(N log k) where N is the total number of nodes and k is the number of linked lists.
// Space Complexity: O(k)
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode mergeKSortedLists(List<ListNode> heads) {
        if(heads == null || heads.isEmpty()) return null;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(ListNode head : heads) {
            ListNode node = head;
            while(node != null) {
                pq.add(node.val);
                node = node.next;
            }
        }

        if (pq.isEmpty()) return null;

        ListNode newNode = new ListNode(pq.poll());
        ListNode temp = newNode;
        while(!pq.isEmpty()) {
            temp.next = new ListNode(pq.poll());
            temp = temp.next;
        }

        return newNode;
    }
}
