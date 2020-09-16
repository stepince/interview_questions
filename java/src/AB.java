import java.util.Stack;

public class AB {

    public static boolean find(String str){
        Stack<Character> st = new Stack<>();
        for ( char ch : str.toCharArray() ){
            if ( ch != 'a' && ch != 'b' ) return false;
            if ( st.empty() ) {
                st.push(ch);
            }
            else if ( st.peek() == 'a' && ch == 'b') {
                st.pop();
            }
            // return false
            else if ( ch == 'b') {
                return false;
            }
            else {
                st.push(ch);
            }
        }   
        return st.empty();
    }

    public static void main(String[] args){
//       String str = args[0].toLowerCase();
        String str = "aabaaabbbabb";
       System.out.println(find(str));
    }
}