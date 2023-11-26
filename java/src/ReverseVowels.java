import java.util.*;

/*
 * reverse the the vowels in a word
 *
 * input: hello
 * output: holle
 *
 * input: leetcode
 * output: leotcede
 *
 *  runtime: O(N)
 *
 *  space:O(N);
 */
public class ReverseVowels {

    static void swap(char[] chars, int i, int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static String reverseVowels3(String str) {
        char[] chars = str.toCharArray();
        Set<Character> vowels = Set.of('a','e','i','o','u');
        List<Integer> vowelsIndices = new ArrayList<>();
        for ( int i = 0; i < chars.length; ++i ) {
            if ( vowels.contains(chars[i]) ) vowelsIndices.add(i);
        }
        for ( int l = 0, r = vowelsIndices.size()-1; l < r;  ++l, --r ){
            swap(chars,vowelsIndices.get(l), vowelsIndices.get(r));
        }
        return new String(chars);
    }
    public static String reverseVowels(String str) {
        Set<Character> vowels = Set.of('a','e','i','o','u','A','E','I','O','U');
        char[] chars = str.toCharArray();
        List<Integer> vowelsIndices = new ArrayList<>();

        for( int i = 0; i < str.length(); ++i){
            if ( vowels.contains(str.charAt(i)) ) vowelsIndices.add(i);
        }

        for( int i = 0; i < vowelsIndices.size(); ++i){
            int idx = vowelsIndices.get(i);
            int reverseIndex = vowelsIndices.size() - i - 1;
            chars[idx] = str.charAt(vowelsIndices.get(reverseIndex));
        }
        return new String(chars);
    }

    public static String reverseVowels2(String str) {
        Set<Character> vowels = Set.of('a','e','i','o','u','A','E','I','O','U');
        char[] chars = str.toCharArray();
        Stack<Character> st = new Stack<>();

        for( int i = 0; i < str.length(); ++i){
            char ch = str.charAt(i);
            if ( vowels.contains(ch) ) st.push(ch);
        }
        for( int i = 0; i < str.length(); ++i){
            char ch = str.charAt(i);
            if ( vowels.contains(ch) ) chars[i] = st.pop();
        }
        return new String(chars);
    }

    public static void main(String[] args){
        // String str = args[0];
        System.out.println(reverseVowels("hello"));
        System.out.println(reverseVowels2("hello"));
        System.out.println(reverseVowels3("hello"));
        System.out.println(reverseVowels("leetcode"));
        System.out.println(reverseVowels2("leetcode"));
        System.out.println(reverseVowels3("leetcode"));
    }
}
