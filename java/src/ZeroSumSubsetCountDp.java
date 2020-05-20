import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*

     Count the number of subsets that sum to zero

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

    O(N*S)
 */
public class ZeroSumSubsetCountDp {

    private static int find(int[] arr, int idx, Integer sum, Map<String,Integer> mem ){
        if ( sum == 0 ) return 1;
        if ( idx == 0 ) return 0;
        String key = idx + ":" + sum;
        if ( mem.containsKey(key)) return mem.get(key);
        int result = find(arr, idx-1, sum -arr[idx-1], mem) + find(arr,idx-1, sum, mem);
        mem.put(key,result);
        return result;
    }
    public static int find(int[] arr) {
        int sum = Arrays.stream(arr).sum();
        return find(arr, arr.length, sum, new HashMap<>());
    }
    public static void main(String[] args){
        int[] arr = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(find(arr));
    }
}