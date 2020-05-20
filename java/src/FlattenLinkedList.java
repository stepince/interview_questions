public class FlattenLinkedList {

    // A linked list node
    static class Node {
        final int value;
        Node next;
        Node down;
        public Node(int value){
            this.value = value;
        }
    }

    // Helper function to insert new node in the beginning of the
    // vertical linked list
    public static Node push(Node head,  int value) {
        Node newNode = new Node(value);
        newNode.next = null;
        newNode.down = head;
        return newNode;
    }

    // Takes two lists sorted in increasing order, and merge their nodes
    // together to make one big sorted list which is returned
    public static Node sortedMerge(Node a, Node b) {
        if (a == null) {
            return b;
        }
        else if (b == null) {
            return a;
        }

        Node result;

        // Pick either a or b, and recur
        if (a.value <= b.value) {
            result = a;
            result.down = sortedMerge(a.down, b);
        }
        else {
            result = b;
            result.down = sortedMerge(a, b.down);
        }

        return result;
    }

	/*
		Split the nodes of the given list into front and back halves.
		If the length is odd, the extra node should go in the front list.
		It uses the fast/slow reference strategy
	*/

    public static Node[] frontBackSplit(Node source) {
        // if length is less than 2, handle separately
        if (source == null || source.down == null) {
            return new Node[]{ source, null } ;
        }

        Node slow = source;
        Node fast = source.down;

        // Advance 'fast' two nodes, and advance 'slow' one node
        while (fast != null) {
            fast = fast.down;
            if (fast != null) {
                slow = slow.down;
                fast = fast.down;
            }
        }

        // 'slow' is before the midpoint in the list, so split it in two
        // at that point.
        Node[] arr = new Node[]{ source, slow.down };
        slow.down = null;

        return arr;
    }

    // Sort given linked list using Merge sort algorithm
    public static Node mergeSort(Node head) {
        // Base case -- length 0 or 1
        if (head == null || head.down == null) {
            return head;
        }

        // Split head into 'a' and 'b' sublists
        Node[] arr = frontBackSplit(head);
        Node front = arr[0];
        Node back = arr[1];

        // Recursively sort the sublists
        front = mergeSort(front);
        back = mergeSort(back);

        // answer = merge the two sorted lists together
        return sortedMerge(front, back);
    }

    // Helper function to print given linked list
    public static void printList(Node head) {
        Node ptr = head;
        while (ptr != null) {
            System.out.print(ptr.value + " -> ");
            ptr = ptr.down;
        }

        System.out.println("null");
    }

    // Iterative function to flatten and sort a given list
    public static void flatten (Node head) {
        Node curr = head;

        while (curr != null) {
            Node last = curr;
            // get last
            while (last.down != null) {
                last = last.down;
            }
            // point last to the next
            last.down = curr.next;

            curr = curr.next;
        }
    }

    // Helper function to create a linked list with elements of given vector
    public static Node createVerticalList(Node head, int[] arr) {
        for (int key: arr) {
            head = push(head, key);
        }
        return head;
    }

    public static void main(String[] args) {
        Node head;

        int[] arr1 = { 8, 6, 4, 1 };
        int[] arr2 = { 7, 3, 2 };
        int[] arr3 = { 9, 5 };
        int[] arr4 = { 12, 11, 10 };

        head = createVerticalList(null, arr1);
        head.next = createVerticalList(head.next, arr2);
        head.next.next = createVerticalList(head.next.next, arr3);
        head.next.next.next = createVerticalList(head.next.next.next, arr4);

        // flatten the list
        flatten(head);

        //sort the list
        mergeSort(head);

        // print the flattened & sorted linked list
        printList(head);
    }
}