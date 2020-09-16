import java.util.HashMap;
import java.util.Map;

public class Anagram {

    static boolean isAnagram(String s, String t){
        // you can also use ascii array, which faster
        if ( s.length() != t.length() ) return false;
        Map<Character,Integer> freqMap1 = new HashMap<>(s.length());
        for ( Character ch: s.toCharArray()){
            freqMap1.merge(ch,1,Integer::sum);
        }
        Map<Character,Integer> freqMap2 = new HashMap<>(s.length());
        for ( Character ch: t.toCharArray()){
            freqMap2.merge(ch,1,Integer::sum);
        }
        return freqMap1.equals(freqMap2);
    }

    public static void main(String[] args){
        String str1 = "anagram";
        String str2 = "nagaram";
        System.out.println(isAnagram(str1,str2));
    }
}
