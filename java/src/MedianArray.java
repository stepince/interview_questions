
/*

   Glassdoor quuestion for Corelogic very similiar to Leetcode find median in a stream
   find the median in an array of numbers

 */

import java.util.PriorityQueue;
import java.util.Queue;

public class MedianArray {

    public static double find(int[] nums){
        Queue<Integer> smallQ = new PriorityQueue<>( (a,b)->b-a );
        Queue<Integer> largeQ = new PriorityQueue<>();
        for ( int num : nums ) {
            if (largeQ.size() < smallQ.size()) {
                largeQ.add(num);
            }
            else {
                smallQ.add(num);
            }
            if (smallQ.size() > 0 && largeQ.size() > 0 && smallQ.peek() > largeQ.peek()) {
                smallQ.add(largeQ.remove());
                largeQ.add(smallQ.remove());
            }
        }
        if ( smallQ.size() == 0 && largeQ.size() == 0    ) {
            return 0;
        }
        else if (smallQ.size() > largeQ.size()) {
            return smallQ.peek();
        }
        else if (largeQ.size() > smallQ.size()) {
            return largeQ.peek();
        }
        else {
            return (double)(smallQ.peek() + largeQ.peek()) / 2;
        }
    }

    public static void main(String[] argss){
        int[] nums= { 7,4,5,2};
        System.out.println(find(nums));
    }
}
