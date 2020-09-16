import java.util.*;
import java.util.function.Consumer;

/*

https://leetcode.com/problems/next-greater-element-iii/

two implementations

nextGreaterElement1: faster, using partition

nextGreaterElement2: slower, using permutation brute force

 */
public class NextGreaterInteger {

    static int[] digitize(int n){
        List<Integer> nums = new ArrayList<>();
        for( int i = n; i != 0; i/=10 ){
            nums.add(i%10);
        }
        //        Collections.reverse(nums);
        return nums.stream().mapToInt(Integer::intValue).toArray();
    }

    static long longValue(int[] nums){
        long val = 0;
        for (int num : nums) {
            val = (10 * val + num);
        }
        return val;
    }

    static void swap( int[] nums, int idx1, int idx2 ){
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

    static void reverse(int[] nums){
        for ( int i = 0, j = nums.length-1; i < j;++i,--j){
            swap(nums,i,j);
        }
    }

    static void permutation( int[] nums, int idx, Consumer<Long> x ) {
        if (idx == nums.length) {
            x.accept(longValue(nums));
        }
        for (int i = idx; i < nums.length; ++i) {
            swap(nums, idx, i);
            permutation(nums, idx + 1, x);
            swap(nums, idx, i);
        }
    }

    // faster implementation
    // algorithm is finding the partition and then sorting number on the left
    static public int nextGreaterElement1(int n) {
        // nums is in reverse order
        int[] nums = digitize(n);
        reverse(nums);
        int partitionIdx = -1;

        // find the lower partition bound index
        for( int i = nums.length-1; i > 0 ; --i ){
            if ( nums[i-1] < nums[i] ){
                partitionIdx = i;
                break;
            }
        }
        if ( partitionIdx ==  -1 ) return -1;
        int partitionLowIdx = partitionIdx-1;

        // find the upper partition bound index
        for ( int i = partitionIdx; i < nums.length; ++i){
            if ( nums[i] > nums[partitionLowIdx]  && nums[i] < nums[partitionIdx] ){
                partitionIdx = i;
            }
        }
        // swap the partition indices (lower,upper)
        swap(nums,partitionLowIdx,partitionIdx);
        // sort the values right side of the partition
        Arrays.sort(nums,partitionLowIdx+1,nums.length);
        long result = longValue(nums);
        return result >= Integer.MAX_VALUE ? -1 : (int)result;
    }

    // slower brute force implementation
    static public int nextGreaterElement2(int n) {
        final List<Integer> results = new ArrayList<>();
        Consumer<Long> x = (a) -> {
            if ( a > n && a < Integer.MAX_VALUE) {
                results.add((int)a.longValue());
            }
        };
        permutation(digitize(n),0,x);
        return results.size() == 0 ? -1 : Collections.min(results) ;
    }

    static public void main(String[] args){
        int n = 12443322;
        long t = System.currentTimeMillis();
        // note: these test cases do now show nextGreaterElement1 is faster
        System.out.println(nextGreaterElement1(n));
        System.out.println("time 1: " + (System.currentTimeMillis()-t));
        System.out.println(nextGreaterElement2(n));
        t = System.currentTimeMillis();
        System.out.println("time 2: " + (System.currentTimeMillis()-t));
    }
}
