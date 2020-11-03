import java.util.*;

/*

https://leetcode.com/problems/letter-combinations-of-a-phone-number/


 */
public class LetterCombinations {

    static final Map<Character, Set<Character>> MAP = new HashMap<>() {{
        put( '1', Collections.EMPTY_SET  );
        put( '2', Set.of('a','b','c'));
        put( '3', Set.of('d','e','f'));
        put( '4', Set.of('g','h','i'));
        put( '5', Set.of('j','k','l'));
        put( '6', Set.of('m','n','o'));
        put( '7', Set.of('p','q','r', 's'));
        put( '8', Set.of('t','u','v'));
        put( '9', Set.of('w','x','y', 'z'));
    }};

    static void letterCombinationsUtil(char[] chars, int idx, StringBuilder prefix, List<String> results ){
        if ( chars.length == idx ) {
            results.add(prefix.toString());
            return;
        }
        int prefixSize = prefix.length();
        for ( char ch: MAP.get(chars[idx]) ) {
            prefix.append(ch);
            letterCombinationsUtil(chars,idx+1,prefix,results);
            prefix.setLength(prefixSize);
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        if ( digits == null || digits.length() == 0 ) return results;
        letterCombinationsUtil(digits.toCharArray(), 0, new StringBuilder(), results );
        return results;
    }

}
