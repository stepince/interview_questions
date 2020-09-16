import java.util.HashSet;
import java.util.Set;

public class CircularArrayLoop {

    static int mod(int a, int b){
        int c = a % b;
        return (c < 0) ? c + b : c;
    }

    public static boolean circularArrayLoop(int[] nums) {
        Set<Integer> visited = new HashSet<>();
        int len = nums.length;
        Integer idx = null;
        for ( int i = 0; i < len; ++i ) {
            if (idx == null) {
                idx = i;
                visited.add(i);
            }
            int prevIdx = idx;
            idx += nums[idx];
            idx = mod(idx,len);
            if ( idx == prevIdx ) {
                idx = null;
                visited.clear();
            }
            else if ((nums[idx] > 0 && nums[prevIdx] > 0) || (nums[idx] < 0 && nums[prevIdx] < 0)) {
                if (visited.contains(idx)) return true;
                visited.add(idx);
            }
            else {
                idx = null;
                visited.clear();
            }
        }
        return false;
    }

    public static void main(String[] args){

//        int[] nums = {2,-1,1,2,2};
//        int[] nums = {3,1,2};
//        int[] nums = {-1,2};
        int[] nums = {-1,-1,-3};
        System.out.println(circularArrayLoop(nums));
    }
}




