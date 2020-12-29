import java.util.HashSet;
import java.util.Set;

public class SumOfTwo {


    static boolean sumOfTwo(int[] nums1, int[] nums2, int target ) {
        Set<Integer> set = new HashSet<>();
        for( int num: nums1 ) set.add(num);

        for( int num: nums2 ){
            if ( set.contains(target-num ) ) return true;
        }
        return false;
    }

    public static void main(String[] args){
        int[] nums1 = {1,2,3};
        int[] nums2 = {10,20,30,40};
        int target = 42;
        System.out.println(sumOfTwo(nums1,nums2,target));
    }
}
