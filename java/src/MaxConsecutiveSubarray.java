import java.util.*;
import java.util.stream.Collectors;

/*


runtime
O(N)

input: 5,100,99,2,3,1,4

output: 5

 */
public class MaxConsecutiveSubarray {

    public static int find(int[] arr){
        int maxLen = 0;

        Set<Integer> s = Arrays.stream(arr).boxed().collect(Collectors.toSet());

        for ( int i = 0; i < arr.length && !s.isEmpty() ; ++i ){
            int begin = i;
            int end = i;
            s.remove(arr[i]);
            //check for previous items, don't count items twice
            while ( s.contains(end+1) ) {
                s.remove(arr[++end]);
            }
            //check for next items, don't count items twice
            while ( s.contains(begin-1) ){
                s.remove(arr[--begin]);
            }
            maxLen = Math.max(maxLen, end-begin+1);
        }
        return maxLen;
    }

    public static void main(String[] args){
        int[] arr =  Arrays.stream(args[0].split("[,\\s]")).mapToInt(Integer::parseInt).toArray();
        System.out.println(find(arr));
    }
}
