/*

https://leetcode.com/problems/shortest-way-to-form-string/

From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).

Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.

I actually implemented this using the following strategy:
     For each used subsequence try to match the left most character of the current subsequence with the leftmost character of the target string, if they match then erase both character otherwise erase just the subsequence character whenever the current subsequence gets empty, reset it to a new copy of subsequence and increment the count, do this until the target sequence gets empty. Finally return the count.
 */
public class ShortestWayString {

    public boolean removeChars( String source, int idx, StringBuilder target){
        boolean deleteChar = false;
        for ( int i = idx; i >= 0  && target.length() > 0  ; --i ){
            // important to delete at end of string cost is O(1);
            if( source.charAt(i) == target.charAt(target.length()-1) ) {
                deleteChar = true;
                target.deleteCharAt(target.length()-1);
            }
        }
        return deleteChar;
    }

    public int shortestWay(String source, String target) {
        StringBuilder sb = new StringBuilder(target);
        int opers = 0;
        for ( int i = source.length() -1; i >= 0 ; --i ){
            while ( removeChars(source,i, sb) ) ++opers;
        }
        return sb.length() == 0 ? opers : -1;
    }
}
