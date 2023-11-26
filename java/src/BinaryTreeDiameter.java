/*


https://www.geeksforgeeks.org/diameter-of-a-binary-tree/
https://leetcode.com/problems/diameter-of-binary-tree/

Note:  this was super confusing. The diameter of tree is the highest number of edges between any 2 nodes


The diameter of a tree T is the largest of the following quantities:

* the diameter of T’s left subtree
* the diameter of T’s right subtree
* the longest path between leaves that goes through the root of T (this can be computed from the heights of the subtrees of T)


Diameter you are counting the nodes on the path.
 */

public class BinaryTreeDiameter {

    static class IntWrapper {
        int val;
    }

    static class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }

    static int getHeight( Node node, IntWrapper diameterIntWrapper ){
        if ( node == null ) return -1;
        int left = getHeight(node.left, diameterIntWrapper);
        int right = getHeight(node.right, diameterIntWrapper);
        int height = Math.max(left,right) + 1;
        diameterIntWrapper.val = Math.max(diameterIntWrapper.val, Math.max(height, left + right + 2));
        return height;
    }

    public static int diameter( Node root ){
        IntWrapper diameter = new IntWrapper();
        getHeight(root,diameter);
        return diameter.val;
    }

    public static void main(String[] args){
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);
        System.out.println("The diameter of given binary tree is : "  + diameter(tree));
    }

}
