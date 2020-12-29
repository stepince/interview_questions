import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/number-of-good-ways-to-split-a-string/

Given a string S, we can split S into 2 strings: S1 and S2. Return the number
of ways S can be split such that the number of unique characters between S1 and S2 are the same.
 */
public class SplitStringCount {

    public static int numSplits( String s ){
        int numWays = 0;
        char[] chars =  s.toCharArray();
        int[] counts = new int[chars.length];
        Set<Character> set = new HashSet<>();
        for ( int i = 0; i < chars.length; ++i ){
            set.add(chars[i]);
            counts[i] = set.size();
        }
        set.clear();
        for ( int i = chars.length-1; i > 0 ; --i ){
            set.add(chars[i]);
            if ( counts[i-1] == set.size() ) ++numWays;
        }
        return numWays;
    }

    public static void main(String[] args){
//        String str = "aaaa";
//        String str = "bac";
        String str = "ababa";
        System.out.println(numSplits(str));
    }
}
