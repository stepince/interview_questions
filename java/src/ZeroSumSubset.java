import java.util.Arrays;
import java.util.stream.Stream;
/*
   Given a set of integers, determine if there is a subset of the given set with sum equal to zero,
   note: not including the empty set, the empty set {} has sum 0

   input : "-8,2,1,-3"
   output : true

   input: 4,-2
   output: false

 */
public class ZeroSumSubset {

    public static Integer sum ( Integer a, Integer b ){
        if ( a == null ) return b;
        return a + b;
    }

    private static boolean find(int[] arr, int idx, Integer total ){
        if ( total != null && total == 0  ) return true;
        if ( idx == arr.length ) return false;
        return find(arr, idx+1, sum( total, arr[idx]) ) || find(arr, idx+1, total);
    }

    public static boolean find(int[] arr) {
        if ( arr == null || arr.length == 0 ) return false;
        return find(arr, 0, null);
    }

    public static void main(String[] args ){
//        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();

        int[] arr = {8,2,1,-3};
        System.out.println(find(arr));
    }
}