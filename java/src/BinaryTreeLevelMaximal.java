import java.util.LinkedList;
import java.util.Queue;

/*
  Maximum Level Sum of a Binary Tree
  https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
  Return the smallest level X such that the sum of all the values of nodes at level X is maximal.

Time:
O(N)

 */
public class BinaryTreeLevelMaximal {

    // Graph node class
    static class Node {
        final Integer value;
        Node left;
        Node right;
        public Node(Integer value) {
            this.value = value;
        }
    }
    
     public static int find(Node root ){
        if ( root == null ) return Integer.MIN_VALUE;
        int max = root.value;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;
        int maxLevel = 1;
        while( !queue.isEmpty() ){
            int size = queue.size();
            int curr = 0;
            while ( size-- > 0 ){
                Node node = queue.remove();
                if ( node.left!= null) queue.add(node.left);
                if ( node.right!= null) queue.add(node.right);
                curr += node.value;
            }
            if ( curr > max ){
                max = curr;
                maxLevel = level;
            }
            ++level;
        }
        return maxLevel;
     }
     public static void main(String[] args){

         /* Construct tree
                 1
               /    \
             2       3
            / \     / \
           4   5   6   7
                      /  \
                     5    30
                           \
                           34
         */
         Node root = new Node(1);
         root.left = new Node(2);
         root.right = new Node(3);
         root.left.left = new Node(4);
         root.left.right = new Node(5);
         root.right.left = new Node(6);
         root.right.right = new Node(7);
         root.right.right.right = new Node(30);
         root.right.right.left = new Node(5);
         root.right.right.right.right = new Node(34);
         // return level 4
         System.out.println( "level: " + find(root));
     }

} 