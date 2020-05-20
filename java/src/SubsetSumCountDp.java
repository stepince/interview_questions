import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
/*

Given a set of distinct integers, and a value m,
count the number of subset of the given set with sum a m.
for non negative numbers
Input : arr[] = {2,4,6,10};
        m = 1;
Output : 2



                                    3,1,7,5/null
                           1,7,5/3                     1,7,5/null
                    7,5/4        7,5/3           7,5/1          7,5/null
                5/11   5/3     5/10  5/3      5/8     5/1     5/5    5/null
            /16  /11  /8 /3  /15 /10 /8 /3  /13 /8  /6  /1   /10 /5  /5  /null


 */

public class SubsetSumCountDp {

    private static int find(int[] arr, int idx, int sum, Map<String,Integer> mem){
        if ( sum == 0 ) return 1;
        if ( idx == arr.length) return 0;
        if ( arr[idx] > sum ) return 0;
        String key = idx + ":" + sum;
        if ( mem.containsKey(key)) return mem.get(key);
        int result = find(arr, idx+1, sum - arr[idx], mem) + find(arr, idx+1, sum, mem);
        mem.put(key,result);
        return result;
    }

    public static int find(int[] arr, int sum) {
        Arrays.sort(arr);
        return find(arr,0, sum, new HashMap<>());
    }

    public static void main(String[] args){
        int[] arr = Stream.of(args[0].split("[,\\s]+")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(args[1]);
        System.out.println(find(arr,m));
    }

}