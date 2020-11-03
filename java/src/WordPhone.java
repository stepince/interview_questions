import java.util.*;
/*


youtube question

get all possible words from the number

 */
public class WordPhone {

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

    static List<String> getWords(char[] chars, int idx, String prefix, Set<String> words){
        List<String> results = new ArrayList<>();
        if (words.contains(prefix) ) results.add(prefix);
        if ( idx == chars.length ) return results;
        for ( int i = idx; i < chars.length; ++i ){
            for ( char ch:  MAP.get(chars[idx]) ) {
                results.addAll( getWords(chars,idx+1,prefix+ch,words) );
            }
        }
        return results;
    }

    static Set<String> getWords(String str, Set<String> words){
        Set<String> results = new HashSet<>();
        char[] chars = str.toCharArray();
        for ( int i = 0; i < chars.length; ++i ){
            for ( char ch:  MAP.get(chars[i]) ) {
                results.addAll(  getWords(chars,i+1,String.valueOf(ch),words) );
            }
        }
        return results;
    }

    public static void main(String[] args){
        String phone = "3662277";
        String[] words = {"foo", "bar", "foobar", "baz", "emo", "cap", "car", "cat"};

        System.out.println(getWords(phone, Set.of(words)));
    }
}
