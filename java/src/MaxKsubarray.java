/*

Given an array and an integer K, find the maximum for each and every contiguous subarray of size k.
Input: arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}, K = 3
Output: 3 3 4 5 5 5 6

Time:
   O(n*lgk);

Space:
   O(K)
 */

import java.util.*;

public class MaxKsubarray {

    static class QueueMax extends LinkedList<Integer> {
        Queue<Integer> maxQueue = new PriorityQueue<>((a,b)->(b-a));

        public Integer getMax(){
            return maxQueue.peek();
        }

        public boolean add(Integer item){
            maxQueue.add(item);
            return super.add(item);
        }

        public Integer remove(){
            Integer item = super.remove();
            maxQueue.remove(item);
            return item;
        }
    }

    public static List<Integer> find(int[] arr, int k){
        List<Integer> l = new ArrayList<>();
        QueueMax queue = new QueueMax();
        for ( int i = 0; i < k-1; ++i ){
            queue.add(arr[i]);
        }
        for ( int i = k-1; i < arr.length; ++i){
            // add to the end
            queue.add(arr[i]);
            l.add(queue.getMax());
            // remove from the front
            queue.remove();
        }
        return l;
    }

    public static void main(String[] args){
        int[] arr = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        int k = 3;
        System.out.println(find(arr,k));
    }
}
