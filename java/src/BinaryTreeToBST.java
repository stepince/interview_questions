import java.util.*;

public class BinaryTreeToBST {

    static class Counter {
        int value;
    }

    static class Node {
        Integer value;
        Node left;
        Node right;
        Node(Integer value){
            this.value = value;
        }
    }

    static void serialize( Node root, List<Integer> l){
        if ( root == null ) return;
        serialize(root.left,l);
        l.add(root.value);
        serialize(root.right,l);
    }

    static void deserialize(Node root, List<Integer> l, Counter counter){
        if ( root == null ) return;
        deserialize(root.left, l, counter);
        root.value = l.get(counter.value++);
        deserialize(root.right, l, counter);
    }

    static Node toBST(Node root){
        if ( root == null ) return null;
        List<Integer> l = new ArrayList<>();
        serialize(root,l);
        Collections.sort(l);
        deserialize(root, l, new Counter());
        return root;
    }

    static void print(Node root){
        if ( root == null ) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            String sep = "";
            while( levelSize > 0 ) {
                Node node = queue.remove();
                System.out.print(sep + node.value);
                sep = " ";
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                --levelSize;
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
         /* Construct tree
                 10
               /    \
             30      15
           /   \    /   \
         20     45 3      5
         */
        Node root = new Node(10);
        root.left = new Node(30);
        root.right = new Node(15);
        root.left.left = new Node(20);
        root.right.right = new Node(5);
        root.right.left = new Node(3);
        root.left.right = new Node(45);
        Node bst = toBST(root);
        print(bst);
    }

}
