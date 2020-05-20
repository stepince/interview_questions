import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
/*

Given a set of non-negative distinct integers, and a value m, determine if
there is a subset of the given set with sum divisible by m.

Input : arr[] = {3, 1, 7, 5};
        m = 6;
Output : YES

Input : arr[] = {1, 6};
        m = 5;
Output : NO

O(S*N)

           2,3
           2/3
         /5   /3


             2,3/null

         2/3     2/null

       /5 /3    /2    /null

 */

public class SubsetSumDivisibleByM_Dp {

    private static boolean find(int[] arr, int idx, int m, int sum, Map<String,Boolean> mem){
        // exclude sum == 0  this will always be true
        if ( sum != 0 && sum % m == 0 ) return true;
        if ( idx == 0 ) return false;
        String key = idx + ":" + sum;
        if ( mem.containsKey(key) ) return mem.get(key);
        boolean result = find(arr, idx-1, m, sum - arr[idx-1],mem) || find(arr, idx-1, m, sum,mem);
        mem.put(key,result);
        return result;
    }

    public static boolean find(int[] arr, int m) {
        int sum = Arrays.stream(arr).sum();
        return find(arr,arr.length,m,sum, new HashMap<>());
    }

    public static void main(String[] args){
        int[] arr = Stream.of(args[0].split("[,\\s]+")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(args[1]);
        System.out.println(find(arr,m));
    }

}