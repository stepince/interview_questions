import java.util.ArrayDeque;
import java.util.Queue;

/*
  Maximum Level Sum of a Binary Tree (BST)
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
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        int maxLevel = 1;
        for( int level = 1, curr=0;  !q.isEmpty(); ++level, curr=0 ){
            for ( int i = 0, size = q.size();  i < size ; ++i ){
                Node node = q.poll();
                if ( node.left!= null) q.add(node.left);
                if ( node.right!= null) q.add(node.right);
                curr += node.value;
            }
            if ( curr > max ){
                max = curr;
                maxLevel = level;
            }
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