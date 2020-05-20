import java.util.Stack;

public class AB {

    public static boolean find(String str){
        Stack<Character> st = new Stack<>();
        for ( char ch : str.toCharArray() ){
            if ( ch != 'a' && ch != 'b' ) return false;
            if ( st.empty() ) {
                st.push(ch);
            }
            else if ( ch == 'b' && st.peek() == 'a' ) {
                st.pop();
            }
            else {
                st.push(ch);
            }
        }   
        return st.empty();
    }

    public static void main(String[] args){
       String str = args[0].toLowerCase();
       System.out.println(find(str));
    }
}