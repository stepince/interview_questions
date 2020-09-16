/*


https://www.geeksforgeeks.org/diameter-of-a-binary-tree/
https://leetcode.com/problems/diameter-of-binary-tree/

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

    static int dfsVisit( Node node, IntWrapper diameter ){
        if ( node == null ) return 0;
        int left = dfsVisit(node.left, diameter);
        int right = dfsVisit(node.right, diameter);
        int height = Math.max(left,right) + 1;
        diameter.val = Math.max(diameter.val, left + right);
        return height;
    }

    public static int dfs( Node root ){
        IntWrapper diameter = new IntWrapper();
        dfsVisit(root,diameter);
        return diameter.val;
    }

    public static void main(String[] args){
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);

        System.out.println("The diameter of given binary tree is : "  + dfs(tree));
    }

}
