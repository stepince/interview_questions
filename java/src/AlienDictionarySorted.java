import java.util.*;
/*

In an alien language, surprisingly they also use english lowercase letters,
but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet,
return true if and only if the given words are sorted lexicographicaly in this alien language.

https://leetcode.com/problems/verifying-an-alien-dictionary/

input:  array of words, order of characters

Time:
  O(N*S)
     where N is the number of words and S is avg length of words

 */
public class AlienDictionarySorted {

    public boolean isAlienSorted(String[] words, String order) {
        Map<Character,Integer> sortedMap = new HashMap<>();
        for ( int i = 0; i < order.length(); ++i ){
            sortedMap.put(order.charAt(i),i);
        }

        for ( int i = 1; i < words.length; ++i ){
            String word1 = words[i-1];
            String word2 = words[i];
            int len = Math.min(word1.length(),word2.length());
            for ( int j = 0 ; j < len ;  ++j ) {
                Character ch1 = word1.charAt(j);
                Character ch2 = word2.charAt(j);
                if ( !ch1.equals(ch2) ) {
                    if ( sortedMap.get(ch1) < sortedMap.get(ch2) ) break;
                    else return false;
                }
                // we have are done consuming chars, make sure word1 is smaller
                else if ( j + 1 == len && word1.length() > word2.length() ){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        AlienDictionarySorted alienDictionary = new AlienDictionarySorted();
        //List<String> words = Arrays.asList(args);

        long t = System.currentTimeMillis();
//        String[] words = {"hello","leetcode"};
//        String order = "hlabcdefgijkmnopqrstuvwxyz";

//        String[] words = {"apple","app"};
//        String order = "abcdefghijklmnopqrstuvwxyz";

        String[] words = {"fxasxpc","dfbdrifhp","nwzgs","cmwqriv","ebulyfyve","miracx","sxckdwzv","dtijzluhts","wwbmnge","qmjwymmyox"};
        String order ="zkgwaverfimqxbnctdplsjyohu";
        System.out.println(alienDictionary.isAlienSorted(words,order));
        System.out.println("time: " + ( System.currentTimeMillis() - t));
    }
}
