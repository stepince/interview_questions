
/*
Runtime
O(N!)
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrintPermutation {

    static void swap(char[] chars,int a, int b){
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }

    private static void permutation1(char[] chars, int idx){
        if ( idx == chars.length ){
//            System.out.println(new String(chars));
        }
        for ( int i = idx; i < chars.length ; ++i ){
            swap(chars,idx,i);
            permutation1(chars,idx+1);
            swap(chars,idx,i);
        }
    }

    public static void permutation1(String str){
        char[] chars = str.toCharArray();
        permutation1(chars,0);
    }

    static void set(List<Character> list, int idx,Character ch) {
        if ( list.size() == 0 ) list.add(ch);
        else list.set(idx,ch);
    }

    static void shiftLeft(List<Character> list, int idx) {
        if ( list.size() ==  0 ) return;
        for( int i = idx; i < list.size(); ++i) {
            list.set(i-1,list.get(i));
        }
        list.remove(list.size()-1);
    }

    static void shiftRight(List<Character> list, int idx) {
        if ( list.size() ==  0 ) return;
        list.add(list.get(list.size()-1));
        for(int i = list.size()-2; i > idx; --i){
            list.set(i,list.get(i-1));
        }
    }

    // Function to print all the permutations of str
    static void permutation2(String ans, String str)
    {
        // If string is empty
        if (str.length() == 0) {
//            System.out.println(ans);
        }

        for (int i = 0; i < str.length(); i++) {

            // ith character of str
            char ch = str.charAt(i);

            // Rest of the string after excluding
            // the ith character
            String ros = str.substring(0, i) + str.substring(i + 1);

            // recursive call
            permutation2(ans + ch,ros );
        }
    }

    // Function to print all the permutations of str
    static void permutation3(List<Character> prefix, List<Character> suffix)
    {
        // If string is empty
        if (suffix.size() == 0) {
//            System.out.println(prefix);
        }

//        for (int i = 0; i < suffix.size(); i++) {
//
//            // ith character of str
//            char ch = suffix.charAt(i);
//
//            // Rest of the string after excluding
//            // the ith character
//            String newSuffix = suffix.substring(0, i) + suffix.substring(i + 1);
//
//            // recursive call
//            permutation3(prefix + ch,newSuffix );
//        }


        for (int i = 0; i < suffix.size(); i++) {
            char ch = suffix.get(i);
            prefix.add(ch);
//            String newSuffix = suffix.substring(0, i) + suffix.substring(i + 1);
//            String newPrefix = prefix +suffix.charAt(i);

            shiftLeft(suffix, i+1);
            // recursive call
            permutation3(prefix, suffix);
            // backtrack
            shiftRight(suffix, i);
            set(suffix,i, ch);
            prefix.remove(prefix.size() - 1);
        }
    }

    static void permutation2(String str){
        permutation2("",str);
    }

    static void permutation3(String str){
        List<Character> suffix = str.chars().mapToObj(x->(char)x).collect(Collectors.toList());
        permutation3(new ArrayList<>(),suffix);
    }

    public static void main(String[] args){
//        String str = args[0];
        String str = "012345678912";
        long t = System.currentTimeMillis();
        permutation1(str);
        System.out.println("time 1: " + (System.currentTimeMillis()-t));

        System.out.println("---------------------");
        t = System.currentTimeMillis();
        permutation2(str);
        System.out.println("time 2: " + (System.currentTimeMillis()-t));

        System.out.println("---------------------");
        t = System.currentTimeMillis();
        permutation3(str);
        System.out.println("time 3: " + (System.currentTimeMillis()-t));
    }
}