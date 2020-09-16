/*


runtime
O(N)

input: 5,100,99,2,3,1,4

output: 5

 */
public class MaxConsecutiveSubarray {

    public static int find(int[] nums){
        if ( nums.length == 0 ) return 0;
        int maxLen = 1;
        int begin = 0;
        for ( int i = 1; i < nums.length ; ++i ){
            if ( nums[i-1]+1 == nums[i] ){
                maxLen = Math.max(maxLen, i-begin+1);
            }
            else {
                begin=i;
            }
        }
        return maxLen;
    }

    public static void main(String[] args){
//        int[] arr =  Arrays.stream(args[0].split("[,\\s]")).mapToInt(Integer::parseInt).toArray();
        int[] nums = {5,100,99,2,3,1,4,1,2,3,7,8,9,10};

//        int[] nums={1, 9, 3, 10, 4, 20, 2};
        System.out.println(find(nums));
    }
}
