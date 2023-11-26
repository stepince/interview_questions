public class BinarySearchUpper {

    public static int find(int[] nums, int target){
        int lo = 0;
        int hi =  nums.length;
        while ( lo < hi ){
            int mid = lo + (hi - lo) / 2;
            // int mid = (lo + hi)/2;
            if ( target >= nums[mid] ){
                lo = mid +1;
            }
            else {
                hi = mid;
            }
        }
        return hi;
    }

    public static void main(String[] args){
//       int[] nums = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//       int num = Integer.parseInt(args[1]);
       int num = 8;
       int[] nums = {3,4,6,7,10};
       System.out.println(find(nums,num));
    }
}