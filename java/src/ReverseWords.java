import java.util.Set;
import java.util.Stack;

public class ReverseWords {

    final static Set<Character> SPACE = Set.of(' ','\t');

    public static String reverseWords(String s) {
        char[] chars = s.toCharArray();
        Stack<String> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < chars.length;++i){
            while( i < chars.length && SPACE.contains(chars[i]) ) ++i;
            if ( i == chars.length ) break;
            int j = i;
            while( i < chars.length && !SPACE.contains(chars[i]) ) ++i;
            st.push(new String(chars,j,i-j));
        }

        String space = "";
        while(!st.isEmpty()){
            sb.append(space);
            sb.append(st.pop());
            space = " ";
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        //String str = args[0];
        String str = "the sky is blue";
        System.out.println(reverseWords(str));
    }
}
