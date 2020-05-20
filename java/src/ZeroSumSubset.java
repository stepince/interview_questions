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

    private static boolean find(int[] arr, int idx, Integer sum ){
        if ( sum == 0 ) return true;
        if ( idx == 0 ) return false;
        // you can add or subtract -7,7 or 0- (-7) - 7 == 0
        return find(arr, idx-1, sum - arr[idx-1]) || find(arr, idx-1, sum);
    }
    public static boolean find(int[] arr) {
        if ( arr == null || arr.length == 0 ) return false;
        int sum = Arrays.stream(arr).sum();
        return find(arr, arr.length, sum);
    }
    public static void main(String[] args ){
        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(find(arr));
    }
}