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

    private static int find(int[] nums, int idx, Integer total ){
        if ( idx == nums.length ) {
            return ( total != null && total == 0 ) ? 1 : 0;
        }
        return find(nums, idx+1, sum(total,nums[idx])) + find(nums, idx+1, total);
    }

    private static int find2(int[] nums, int idx, Integer total ){
        if ( idx == nums.length ) return ( total == 0 ) ? 1 : 0;
        return find2(nums, idx+1, total+nums[idx]) + find2(nums, idx+1, total);
    }

    public static int find(int[] nums) {
        return find(nums, 0,null);
    }

    public static int find2(int[] nums) {
        // deduct 1 for the empty set;
        return find2(nums, 0,0)-1;
    }

    private static int find3Util(int[] nums, int idx, int total ){
        if ( idx == nums.length ) return ( total == 0 ) ? 1 : 0;
        return find3Util(nums, idx+1, total+nums[idx]) + find3Util(nums, idx+1, total);
    }

    public static int find3(int[] nums) {
        int count = 0;
        for ( int i = 0; i < nums.length; ++i ){
            count += find3Util(nums,i+1, nums[i] );
        }
        return count;
    }

    public static void main(String[] args){
//        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int[] nums = {9,2, 2,-4,-4,5,8};
        System.out.println(find(nums));
        System.out.println(find2(nums));
        System.out.println(find3(nums));
    }
}