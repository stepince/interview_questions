import java.util.*;
/*
  Amazon interview question:
          List all words that compose a word.
          e.g.  rockstar
          ["rockstar"] ["rock","star"]

Time:
   O(n*s^2)
   where n is the number of words
   and s is size of the longest word
 */
public class Words {

    static void findUtil(String word, int idx, Set<String> dictionary, Deque<String> currentWords, List<List<String>> allWords ){

        if ( idx == word.length() ) return;

        if ( dictionary.contains(word.substring(idx)) ){
            List<String> newWords = new ArrayList<>(currentWords);
            newWords.add( word.substring(idx) );
            allWords.add( newWords );
        }

        for ( int i = idx + 1; i < word.length(); ++i ){
            String substr = word.substring(idx,i);
            if ( dictionary.contains(substr) ) {
                currentWords.add(substr);
                findUtil(word, i, dictionary, currentWords, allWords);
                currentWords.removeLast();
            }
        }
    }

    static List<List<String>> find(String word, Set<String> dictionary){
        List<List<String>> allWords = new ArrayList<>();
        findUtil(word,0, dictionary, new LinkedList<>(), allWords);
        return allWords;
    }

    public static void main(String[] args){
        Set<String> dictionary = Set.of("rockstar", "rock", "star");
        for( String word: dictionary ) {
            System.out.println(find(word,dictionary));
        }
    }
}
