import java.util.HashSet;
import java.util.Set;

public class FirstNonRepeatingChar {


    // create set of characters -> s1
    // create a second set of repeating characters -> s2
    // find the first char not in s2
    public static Character find(String str){
        Set<Character> s1 = new HashSet<>();
        Set<Character> s2 = new HashSet<>();
        for ( int i = 0; i < str.length(); ++i ){
            Character ch = str.charAt(i);
            if ( s1.contains( ch ) ) s2.add( ch );
            s1.add(ch);
        }
        for ( int i = 0; i < str.length(); ++i ){
            if ( !s2.contains( str.charAt(i) ) ) return str.charAt(i);
        }
        return null;
    }

    public static void main(String[] args){
         System.out.println(find(args[0]));
    }
}
