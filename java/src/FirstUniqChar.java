import java.util.*;
/*

https://leetcode.com/problems/first-unique-character-in-a-string

 */
public class FirstUniqChar {

    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        Map<Character,Integer> charIndexMap = new LinkedHashMap<>();
        Set<Character> allChars = new LinkedHashSet<>();
        Set<Character> repeatChars = new HashSet<>();
        for ( int i = 0; i < chars.length; ++i ){
            char ch = chars[i];
            if ( allChars.contains(ch) ) {
                repeatChars.add(ch);
            }
            else {
                charIndexMap.put(ch,i);
                allChars.add(ch);
            }
        }
        for ( char ch: allChars ){
            if ( !repeatChars.contains(ch) ) return  charIndexMap.get(ch);
        }
        return -1;
    }
}
