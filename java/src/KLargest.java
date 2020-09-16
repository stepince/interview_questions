
/*

  Time:
     O(nlgk)

https://leetcode.com/problems/kth-largest-element-in-an-array/

 */

import java.util.PriorityQueue;
import java.util.Queue;

public class KLargest {

    public static Integer findKthLargest(int[] nums, int k) {
        if ( nums.length < k ) return null;
        Queue<Integer> queue = new PriorityQueue<>();
        for ( int num: nums ){
            queue.add(num);
            if ( queue.size() > k ) queue.remove();
        }
        return queue.peek();
    }

    public static void main(String[] args){
//        int[] arr = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//        int k = Integer.parseInt(args[1]);

        int[] arr = {3,2,1,5,6,4};
        int k = 2;
        System.out.println(findKthLargest(arr,k));
    }
}
