import java.util.Arrays;
import java.util.Random;
/*

https://leetcode.com/problems/random-pick-with-weight/

You are given an array of positive integers w where w[i] describes the weight of ith index (0-indexed).

We need to call the function pickIndex() which randomly returns an integer in the range [0, w.length - 1]. pickIndex() should return the integer proportional to its weight in the w array. For example, for w = [1, 3], the probability of picking the index 0 is 1 / (1 + 3) = 0.25 (i.e 25%) while the probability of picking the index 1 is 3 / (1 + 3) = 0.75 (i.e 75%

*/
public class RandomWeightIndex {

    int sum;
    int[] prefixSum;
    Random rand;

    public int binarySearchLower( int[] nums, int val ){
        int lo = 0;
        int hi = nums.length;
        while ( lo < hi ){
            int mid = (lo + hi)/2;
            if ( val <= nums[mid] ){
                hi = mid;
            }
            else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    public RandomWeightIndex(int[] w) {
        this.sum = Arrays.stream(w).sum();
        this.prefixSum = new int[w.length];
        this.rand = new Random();
        prefixSum[0] = w[0];
        for ( int i = 1; i < w.length; ++i ){
            prefixSum[i] = prefixSum[i-1]+w[i];
        }
    }

    public int pickIndex() {
        int num = rand.nextInt(this.sum);
        int idx = binarySearchLower(this.prefixSum,num);
        // edge move to the next bucket
        if ( this.prefixSum[idx] == num ) ++idx;
        return idx;
    }

}
