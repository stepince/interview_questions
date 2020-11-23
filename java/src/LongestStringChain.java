import java.util.*;

/*

https://leetcode.com/problems/longest-string-chain/

longest path
 */
public class LongestStringChain {
    Map<String, Set<String>> graph = new HashMap<>();

    int dfsVisit( String node ){
        int max = 0;
        for ( String child : graph.getOrDefault(node, Collections.emptySet()) ){
            max = Math.max( max, 1 + dfsVisit(child) );
        }
        return max;
    }

    public int longestStrChain(String[] words) {

        Set<String> set = new HashSet<>();
        Collections.addAll(set, words);

        for ( String word: words){
            StringBuilder sb = new StringBuilder(word);
            char[] chars = word.toCharArray();
            for ( int i = 0; i < word.length() ; ++i ){
                sb.deleteCharAt(i);
                String child = sb.toString();
                if ( set.contains(child) ){
                    graph.computeIfAbsent(word, (key)->new HashSet<>()).add(child);
                }
                sb.insert(i,chars[i]);
            }
        }
        int max = 1;
        for ( String word : graph.keySet() ){
            max = Math.max(max, 1 + dfsVisit(word) );
        }
        return max;
    }
}
