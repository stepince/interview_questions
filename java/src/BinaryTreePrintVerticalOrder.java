import java.util.*;
/*
Time:
   O(N + NLGN)

Space:
   O(N)

*/
public class BinaryTreePrintVerticalOrder {

    static class Node {
        final String value;
        Node left;
        Node right;
        Node(String value){
            this.value = value;
        }
    }

    static void printUtil(Node root, int level, Map<Integer,List<String>> map){
        if ( root == null ) return;
        map.computeIfAbsent(level, (key) -> new ArrayList<>()).add(root.value);
        printUtil(root.left,level-1, map);
        printUtil(root.right,level+1, map);
    }

    static void print(Node root){
        if ( root == null) return;
        Map<Integer, List<String>> map = new HashMap<>();
        printUtil( root,0, map );
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        for ( Integer key: keys ){
            System.out.println(map.get(key));
        }
    }

    public static void main(String[] args){
         /* Construct tree
                  1
               /      \
             2          3
           /  \        /  \
          4    5      6    7
                       \    \
                        8    9
         */
        Node root = new Node(String.valueOf(1));
        root.left = new Node(String.valueOf(2));
        root.right = new Node(String.valueOf(3));
        root.left.left = new Node(String.valueOf(4));
        root.left.right = new Node(String.valueOf(5));
        root.right.left = new Node(String.valueOf(6));
        root.right.left.right = new Node(String.valueOf(8));
        root.right.right = new Node(String.valueOf(7));
        root.right.right.right = new Node(String.valueOf(9));
        print(root);
    }
}
