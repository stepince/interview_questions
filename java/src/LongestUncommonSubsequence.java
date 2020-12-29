/*
Given two strings a and b, find the length of the longest uncommon subsequence between them.
https://leetcode.com/problems/longest-uncommon-subsequence-i/

 */

public class LongestUncommonSubsequence {

    public static int findLUSlength(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(),b.length());
    }

    public static void main(String[] args){
        String a = "aaaa";
        String b = "b";
        System.out.println(findLUSlength(a,b));
    }
}
