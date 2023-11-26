/*
   Given a set of integers, determine if there is a subset of the given set with sum equal to zero,
   note: not including the empty set, the empty set {} has sum 0

   input : "-8,2,1,-3"
   output : true

   input: 4,-2
   output: false

 */
public class ZeroSumSubset {

    private static Integer sum ( Integer a, Integer b ){
        if ( a == null ) return b;
        return a + b;
    }

    private static boolean find(int[] nums, int idx, Integer total ){
        if ( total != null && total == 0  ) return true;
        if ( idx == nums.length ) return false;
        return find(nums, idx+1, sum( total, nums[idx]) ) || find(nums, idx+1, total);
    }

    public static boolean find(int[] arr) {
        if ( arr == null || arr.length == 0 ) return false;
        return find(arr, 0, null);
    }

    public static boolean find2Util(int[] arr, int idx, int total) {
        if ( total == 0 ) return true;
        if ( idx == arr.length ) return false;
        return find2Util(arr,idx+1, total + arr[idx] );
    }

    public static boolean find2(int[] arr) {
        if ( arr == null || arr.length == 0 ) return false;
        for( int i = 0; i < arr.length; ++i ) {
            if ( find2Util(arr,i+1,arr[i]) )   return true;
        }
        return false;
    }

    public static void main(String[] args ){
//        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();

        int[] arr = {8,2,1,-3};
        System.out.println(find(arr));
        System.out.println(find2(arr));
    }
}