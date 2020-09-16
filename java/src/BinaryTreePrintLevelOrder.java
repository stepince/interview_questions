import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreePrintLevelOrder {

    static class Node {
        final String value;
        Node left;
        Node right;
        Node(String value){
            this.value = value;
        }
    }

    static void print(Node root){
        if ( root == null ) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            String sep = "";
            for( int levelSize = queue.size() ; levelSize > 0 ; --levelSize) {
                Node node = queue.remove();
                System.out.print(sep + node.value);
                sep = " ";
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
         /* Construct tree
                  1
               /      \
             2          3
           /  \        /  \
          4    5      6    7
         / \  / \    / \   / \
        8   9 10 11 12 13 14 15
         */
        Node root = new Node(String.valueOf(1));
        root.left = new Node(String.valueOf(2));
        root.right = new Node(String.valueOf(3));
        root.left.left = new Node(String.valueOf(4));
        root.left.left.left = new Node(String.valueOf(8));
        root.left.left.right = new Node(String.valueOf(9));
        root.left.right = new Node(String.valueOf(5));
        root.left.right.left = new Node(String.valueOf(10));
        root.left.right.right = new Node(String.valueOf(11));
        root.right.left = new Node(String.valueOf(6));
        root.right.left.left = new Node(String.valueOf(12));
        root.right.left.right = new Node(String.valueOf(13));
        root.right.right = new Node(String.valueOf(7));
        root.right.right.left = new Node(String.valueOf(14));
        root.right.right.right = new Node(String.valueOf(15));
        print(root);
    }
}
