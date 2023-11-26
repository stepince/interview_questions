import java.util.Arrays;
/*
   https://leetcode.com/problems/next-permutation

 */
public class NextPermutation {


    static void swap(int[] nums, int a, int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    static void reverse(int[] nums, int idx){
        for( int i = idx, j = nums.length-1; i < j; ++i, --j ) {
            swap(nums, i, j);
        }
    }

    public static void nextPermutation(int[] nums) {
        // decreasing element
        Integer decrIdx = null;
        for( int i = nums.length-2; i >=0 ; --i){
            if( nums[i] < nums[i+1]){
                decrIdx = i;
                break;
            }
        }

        if ( decrIdx == null ){
            Arrays.sort(nums);
            return;
        }
        // next biggest number that is furthurest away hence "<="
        Integer incrIdx = decrIdx+1;
        for( int i = decrIdx+2; i < nums.length; ++i){
            if( nums[i] > nums[decrIdx] && (nums[i] <= nums[incrIdx]) ){
                incrIdx = i;
            }
        }
        swap(nums,incrIdx,decrIdx);
        // reverse the array after the
        reverse(nums, decrIdx +1);
    }

    public static void main(String[] args){
        int[] nums = {2,5,6,1,8};
        System.out.println(Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
