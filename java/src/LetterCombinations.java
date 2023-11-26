import java.util.*;

/*

https://leetcode.com/problems/letter-combinations-of-a-phone-number/


O(4^N)
 */
public class LetterCombinations {

    static final Map<Character, List<Character>> MAP = new HashMap<>() {{
        put( '2', List.of('a','b','c'));
        put( '3', List.of('d','e','f'));
        put( '4', List.of('g','h','i'));
        put( '5', List.of('j','k','l'));
        put( '6', List.of('m','n','o'));
        put( '7', List.of('p','q','r', 's'));
        put( '8', List.of('t','u','v'));
        put( '9', List.of('w','x','y', 'z'));
    }};

    static void letterCombinationsUtil(char[] chars, int idx, StringBuilder prefix, List<String> results ){
        if ( chars.length == idx ) {
            results.add(prefix.toString());
            return;
        }

        for ( char ch: MAP.get(chars[idx]) ) {
            letterCombinationsUtil(chars,idx+1,prefix.append(ch),results);
            // reset length to begin idx. backtrack
            prefix.setLength(idx);
        }
    }

    public static List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        if ( digits == null || digits.length() == 0 ) return results;
        letterCombinationsUtil(digits.toCharArray(), 0, new StringBuilder(), results );
        return results;
    }

    public static void main(String[] args){
        List<String> l = letterCombinations("7922");
        System.out.println(l+ "\nsize: " + l.size());
    }
}
