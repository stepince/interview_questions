import java.util.Arrays;
import java.util.stream.Stream;

public class BinarySearch {

    public static int find(int[] nums, int target){
        int lo = 0;
        int hi =  nums.length;
        int idx = - 1;
        while ( lo < hi ){
            int mid = lo + (hi - lo) / 2;
            // int mid = (lo + hi)/2;
            if ( nums[mid] == target){
                idx = mid;
                break;
            }
            else if ( target < nums[mid] ){
                hi = mid;
            }
            else {
                lo = mid +1;
            }
        }
        return idx;
    }

    public static void main(String[] args){
       int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
       int num = Integer.parseInt(args[1]);
       Arrays.sort(arr);
       System.out.println(Arrays.toString(arr));
       System.out.println(find(arr,num));
    }
}