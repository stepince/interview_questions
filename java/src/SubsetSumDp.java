import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
/*

Given a set of distinct integers, and a value m, determine if
there is a subset of the given set with sum a m.

Input : arr[] = {3, 1, 7, 5};
        m = 11;
Output : YES

Input : arr[] = {1, 6};
        m = 5;
Output : NO


                                        3,1,7,5/null
                           1,7,5/3                     1,7,5/null
                    7,5/4        7,5/3           7,5/1          7,5/null
                5/11   5/3     5/10  5/3      5/8     5/1     5/5    5/null
            /16  /11  /8 /3  /15 /10 /8 /3  /13 /8  /6  /1   /10 /5  /5  /nul
 */

public class SubsetSumDp {

    private static boolean find(int[] arr, int idx, int sum, Map<String,Boolean> mem){
        if ( sum == 0 ) return true;
        if ( idx == arr.length ) return false;
        if ( arr[idx] > sum ) return false;
        String key =  idx + ":" + sum;
        if ( mem.containsKey(key)) return mem.get(key);
        boolean result = find(arr, idx+1, sum - arr[idx], mem) || find(arr, idx+1, sum, mem);
        mem.put(key,result);
        return result;
    }

    public static boolean find(int[] arr, int sum) {
        Arrays.sort(arr);
        return find(arr,0, sum, new HashMap<>());
    }

    public static void main(String[] args){
        int[] arr = Stream.of(args[0].split("[,\\s]+")).mapToInt(Integer::parseInt).toArray();
        int sum = Integer.parseInt(args[1]);
        System.out.println(find(arr,sum));
    }

}