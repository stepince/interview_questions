/*

Google question.

Given an Array A, find the minimum amplitude you can get after changing up to 3 elements.
Amplitude is the range of the array (basically difference between largest and smallest element).

 */

import java.util.*;

public class MinAmplitude {

    public static int findMinAmplitude(int[] nums, int K){
        if ( nums.length <= K+1 ) return 0;

        Queue<Integer> minQueue = new PriorityQueue<>();
        Queue<Integer> maxQueue = new PriorityQueue<>((a,b)->b-a);

        // get the min/max K values O(nlgK);
        for ( int num : nums ){
            minQueue.add(num);
            maxQueue.add(num);
            if ( minQueue.size() > K + 1 ) minQueue.remove();
            if ( maxQueue.size() > K + 1 ) maxQueue.remove();
        }

        // get the max values from the minHeap;
        List<Integer> maxList = new ArrayList<>();
        while( !minQueue.isEmpty() ) maxList.add(minQueue.remove());

        // get the min values from the maxnHeap;
        List<Integer> minList = new ArrayList<>();
        while( !maxQueue.isEmpty() ) minList.add(maxQueue.remove());

        // loop thru all min/max values and find the min diff
        int min = Integer.MAX_VALUE;
        for ( int i = 0, j = K ; i < K; ++i, --j ){
            int maxVal = maxList.get( i );
            int minVal = minList.get( j );
            min = Math.min(min, Math.abs(maxVal-minVal ) );
        }
        return min;
    }

    public static void main(String[] args){
//        int[] nums = {10, 10, 3, 4, 10};
        int[] nums = {-1, 3, -1, 8, 5, 4};
        int K = 3;
        System.out.println( findMinAmplitude(nums,K)  );
    }
}
