/*
BinaryTreeEqual

 return false if not equal

O(N)

 */
public class BinaryTreeEqual {

    // Graph node class
    static class Node {
        final String value;
        Node left;
        Node right;
        public Node(String value) {
            this.value = value;
        }
    }

    public static boolean isEqual( Node root1, Node root2){
        if ( root1 == null && root2 == null) return true;
        if ( root1 == null || root2 == null) return false;

        // if value == null
        if ( root1.value == null && root2.value == null ) return true;
        if ( root1.value == null ) return false;

        if ( !root1.value.equals(root2.value) ) return false;
        if ( !isEqual( root1.left, root2.left) ) return false;
        return isEqual( root1.right, root2.right);
    }

     public static void main(String[] args){

         /* Construct tree
                  1
               /    \
             2       3
            / \     / \
           4   5   6   7
         */
         Node root1 = new Node(String.valueOf(1));
         root1.left = new Node(String.valueOf(2));
         root1.right = new Node(String.valueOf(3));
         root1.left.left = new Node(String.valueOf(4));
         root1.left.right = new Node(String.valueOf(5));
         root1.right.left = new Node(String.valueOf(6));
         root1.right.right = new Node(String.valueOf(7));

         Node root2 = new Node(String.valueOf(1));
         root2.left = new Node(String.valueOf(2));
         root2.right = new Node(String.valueOf(3));
         root2.left.left = new Node(String.valueOf(4));
         root2.left.right = new Node(String.valueOf(5));
         root2.right.left = new Node(String.valueOf(6));
         root2.right.right = new Node(String.valueOf(7));

         System.out.println( "isEquals: " + isEqual(root1,root2));

         root2.right.right = new Node(String.valueOf(8));
         System.out.println( "isEquals: " + isEqual(root1,root2));
     }

} 