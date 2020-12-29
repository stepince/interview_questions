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

    private static int find1(int[] arr, int idx, Integer total, Map<String,Integer> mem ) {
        if (idx == arr.length) {
            return (total != null && total == 0) ? 1 : 0;
        }
        String key = idx + ":" + total;
        if (mem.containsKey(key)) return mem.get(key);
        int ans = find1(arr, idx + 1, sum(total, arr[idx]), mem) + find1(arr, idx + 1, total, mem);
        mem.put(key, ans);
        return ans;
    }

    public static int find2Util(int[] nums, int idx, int total, Map<String,Integer> mem) {
        if ( idx == nums.length && total == 0 ) return 1;
        if ( idx == nums.length ) return 0;
        String key = idx + ":" + total;
        if ( mem.containsKey(key) ) return mem.get(key);
        Integer result = find2Util( nums, idx+1,total + nums[idx], mem) + find2Util( nums, idx+1, total,mem);
        return mem.merge(key,result,(curr,prev)->curr);
    }

    public static int find2(int[] nums) {
        int count = 0;
        Map<String,Integer> mem =  new HashMap<>();
        for ( int i = 0; i < nums.length; ++i ){
            count += find2Util(nums,i+1,nums[i],mem);
        }
        return count;
    }

    public static int find1(int[] nums) {
        return find1(nums, 0, null, new HashMap<>());
    }

    public static void main(String[] args){
//        int[] arr = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int[] nums = {9,2, 2,-4,-4,5,8};
        System.out.println(find1(nums));
        System.out.println(find2(nums));
    }
}