import java.util.Arrays;
import java.util.stream.Stream;
/*

     Count the number of subsets that sum to zero

     notes:
         null is used to indicate no sum being recorded

    input:
    "2, 2, -4,4,5,8"
    output:
    {}
    2,2,-4
   -4,4
      count - 3

    input:
    "2, 2, 2, -4, 4"

    output:
    {}
    2,2,-4
    2,2,-4
    2,2,-4
    -4, 4
    count - 5
 */
public class ZeroSumSubsetCount {
    private static int find(int[] arr, int idx, Integer sum ){
        if ( sum == 0 ) return 1;
        if ( idx == 0 ) return 0;
        return find(arr, idx-1, sum-arr[idx-1]) + find(arr, idx-1, sum);
    }
    public static int find(int[] arr) {
        // one for the empty set
        int sum = Arrays.stream(arr).sum();
        return find(arr, arr.length,sum);
    }
    public static void main(String[] args){
        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(find(arr));
    }
}