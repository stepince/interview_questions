/*
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of
candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency
of at least one of the chosen numbers is different.
https://leetcode.com/problems/combination-sum/submissions/

Time Complexity: O(N^T/M)

Space Commplexity:  T/M


 */

import java.util.*;

public class CombinationSum {

    List<List<Integer>> result = new ArrayList<>();
    int target;

    boolean combinationSumUtil(int[] candidates, int sum, int start, Deque<Integer> deq ){
        if ( sum == this.target ){
            result.add( new ArrayList<>(deq) );
            return true;
        }
        if ( sum > this.target ){
            return false;
        }
        boolean doContinue = true;
        for ( int i = start; i < candidates.length && doContinue; ++i ){
            deq.addLast(candidates[i]);
            doContinue = combinationSumUtil(candidates, sum+candidates[i], i, deq ) ;
            deq.removeLast();
        }
        return true;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.target = target;
        Arrays.sort(candidates);
        combinationSumUtil(candidates, 0, 0, new ArrayDeque<>() );
        return result;
    }
}
