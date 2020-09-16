//import java.util.Arrays;
//import java.util.stream.Stream;
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

    public static Integer sum ( Integer a, Integer b ){
        if ( a == null ) return b;
        return a + b;
    }

    private static int find(int[] arr, int idx, Integer total ){
        if ( idx == arr.length ) {
            return ( total != null && total == 0 ) ? 1 : 0;
        }

        return find(arr, idx+1, sum(total,arr[idx])) + find(arr, idx+1, total);
    }

    public static int find(int[] arr) {
        return find(arr, 0,null);
    }
    public static void main(String[] args){
//        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();

        int[] arr = {9,2, 2,-4,-4,5,8};
        System.out.println(find(arr));
    }
}