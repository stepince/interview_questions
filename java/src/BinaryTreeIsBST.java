/*

Time: O(n)
Auxiliary Space : O(1) if Function Call Stack size is not considered, otherwise O(n)
 */
public class BinaryTreeIsBST {

    static class Container {
        Integer value;
        Container(Integer value ){
            this.value = value;
        }
    }

    static class Node {
        Integer value;
        Node left;
        Node right;
        Node(Integer value){
            this.value = value;
        }
    }

    public static boolean isBSTUtil(Node root, int min, int max){
        if ( root == null ) return true;
        if ( root.value < min || root.value > max) return false;
        return isBSTUtil(root.left,min,root.value-1) && isBSTUtil(root.right,root.value+1, max);
    }

    public static boolean isBSTUtil2(Node root, Container prev){
        if ( root == null ) return true;
        if (!isBSTUtil2(root.left, prev)) return false;
        if ( prev.value >= root.value ) return false;
        prev.value = root.value;
        return isBSTUtil2(root.right,prev);
    }

    public static boolean isBST(Node root){
        return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBST2(Node root){
        return isBSTUtil2(root, new Container(Integer.MIN_VALUE));
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
        System.out.println(isBST(root));
        System.out.println(isBST2(root));

        System.out.println("-------------------------------");
         /* Construct tree
                 15
               /    \
             5       30
           /   \    /   \
         3     10  20    45
         */

        Node root2 = new Node(15);
        root2.left = new Node(5);
        root2.right = new Node(30);
        root2.left.left = new Node(2);
        root2.right.right = new Node(45);
        root2.right.left = new Node(20);
        root2.left.right = new Node(10);
        System.out.println(isBST(root2));
        System.out.println(isBST2(root2));
        System.out.println("-------------------------------");
         /* Construct tree
                 15
               /    \
             5       30
           /   \    /   \
         3     15 20    45
         */

        Node root3 = new Node(15);
        root3.left = new Node(5);
        root3.right = new Node(30);
        root3.left.left = new Node(3);
        root3.right.right = new Node(45);
        root3.right.left = new Node(20);
        root3.left.right = new Node(16);
        System.out.println(isBST(root3));
        System.out.println(isBST2(root3));
    }
}
