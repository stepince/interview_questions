public class ReverseLinkedList {

    // A linked list node
    static class Node {
        final int value;
        Node next;
        public Node(int value){
            this.value = value;
        }
    }

    // Helper function to print given linked list
    public static void printList(Node head) {
        Node ptr = head;
        while (ptr != null) {
            System.out.print(ptr.value + " -> ");
            ptr = ptr.next;
        }

        System.out.println("null");
    }

    public static Node reverse(Node node){
        Node prev = null;
        while ( node != null ){
            Node next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        // print the flattened & sorted linked list
        printList(head);
        head = reverse(head);
        printList(head);
    }
}