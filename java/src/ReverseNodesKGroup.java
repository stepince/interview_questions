
/*

https://leetcode.com/problems/reverse-nodes-in-k-group/

 */

public class ReverseNodesKGroup {

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
/*
3->2->1->6->5->4
 */
    static void reverseSegment( ListNode start, ListNode end ){
        ListNode prev = null;
        while( start != end ){
            ListNode next = start.next;
            start.next = prev;
            prev = start;
            start = next;
        }
        start.next = prev;
    }

    public static ListNode reverse( ListNode head, int k ) {
        if ( head == null || k == 1 ) return head;
        ListNode begin = null;
        ListNode next;
        ListNode kCurr = head;
        ListNode kPrev = null;
        int counter = 0;
        while ( head != null ) {
            next = head.next;
            if ( ++counter % k == 0 ) {
                reverseSegment(kCurr, head);
                if ( kPrev != null ) kPrev.next = head;
                kCurr.next = next;
                if ( begin == null ) begin = head;
                kPrev = kCurr;
                kCurr = next;
            }
            head = next;
        }
        return begin;
    }

    static ListNode arrayToList(int[] arr){
        ListNode l= new ListNode(arr[0]);
        ListNode head = l;
        for ( int i = 1; i < arr.length ; ++i ){
            l.next = new ListNode(arr[i]);
            l =  l.next;
        }
        return head;
    }

    public static void main(String[] args){
        int k = 3;
        int[] arr = {1,2,3,4,5,6} ;
        ListNode l = arrayToList(arr);
        print(reverse(l,k));
    }
}
