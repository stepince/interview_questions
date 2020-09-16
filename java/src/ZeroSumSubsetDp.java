import java.util.HashMap;
import java.util.Map;

/*
   Given a set of integers, determine if there is a subset of the given set with sum equal to zero,
   note: not including the empty set, the empty set {} has sum 0

   input : "-8,2,1,-3"
   output : true

   input: 4,-2
   output: false

 */
public class ZeroSumSubsetDp {

    public static Integer sum ( Integer a, Integer b ){
        if ( a == null ) return b;
        return a + b;
    }

    private static boolean find(int[] arr, int idx, Integer total, Map<String,Boolean> mem){
        if ( total != null && total == 0  ) return true;
        if ( idx == arr.length ) return false;
        String key = idx + ":" + total;
        if ( mem.containsKey(key) ) return mem.get(key);
        boolean ans = find(arr, idx+1, sum( total, arr[idx]), mem ) || find(arr, idx+1, total,mem);
        return mem.merge(key,ans,(prev,curr)->curr);
    }

    public static boolean find(int[] arr) {
        if ( arr == null || arr.length == 0 ) return false;
        return find(arr, 0, null, new HashMap<>());
    }

    public static void main(String[] args ){
//        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();

        int[] arr = {8,7,5,-3,-1};
        System.out.println(find(arr));
    }
}