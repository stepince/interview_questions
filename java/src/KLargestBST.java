/*


 */

public class KLargestBST {

    static class Counter {
        int value;
    }

    static class Node {
        final int value;
        Node left;
        Node right;
        public Node(int value){
            this.value = value;
        }
    }

    static Node findKLargest(Node root, int k, Counter counter){
        if ( root == null ) return null;
        Node node = findKLargest(root.right,k,counter);
        if ( node != null ) return node;
        if ( ++counter.value == k ) return root;
        return findKLargest(root.left,k,counter);
    }

    public static int find(Node root, int k){
        Node node = findKLargest(root,k,new Counter());
        return node == null ? -1 : node.value;
    }

    public static void main(String[] args) {

    /* Construct tree
         20
        /  \
       8    22
      / \
     4   12
        /  \
       10   14

*/
        Node root = new Node(20);
        root.left = new Node(8);
        root.right = new Node(22);
        root.left.left = new Node(4);
        root.left.right = new Node(12);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(14);
        int k = 1;
        System.out.println( find(root,k));
    }
}
