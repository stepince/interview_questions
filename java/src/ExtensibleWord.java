import java.util.Set;

public class ExtensibleWord {
    static Set<String> DICT = Set.of("hello");

    static boolean isSeq(StringBuilder str, int idx ){
        return ( (str.length() - idx) > 2 &&  (str.charAt(idx) ==  str.charAt(idx+1)) &&  (str.charAt(idx) == str.charAt(idx+2))  );
    }

    public static boolean isExtensibleWord(StringBuilder word, int idx) {
        if ( word.length() == idx ) return DICT.contains(word.toString());
        if ( isSeq(word,idx) ) {
            char ch = word.charAt(idx);
            word.deleteCharAt(idx);
            if ( isExtensibleWord( word,idx) ) return true;

            word.deleteCharAt(idx);
            if ( isExtensibleWord( word,idx) ) return true;

            word.insert(idx,ch);
            word.insert(idx,ch);
        }
        return isExtensibleWord( word, idx+1);
    }

    static boolean isExtensibleWord(String word) {
        if (  DICT.contains(word) ) return true;
        return  isExtensibleWord( new StringBuilder(word), 0);
    }

    public static void main(String[] args ){
        System.out.println( isExtensibleWord("hellooooooooooooooooooooo"));
    }
}
