import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/longest-substring-without-repeating-characters/
Given a string, find the length of the longest substring without repeating characters.
 */
public class SubstringWithoutRepeat {


    static public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> m = new HashMap<>();
        int len = 0;
        int idx = -1;
        for ( int i =0; i < s.length() ; ++i ){
            char ch = s.charAt(i);
            int lastIdx = m.getOrDefault(ch,-1);
            if ( lastIdx > idx ) idx = lastIdx;
            m.put(ch,i);
            len = Math.max(i-idx,len);
        }
        return len;
    }

    public static void main(String[] args){
        String s = "abc";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
