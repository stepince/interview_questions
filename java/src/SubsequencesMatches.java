import java.util.*;

/*
https://leetcode.com/problems/number-of-matching-subsequences/

Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.

A subsequence of a string is a new string generated from the original string with some characters (can be none)
 deleted without changing the relative order of the remaining characters.


 */
public class SubsequencesMatches {
    static class Node {
        int index;
        char[] chars;
        Node(char[] chars){
            this.chars = chars;
        }
    }

    public int numMatchingSubseq(String s, String[] words) {
        Map<Character, List<Node>> wordMap = new HashMap<>();
        for ( String w : words) {
            char[] chars = w.toCharArray();
            wordMap.computeIfAbsent(chars[0],(key)->new ArrayList<>()).add(new Node(chars));
        }
        int total = 0;

        for ( char ch: s.toCharArray()){
            List<Node> oldBucket = wordMap.getOrDefault(ch, Collections.emptyList());
            wordMap.remove(ch);
            for( Node node: oldBucket ){
                if ( ++node.index == node.chars.length ){
                    ++total;
                }
                else {
                    wordMap.computeIfAbsent(node.chars[node.index],(key)->new ArrayList<>()).add(node);
                }
            }
        }
        return total;
    }
}
