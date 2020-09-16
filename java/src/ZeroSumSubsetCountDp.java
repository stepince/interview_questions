//import java.util.Arrays;
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
    public static Integer sum ( Integer a, Integer b ){
        if ( a == null ) return b;
        return a + b;
    }

    private static int find(int[] arr, int idx, Integer total, Map<String,Integer> mem ){
        if ( idx == arr.length ) {
            return ( total != null && total == 0 ) ? 1 : 0;
        }
        String key = idx + ":" + total;
        if ( mem.containsKey(key)) return mem.get(key);
        int ans = find(arr, idx+1, sum(total,arr[idx]),mem) + find(arr, idx+1, total,mem);
        mem.put(key,ans);
        return ans;
    }

    public static int find(int[] arr) {
        return find(arr, 0, null, new HashMap<>());
    }

    public static void main(String[] args){
//        int[] arr = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();

        int[] arr = {9,2, 2,-4,-4,5,8};
        System.out.println(find(arr));
    }
}