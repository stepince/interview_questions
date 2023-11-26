import java.util.*;
import java.util.stream.Collectors;
/*

https://leetcode.com/problems/alien-dictionary

get the alphabetical order of an alien dictionary


There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.

You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted lexicographically by the rules of this new language.

Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "".
If there are multiple solutions, return any of them.

A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes before the letter in t in the alien language.
If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.

input:  ["wrt","wrf","er","ett","rftt"]
output: "wertf"

Time:
  O(V+E)
  O(N+A)
  where N is the number of words A and is the size of the alphabet
Space:
  O(V+E)  ( The sum of the all the characters )

  DFS for cycle detection
  Topological sorting
 */

public class AlienDictionary2 {

    Map<Character,Set<Character>> graph = new HashMap<>();

    boolean dfsUtil( char node, Map<Character,Boolean> visited, Deque<Character> deq ){
        if ( visited.containsKey(node) ) return visited.get(node);
        visited.put(node,true);
        for ( char ch: graph.get(node) ){
            if ( dfsUtil( ch, visited, deq) ) return true;
        }
        visited.put(node,false);
        deq.push(node);
        return false;
    }

    Deque<Character> dfs(){
        Deque<Character> deq = new ArrayDeque<>();
        Map<Character,Boolean> visited = new HashMap<>();
        for ( char ch: graph.keySet() ) {
            if ( dfsUtil( ch, visited, deq ) ) return new ArrayDeque<>();
        }
        return deq;
    }

    public String alienOrder(String[] words) {
        for ( String word: words ){
            for ( char ch: word.toCharArray() ){
                graph.putIfAbsent(ch, new HashSet<>());
            }
        }
        boolean same = true;
        for ( int i = 1; i < words.length; ++i ){
            String word1 = words[i-1];
            String word2 = words[i];
            int len1 = word1.length();
            int len2 = word2.length();
            for ( int j = 0; j < Math.min(len1,len2); ++j ){
                char ch1 = word1.charAt(j);
                char ch2 = word2.charAt(j);
                if ( ch1 != ch2 ) {
                    same = false;
                    graph.get(ch1).add(ch2);
                    break;
                }
            }
            if ( same && len1 > len2 ) return "";
        }
        return dfs().stream().map(String::valueOf).collect(Collectors.joining());
    }

    public static void main(String[] args){
        AlienDictionary2 alienDictionary = new AlienDictionary2();
        String[] words = {"bgg", "fbg", "fqf", "ffq", "gfg"};
        System.out.println(alienDictionary.alienOrder(words));
    }
}
