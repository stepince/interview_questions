import java.util.ArrayList;
import java.util.List;
/*
  create a iinkedlist inorder

  flatten out the list into doublelinked list
  Time: O(N)
  Space: O(N)


 */
public class BinaryTree2DoublyLinkedList {

    // Graph node class
    static class Node {
        private final String value;
        Node left;
        Node right;
        public Node(String value) {
            this.value = value;
        }
    }

    public static void tree2LinkedListUtil( Node root, List<Node> l ){
        if ( root == null ) return;
        tree2LinkedListUtil(root.left,l);
        l.add(root);
        tree2LinkedListUtil(root.right,l);
    }

    public static Node tree2LinkedList( Node root ){
        if ( root == null ) return null;
        List<Node> l = new ArrayList<>();
        // do the inorder traversal
        tree2LinkedListUtil(root,l);
        l.get(0).left =  null;
        for (int i = 1; i < l.size();++i){
            // assign next
            l.get(i-1).right = l.get(i);
            // assign previous
            l.get(i).left = l.get(i-1);
        }
        l.get(l.size()-1).right=null;
        return l.get(0);
    }

    public static void print(Node root){
        while( root != null ){
            System.out.println(root.value);
            root = root.right;
        }
    }

    public static void main(String[] args){

         /* Construct tree
                 4
               /   \
             2       6
            / \     / \
           1   3   5   7
         */
        Node root1 = new Node(String.valueOf(4));
        root1.left = new Node(String.valueOf(2));
        root1.right = new Node(String.valueOf(6));
        root1.left.left = new Node(String.valueOf(1));
        root1.left.right = new Node(String.valueOf(3));
        root1.right.left = new Node(String.valueOf(5));
        root1.right.right = new Node(String.valueOf(7));

        Node l = tree2LinkedList(root1);
        print(l);

        //System.out.println( "isEquals: " + isEqual(root1,root2));
    }
}
