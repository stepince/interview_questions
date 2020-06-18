
/*
Merge K Sorted Lists
https://leetcode.com/problems/merge-k-sorted-lists/

Time:
   O(NlogK)
   where N is the size of the lists and K is the number of linked lists

Space:
  O(K) the size of the priority queue
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists {

    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        public int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void print( ListNode l ){
        String comma = "";
        while(l != null){
            System.out.print(comma+l.val);
            comma = ",";
            l = l.next;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if ( lists == null ) return null;

        // This is faster, just wanted to remove the warning.
        //PriorityQueue<ListNode> queue = new PriorityQueue<>( (a,b) -> a.val - b.val);
        Queue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for ( ListNode l: lists ) {
            if ( l != null ) queue.add(l);
        }
        if ( queue.size() == 0 ) return null;

        ListNode head = queue.remove();
        if ( head.next != null ) queue.add(head.next);
        ListNode curr = head, node;

        while( !queue.isEmpty() ){
            node = queue.remove();
            if ( node.next != null ) queue.add( node.next );
            curr = curr.next = node;
        }
        return head;
    }

    public static void main(String[] args){

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = {l1,l2,l3};
        ListNode sortedList =  mergeKLists(lists);
        print(sortedList);
    }
}
