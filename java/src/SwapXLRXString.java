
/*

  Time:
     O(n)

https://leetcode.com/problems/swap-adjacent-in-lr-string/

 */

import java.util.ArrayList;
import java.util.List;

public class SwapXLRXString {

    public static boolean canTransform(char[] start, char[] end) {
        int len = start.length;
        List<Integer> startL = new ArrayList<>();
        List<Integer> startR = new ArrayList<>();

        List<Integer> endL = new ArrayList<>();
        List<Integer> endR = new ArrayList<>();

        for ( int i = 0;i < len; ++i ){
            if ( start[i] == 'L' ) {
                startL.add(i);
            }
            else if ( start[i] == 'R' ) {
                startR.add(i);
            }

            if ( end[i] == 'L' ) {
                endL.add(i);
            }
            else if ( end[i] == 'R' ) {
                endR.add(i);
            }
        }

        int lSize =  startL.size();
        for ( int i = 0;i < lSize ; ++i ){
            if ( endL.get(i) > startL.get(i)  ) return false;
        }
        int rSize =  startR.size();
        for ( int i = 0;i < rSize ; ++i ){
            if ( startR.get(i) > endR.get(i) ) return false;
        }
        return true;
    }

    public static boolean canTransform(String start,String end) {
        if ( !start.replaceAll("X","").equals(end.replaceAll("X","" )) || start.length() != end.length()  ) return false;
        return  canTransform(start.toCharArray(), end.toCharArray());    }

    public static void main(String[] args){
        String start = "XRLXLXXXLXRXXXLXLLXX";
        String  end = "XRXXLXXLXLRXXLXXLXXL";
        System.out.println( canTransform(start, end));
    }
}
