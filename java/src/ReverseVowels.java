import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

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
        System.out.println(reverseVowels("leetcode"));
        System.out.println(reverseVowels2("leetcode"));
    }
}
