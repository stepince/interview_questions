import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BinaryTreeHeight {

    static class Node {
        final String value;
        Node left;
        Node right;
        Node(String value){
            this.value = value;
        }
    }

    private static boolean isNull(String str){
        return str == null || str.length() == 0;
    }

    private static Node deserialize( Iterator<String> iter ){
        if ( !iter.hasNext() ) return null;
        String val = iter.next();
        if ( isNull(val) )  return null;
        Node n = new Node(val);
        n.left = deserialize( iter );
        n.right = deserialize( iter );
        return n;
    }

    public static int getHeight(Node n){
        if ( n == null ) return 0;
        return Math.max( getHeight(n.left), getHeight(n.right) ) + 1;
    }

    public static void main(String[] args){
        System.out.println(args[0]);
        List<String> l = Arrays.asList(args[0].split("\\s*,\\s*"));
        Node n = deserialize(l.iterator());
        System.out.println(getHeight(n));
    }
}