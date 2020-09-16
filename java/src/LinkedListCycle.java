
/*



Tested code on leetcode
https://leetcode.com/problems/linked-list-cycle/submissions/
 */


public class LinkedListCycle {


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {

        ListNode fast = head;
        while ( head != null ){
            if ( fast != null ) fast = fast.next;
            if ( fast != null ) fast = fast.next;
            if ( fast == head ) return true;
            head = head.next;
        }

        return false;
    }


















    public static void main(String[] args){

    }
}
