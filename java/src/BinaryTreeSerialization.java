import java.util.*;
/*
   https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */
public class BinaryTreeSerialization {

    static class Counter{
        int value;
    }

    static class Node {
        final String value;
        Node left;
        Node right;
        Node(String value){
            this.value = value;
        }
    }

    private static final String MARKER = "#";

    private static class PrintHelper {

        private static String traversePreOrder(Node root) {

            if (root == null) return "";

            StringBuilder sb = new StringBuilder();
            sb.append(root.value);

            String pointerRight = "└──";
            String pointerLeft = (root.right != null) ? "├──" : "└──";

            traverseNodes(sb, "", pointerLeft, root.left, root.right != null);
            traverseNodes(sb, "", pointerRight, root.right, false);

            return sb.toString();
        }

        private static void traverseNodes(StringBuilder sb, String padding, String pointer, Node node, boolean hasRightSibling) {
            if ( node == null ) return;
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.value);

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            }
            else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (node.right != null) ? "├──" : "└──";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.left, node.right != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.right, false);
        }

        static void print(Node n){
            System.out.println( traversePreOrder(n));
        }
    }

    private static Node deserialize( List<String> l, Counter counter ){
        if ( counter.value >= l.size()  ) return null;
        String val = l.get(counter.value++);
        if ( MARKER.equals(val) )  return null;
        Node n = new Node(val);
        n.left = deserialize( l, counter );
        n.right = deserialize( l, counter );
        return n;
    }

    public static Node deserialize( List<String> l){
        return deserialize(l, new Counter());
    }

    public static void serialize(Node n, List<String> l ){
        if ( n == null ) {
            l.add(MARKER);
            return;
        }
        l.add(n.value);
        serialize(n.left,l);
        serialize(n.right,l);
    }

    private static List<String> serialize( Node n ){
        List<String> l = new ArrayList<>();
        serialize(n,l);
        return l;
    }

    public static void main(String[] args){
        //System.out.println(args[0]);
        //List<String> l = Arrays.asList(args[0].split("\\s*,\\s*"));
        List<String> l = Arrays.asList("hello","#","world");
        Node n = deserialize(l);
        PrintHelper.print(n);
        List<String> serializeTree = serialize(n);
        //toArray
        //serializeTree.toArray(String[]::new);
        System.out.println(serializeTree);
    }



}