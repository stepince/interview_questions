import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*

User Inorder traversal
Time:
   O(N)

Algo: inorder traversal
 */
public class CorrectBST {

    static class Node {
        Integer value;
        Node left;
        Node right;
        Node(Integer value){
            this.value = value;
        }
    }

    static class NodeContainer implements Comparable<NodeContainer> {
        Node node ;
        NodeContainer(Node node){
            this.node = node;
        }
        NodeContainer(){
        }

        @Override
        public int compareTo(NodeContainer o) {
            return this.node.value - o.node.value;
        }
    }

    static void correctBST(Node n, NodeContainer prev, List<NodeContainer> l){
        if ( n == null ) return;
        correctBST(n.left, prev,l);
        if ( prev.node != null && prev.node.value > n.value )  {
            if ( l.size() == 0 || l.get(l.size()-1).node != prev.node ) l.add( new NodeContainer(prev.node));
            l.add(new NodeContainer(n));
        }
        prev.node = n;
        correctBST(n.right, prev,l);
    }

    static void correctBST(Node n){
        List<NodeContainer> l = new ArrayList<>();
        correctBST(n, new NodeContainer(),l);
        List<Integer> vals = new ArrayList<>();
        for ( NodeContainer  nodeContainer: l ){
            vals.add(nodeContainer.node.value);
        }
        Collections.sort(vals);
        for ( int i = 0; i < l.size(); ++i  ){
            l.get(i).node.value = vals.get(i);
        }
    }

    public static void swap( NodeContainer a, NodeContainer b){
        int temp = a.node.value;
        a.node.value = b.node.value;
        b.node.value = temp;
    }

    static void correctBST2(Node n, NodeContainer prev, NodeContainer first, NodeContainer second){
        if ( n == null ) return;
        correctBST2(n.left, prev, first, second);
        if ( prev.node != null && prev.node.value >= n.value ){
            if ( first.node == null ) first.node = prev.node;
            else second.node = n;
        }
        prev.node = n;
        correctBST2(n.right, prev, first, second);
    }

    static void correctBST2(Node n){
        NodeContainer first =  new NodeContainer();
        NodeContainer second = new NodeContainer();
        correctBST2(n, new NodeContainer(), first, second);
        swap(first,second);
    }

    static void printBST(Node node, List<Integer> l){
        if ( node == null ) return;
        printBST(node.left,l);
        l.add(node.value);
        printBST(node.right,l);
    }

    static void printBST(Node node){
        List<Integer> l = new ArrayList<>();
        printBST(node,l);
        System.out.println(l.toString());
    }

    public static void main(String[] args) {

    /* Construct tree

         20
        /  \
       8    22
      / \
     4   12
        /  \
       10   14

         10
        /  \
       8    22
      / \
     4   12
        /  \
       20   14

       1
      /
     3
      \
       2
*/

//        Node root = new Node(10);
//        root.left = new Node(8);
//        root.right = new Node(22);
//        root.left.left = new Node(4);
//        root.left.right = new Node(12);
//        root.left.right.left = new Node(20);
//        root.left.right.right = new Node(14);

        Node root1 = new Node(1);
        root1.left = new Node(3);
        root1.left.right = new Node(2);

        Node root2 = new Node(1);
        root2.left = new Node(3);
        root2.left.right = new Node(2);

        printBST(root1);
        correctBST(root1);
        System.out.println("fixed BST");
        printBST(root1);
        System.out.println("-------------------------------------------------");
        printBST(root2);
        correctBST2(root2);
        System.out.println("fixed BST");
        printBST(root2);
    }
}
