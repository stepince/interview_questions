/*

   Sentence Screen Fitting

   Given a rows x cols screen and a sentence represented as a list of strings, return the number of times the given sentence can be fitted on the screen.

   The order of words in the sentence must remain unchanged, and a word cannot be split into two lines. A single space must separate two consecutive words in a line.

   https://leetcode.com/problems/sentence-screen-fitting/

   Time Complexity: O(N*M);
   Space Complexity: O(N*M);
 */

import java.util.Arrays;

public class ScreenSentenceFitting {

    static class CountState {
        int idx;
        int count;
        CountState(int idx, int count){
            this.idx = idx;
            this.count = count;
        }
    }

    // you can normalize to remove start
    CountState countSentences(int[] wordLength, int start, int cols, int idx ){
        int count = 0;
        for ( int j = start; j + wordLength[idx%wordLength.length] <= cols; ){
            j += wordLength[idx%wordLength.length] + 1;
            count = ( ++idx%wordLength.length == 0 ) ? count + 1 : count;
        }
        return new CountState(idx,count);
    }

    public int wordsTyping(String[] sentence, int rows, int cols) {
        int idx = 0;
        int characters = 0;
        int[] wordLength = new int[sentence.length];
        for ( int i = 0; i < sentence.length; ++i ){
            wordLength[i] = sentence[i].length();
            characters += wordLength[i] + 1;
        }

        int sentenceCount = cols/characters;
        int start = characters * sentenceCount;
        int count = sentenceCount * rows;

        CountState[] dp = new CountState[sentence.length];
        for ( int i = 0; i < rows; ++i ){
            int realIdx = idx%sentence.length;
            if ( dp[realIdx] == null ) {
                dp[realIdx] = countSentences( wordLength, start, cols, realIdx );
            }
            idx = dp[realIdx].idx;
            count += dp[realIdx].count;
        }
        return count;
    }

    public static void main(String[] args){
        String[] sentence = {"hello", "world"};
        var rows = 2;
        var cols = 8;
        ScreenSentenceFitting screenSentenceFitting = new ScreenSentenceFitting();
        System.out.println(Arrays.toString(sentence));
        System.out.println(screenSentenceFitting.wordsTyping(sentence,rows,cols));
    }

}



