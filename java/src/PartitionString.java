/* partition a string so that all the letter only reside in partition

   input:  ababcbacadefegdehijhklij
           ababcbaca  defegde hijhklij
   output: 9,7,8

Runtime: O(N)
Space (K)
 */
import java.util.*;

public class PartitionString {

    static int[] find(char[] arr){
        Map<Character,Integer> lastMap = new HashMap<>();
        List<Integer> l = new ArrayList<>();
        for ( int i = 0; i < arr.length; ++i ){
            lastMap.put(arr[i],i);
        }

        for ( int i = 0; i < arr.length; ){
            int next = lastMap.get(arr[i]);
            for ( int j = i; j < next; ++j ) {
                next = Math.max(next,lastMap.get(arr[j]));
            }
            // partition is the next char, i is the starting point
            l.add(++next-i);
            // next starting point is next
            i = next;
        }
        return l.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args){
        char[] arr = args[0].toCharArray();
        System.out.println(Arrays.toString(find(arr)));
    }
}
