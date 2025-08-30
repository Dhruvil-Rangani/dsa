import java.util.*;
// You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
// Merge all the linked-lists into one sorted linked-list and return it.
// Time Complexity: O(N log N) where N is the total number of nodes
// Space Complexity: O(N)
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

/*Definition for singly Linked List
class ListNode {
    int val;
    ListNode next;

    ListNode() {
        val = 0;
        next = null;
    }

    ListNode(int data1) {
        val = data1;
        next = null;
    }

    ListNode(int data1, ListNode next1) {
        val = data1;
        next = next1;
    }
}
*/

// Optimized approach using Min-Heap (Priority Queue)
// Time Complexity: O(N log k) where N is the total number of nodes and k is the number of linked lists.
// Space Complexity: O(k)
class Solution2 {
    public ListNode mergeKSortedLists(List<ListNode> heads) {
        if(heads == null || heads.isEmpty()) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.val, b.val));

        for(ListNode head : heads) {
            if(head != null) {
                pq.add(head);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;

        while(!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            temp.next = minNode;
            temp = temp.next;
            if(minNode.next != null) {
                pq.add(minNode.next);
            }
        }

        return dummy.next;
    }
}
