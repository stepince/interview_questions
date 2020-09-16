import java.util.LinkedList;
import java.util.Queue;

/*

https://leetcode.com/problems/invert-binary-tree/

input:
     4
   /   \
  2     7
 / \   / \
1   3 6   9

output:
     4
   /   \
  7     2
 / \   / \
9   6 3   1
 */
public class InvertTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

    static void swap(TreeNode node ){
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    public static TreeNode invertTreeBFS(TreeNode root) {
        if ( root == null ) return null;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while ( !q.isEmpty() ){
            TreeNode node = q.poll();
            swap(node);
            if ( node.left != null ) q.add(node.left);
            if ( node.right != null ) q.add(node.right);
        }
        return root;
    }

    public static TreeNode invertTreeDFS(TreeNode root) {
        if ( root == null ) return null;
        swap(root);
        invertTreeDFS(root.left);
        invertTreeDFS(root.right);
        return root;
    }

    public static void print( TreeNode root ){
        if ( root == null ) return;
        Queue<TreeNode> q = new LinkedList<>();
        String comma = "";
        q.add(root);
        while( !q.isEmpty() ){
            TreeNode node = q.remove();
            System.out.print(comma+node.val);
            comma = ",";
            if ( node.left != null ) q.add(node.left);
            if ( node.right != null ) q.add(node.right);
        }
        System.out.println();
    }

    public static void main(String[] args) {
	    /* Construct below tree
				  1
				/   \
			   /     \
			  2       3
			 / \     / \
			4   5   6   7
		*/

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        print(invertTreeBFS(root));
        print(invertTreeDFS(root));
    }
}
