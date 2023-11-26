import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


/*
https://leetcode.com/problems/sliding-window-maximum/

 */
public class MaxSlidingWindow {


    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length-k+1];
        Deque<Integer> deq = new ArrayDeque<>();
        // init window
        for ( int i = 0; i < k; ++i ) {
            while ( !deq.isEmpty() && nums[i] > nums[deq.peekLast()] ) deq.removeLast();
            deq.addLast(i);
        }

        ans[0] = nums[deq.peekFirst()];
        for ( int i = k, j = 1; i < nums.length; ++i, ++j ){
            if ( i >= deq.peekFirst() + k ) deq.removeFirst();

            // decreasing monotone queue
            while ( !deq.isEmpty() && nums[i] > nums[deq.peekLast()] ) deq.removeLast();
            deq.addLast(i);
            ans[j] = nums[deq.peekFirst()];
        }
        return ans;
    }

    public static void main(String[] args){
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums,k)));
    }

}
