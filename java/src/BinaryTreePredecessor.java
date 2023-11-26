/*
BST

 */
public class BinaryTreePredecessor {

    static class Container {
        Node node;
    }

    static class Node {
        Integer value;
        Node left;
        Node right;
        Node(Integer value){
            this.value = value;
        }
        public String toString(){
            return String.valueOf(value);
        }
    }

    public static Node getPredecessor(Node input, Container next){
         if ( input == null ) return null;
        getPredecessor(input.right, next);

         if ( next.node != null ) return next.node;
         next.node = input;
         return getPredecessor(input.left, next );
    }

    public static Node getPredecessor(Node input){
        if (input == null ) return null;
        return getPredecessor(input.left, new Container());
    }

    public static void main(String[] args){
        Node root = new Node(18);
        root.left = new Node(5);
        root.right = new Node(30);
        root.left.left = new Node(3);
        root.left.right = new Node(15);
        root.right.right = new Node(45);
        root.right.left = new Node(20);
        System.out.println("-------------------------------");
         /* Construct tree
                 18
               /    \
             5       30
           /   \    /   \
         3     15 20    45
         */

        System.out.println(getPredecessor(root));
    }
}
