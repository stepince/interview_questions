import java.util.Arrays;

public class PrintPairs {



    public static void printPairs(int[] nums, int target) {
        Arrays.sort(nums);
        for (int i = 0, j = nums.length - 1; i < j; ) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                System.out.println(nums[i] + ":" + nums[j]);
                --j;
                ++i;
            }
            else if (sum < target) {
                ++i;
            }
            else {
                --j;
            }
        }
    }
    public static void main(String[] args){
        int[] nums = { 1,2,3,4,5,5,2};
        int target = 8;
        printPairs(nums,target);
    }

}
