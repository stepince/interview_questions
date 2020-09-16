import java.util.*;
/*
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest.
If two words have the same frequency, then the word with the lower
alphabetical order comes first.

  https://leetcode.com/problems/top-k-frequent-words/submissions/



Time:
   O(NlogK)

Space:
   O(N)

 */
public class TopKFrequentWords {

    public static List<String> topKFrequent(String[] words, int k) {

        Map<String,Integer> freqMap = new HashMap<>();
        Queue<String> queue = new PriorityQueue<>( (a,b) -> {
            int compare = freqMap.get(a)-freqMap.get(b);
            // Higher sorted get removed first
            return compare == 0 ?  b.compareTo(a) : compare;
        });
        for ( String word: words){
            freqMap.merge(word,1,Integer::sum);
        }
        for ( String word: freqMap.keySet() ){
            queue.add(word);
            if ( queue.size() > k ) queue.remove();
        }
        List<String> ans = new ArrayList<>();
        while (!queue.isEmpty()) ans.add(queue.remove());
        Collections.reverse(ans);
        return ans;
    }


    public static void main(String[] args){
//        int[] arr = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//        int k = Integer.parseInt(args[1]);

        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        System.out.println(topKFrequent(words,k));
    }
}
