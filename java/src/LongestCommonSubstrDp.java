
/*

input : x = “GeeksforGeeks”, y = “GeeksQuiz"
output: Geeks

input : x = “abc123abc”, y = “123"
output:  123

find2: O(N^2)



notes: you can multiple common string. this is only returning one
 */

public class LongestCommonSubstrDp {

    private static String maxWord(String word1, String word2) {
        return word1.length() > word2.length() ? word1 : word2;
    }

    private static String findUtil(char[] chars1, int idx1, char[]  chars2, int idx2, String[][] mem){
        if ( idx1 == chars1.length || idx2 == chars2.length ) return "";
        if ( mem[idx1][idx2] != null ) return mem[idx1][idx2];
        return mem[idx1][idx2] = ( chars1[idx1] == chars2[idx2] )
                ? (chars1[idx1] + findUtil(chars1,idx1+1, chars2, idx2+1,mem))
                : "";
    }

    public static String find(String word1, String word2){
        char[] chars1 =  word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        String maxWord = "";
        if (chars1.length == 0 ||  chars2.length == 0 ) return "";
        String[][] mem = new String[chars1.length][chars2.length];
        for ( int i = 0; i < chars1.length;  ++i ){
            for ( int j = 0; j < chars2.length ; ++j ){
                if ( chars1[i] == chars2[j] ){
                    maxWord = maxWord( maxWord, chars1[i] + findUtil(chars1,i+1 , chars2,j+1,mem));
                }
            }
        }
        return maxWord;
    }


    public static void main(String[] args){
//        String word1 = args[0];
//        String word2 = args[1];
        String word1 = "GeeksForGeeks";
        String word2 = "GeeksQuiz";
        long t = System.currentTimeMillis();
        System.out.println(find(word1,word2));
        System.out.println("dp time = " + ( System.currentTimeMillis() - t ) );

//        t = System.currentTimeMillis();
//        System.out.println(find2(word1,word2));
//        System.out.println("time 2 = " + ( System.currentTimeMillis() - t ) );
    }

}