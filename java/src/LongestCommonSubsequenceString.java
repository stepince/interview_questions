public class LongestCommonSubsequenceString {

    static String maxWord(String word1, String word2){
        return word1.length() > word2.length() ? word1 : word2;
    }

    static String lcs(char[] chars1, int idx1, char[] chars2, int idx2){
        if ( chars1.length == idx1 || chars2.length == idx2 ) return "";
        if ( chars1[idx1] == chars2[idx2] ){
            return chars1[idx1] + lcs( chars1, idx1+1, chars2, idx2+1);
        }
        else {
            String word1 = lcs( chars1, idx1, chars2, idx2+1);
            String word2 = lcs( chars1, idx1+1, chars2, idx2);
            return maxWord(word1,word2);
        }
    }

    static String lcs(String str1, String str2){
        return lcs(str1.toCharArray(), 0, str2.toCharArray(), 0);
    }

    public static void main(String[] args){
        String word1 = "ABAZDC";
        String word2 = "BACBAD";
        System.out.println( lcs(word1,word2));
    }
}
