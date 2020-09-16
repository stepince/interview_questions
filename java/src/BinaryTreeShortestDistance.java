import java.util.Deque;
import java.util.LinkedList;

public class BinaryTreeShortestDistance {

    // Graph node class
    public static class Node {
        private final int value;
        Node left;
        Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    static boolean dfs( Node node, int val , Deque<Node> deq ){
        if ( node == null ) return false;
        deq.add(node);
        boolean ans = ( node.value == val || dfs(node.left, val, deq) || dfs(node.right, val, deq) );
        // remember to backtrack
        if ( !ans ) deq.removeLast();
        return ans;
    }

    public static int find( Node root, int val1, int val2 ){
        Deque<Node> deq1 = new LinkedList<>();
        Deque<Node> deq2 = new LinkedList<>();
        if ( !dfs(root,val1,deq1) || !dfs(root,val2,deq2) ) return -1;

        while( !deq1.isEmpty() && !deq2.isEmpty() && deq1.peek().value == deq2.peek().value ){
            deq1.remove();
            deq2.remove();
        }
        return deq1.size() + deq2.size();
    }

    public static void main(String[] args){

         /* Construct tree
                  1
               /    \
             2       3
            / \     / \
           4   5   6   7
         */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.println( "distance(4,5): " + find(root,4,5));
        System.out.println( "distance(1,5): " + find(root,1,5));
        System.out.println( "distance(4,7): " + find(root,4,7));
    }
}
