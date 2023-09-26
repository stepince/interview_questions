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
        char[] chars = order.toCharArray();
        for(int i = 0; i < chars.length; ++i) {
            orderMap.put(chars[i],i);
        }
        for( int i = 1; i < words.length; ++i ) {
           char[] chars1 = words[i-1].toCharArray();
           char[] chars2 = words[i].toCharArray();
           int j = 0;
           int min = Math.min(chars1.length,chars2.length);
           for (j = 0; j < min; ++j) {
               if (chars1[j] == chars2[j]) continue;
               if( orderMap.get(chars1[j]) > orderMap.get(chars2[j]) ) return false;
               break;
           }
           if (j == min && chars1.length > chars2.length ) {
               return false;
           }
        }
        return true;;
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
