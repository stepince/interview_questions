import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class FirstNonRepeatingChar {


    // create set of characters -> s1
    // create a second set of repeating characters -> s2
    // find the first char not in s2
    public static Character find(String str){
        Set<Character> allChars = new LinkedHashSet<>();
        Set<Character> repeatChars = new HashSet<>();
        for ( int i = 0; i < str.length(); ++i ){
            Character ch = str.charAt(i);
            if ( allChars.contains( ch ) ) repeatChars.add( ch );
            allChars.add(ch);
        }
        for ( char ch: allChars  ){
            if ( !repeatChars.contains( ch ) ) return ch;
        }
        return null;
    }

    public static void main(String[] args){
        String str = "vroomvrooms";
        System.out.println(find(str));
    }
}
