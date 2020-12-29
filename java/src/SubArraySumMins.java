import java.util.Arrays;
import java.util.Stack;
/*

https://leetcode.com/problems/sum-of-subarray-minimums/
 */
public class SubArraySumMins {

    private static long sumSubarrayMinsUtil2(int[] A, int idx, int min, Long[][] mem){
        if ( idx == A.length ) return 0;
        if ( mem[idx][min] != null ) return mem[idx][min];
        long result = ( A[idx] < min )
                ? A[idx] + sumSubarrayMinsUtil2(A, idx+1, A[idx], mem)
                : min + sumSubarrayMinsUtil2(A, idx+1, min, mem);
        return mem[idx][min] = result;
    }

    // only for validating
    public static int sumSubarrayMins2(int[] A) {
        int max = Arrays.stream(A).max().getAsInt();
        Long[][] mem = new Long[A.length][max +1];
        long sum = 0;
        for ( int i = 0; i < A.length; ++i ){
            sum += sumSubarrayMinsUtil2(A,i,A[i],mem);
        }
        return (int) (sum %1000000007);
    }

    public static int sumSubarrayMins1(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int[] ple = new int[nums.length];
        int[] nle = new int[nums.length];

        long sum = 0;
        // 1,2,3,4,5
        // increasing monotone stack
        // note: it is non-inclusive (<=), no duplicates
        for ( int i = 0; i < nums.length; ++i ){
            while( !st.empty() && nums[i] <= nums[st.peek()] ) st.pop();
            ple[i]= st.empty()? -1 : st.peek();
            st.push(i);
        }

        st.clear();
        // 5,4,3,2,1
        // increasing monotone stack
        // note: it is inclusive (<) duplicates
        for ( int i = nums.length-1; i >= 0; --i ){
            while( !st.empty() && nums[i] < nums[st.peek()] ) st.pop();
            nle[i]=st.empty()? nums.length : st.peek();
            st.push(i);
        }

        // 2,5,6, (3), 4
        for ( int i = 0; i < nums.length ; ++i ){
            int pleCount = i - ple[i];
            int nleCount = nle[i] - i ;
            sum += (nums[i] * pleCount * nleCount);
        }

        return (int) (sum %1000000007);
    }

    public static void main(String[] args){
        int[] nums = {27,1,13,88,39,33,59,26,94,79,56,40,11,11,69,91,75,18,67,42,61,24,96,82,88,51,44,22,74,90,79,31,29,47,5,85,16,80,81,76,61,41,93,7,51,4,33,16,67,17,0,11,35,40,60,32,0,38,46,73,66,17,22,47,17,84,22,30,7,19,46,50,72,85,91,93,23,81,90,25,4,94,90,3,88,39};

//        int[] nums = {27,1,13,88,39,33,59,26,94};
//        int[] nums = {71,55,82,55};
//        int[] nums = {2,2};
        // 2,5,6,3,4
        // 2  2,5   2,5,6   2,5,6,3
//        int[] nums = {2,5,6, 3, 4};
        long t = System.currentTimeMillis();
        System.out.println(sumSubarrayMins1(nums));
        System.out.println("time 1 = " + ( System.currentTimeMillis() - t ) );
        t = System.currentTimeMillis();
        System.out.println(sumSubarrayMins2(nums));
        System.out.println("time 2 = " + ( System.currentTimeMillis() - t ) );
    }

}
