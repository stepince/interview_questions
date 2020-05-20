import java.util.Deque;
import java.util.LinkedList;

/*
 LCA

 return -1 if not found

O(N)

 */
public class LCA {

    // Graph node class
    public static class Node {
        private final int value;
        Node left;
        Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    private static boolean dfs( Node node, Integer val, Deque<Node> dq ){
        if ( node == null ) return false;
        dq.add(node);
        if ( node.value == val ) return true;
        if ( dfs(node.left,val,dq) ) return true;
        if ( dfs(node.right,val,dq) ) return true;
        dq.removeLast();
        return false;
    }

    public static Integer find( Node root, Integer val1, Integer val2){
        Deque<Node> dq1 = new LinkedList<>();
        if ( !dfs(root,val1,dq1) ) return -1;
        Deque<Node> dq2 = new LinkedList<>();
        if ( !dfs(root,val2,dq2) ) return -1;

        int lca = root.value;
        while( !dq1.isEmpty() && !dq2.isEmpty() && dq1.peek().equals(dq2.peek()) ){
            lca = dq1.peek().value;
            dq1.remove();
            dq2.remove();
        }
        return lca;
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

         System.out.println( "lca(4,5): " + find(root,4,5));
         System.out.println( "lca(1,5): " + find(root,1,5));
     }

} 