/*

https://leetcode.com/problems/combination-sum-ii

Time Complexity: O(N^2)

Space Commplexity:  O(N)

 */

import java.util.*;

public class CombinationSum2 {

    List<List<Integer>> results = new ArrayList<>();
    Map<Integer,Integer> freqMap = new HashMap<>();

    void combinationSum2Util(int[] candidates, final int target, int start, Deque<Integer> deq ){
        if ( target == 0){
            results.add( new ArrayList<>(deq));
            return;
        }

        if ( start == candidates.length || target < 0 ){
            return;
        }
        for ( int i = start; i < candidates.length; ++i ){
            int num = candidates[i];
            int sum = target-num;
            if ( sum < 0 || freqMap.get(num) == 0 ) continue;

            this.freqMap.merge(num,-1,Integer::sum);
            deq.addLast(num);
            combinationSum2Util(candidates, sum, i, deq);
            this.freqMap.merge(num,1,Integer::sum);
            deq.removeLast();
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        for ( int num: candidates ){
            this.freqMap.merge(num,1,Integer::sum);
        }
        combinationSum2Util(freqMap.keySet().stream().mapToInt(x->x).toArray(), target, 0, new ArrayDeque<>());
        return results;
    }
}
