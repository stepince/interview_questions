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
   no real gain, the merging is expensive, appendWords is a loop

 */
public class WordsDp {

    static List<List<String>> appendWords(String word, List<List<String>> suffices ){
        if ( suffices == null  ) return null;
        List<List<String>> wordList = new ArrayList<>();
        for( List<String> suffix: suffices){
            List<String> l = new ArrayList<>(Collections.singletonList(word));
            l.addAll(suffix);
            wordList.add(l);
        }
        return wordList;
    }

    static void combineWordList( List<List<String>> wordList1, List<List<String>> wordList2){
        if ( wordList2 == null ) return;
        wordList1.addAll(wordList2);
    }

    static List<List<String>> findUtil(String word, int idx, Set<String> dictionary, Map<Integer, List<List<String>> > mem){

        if ( idx == word.length() ) return null;

        if ( mem.containsKey(idx) ){
            return new ArrayList<>( mem.get(idx)  );
        }

        List<List<String>> wordList = new ArrayList<>();
        if ( dictionary.contains(word.substring(idx)) ){
            // add the word as a list item
            wordList.add( new ArrayList<>(Collections.singletonList(word.substring(idx))) );
        }

        for ( int i = idx + 1; i < word.length(); i++){
            String substr = word.substring(idx,i);
            if ( dictionary.contains(substr)) {
                combineWordList( wordList, appendWords( substr, findUtil(word, i, dictionary,mem) )  );
            }
        }

        mem.put(idx, new ArrayList<>(wordList));
        return wordList;
    }

    static List<List<String>> find(String word, Set<String> dictionary){
        return findUtil(word,0,dictionary, new HashMap<>());
    }

    public static void main(String[] args){

//        Set<String> dictionary = Set.of("rockstar", "rock", "star");
        Set<String> dictionary = Set.of("i", "like", "sam", "sung", "samsung", "ilikesamsung");

        for( String word: dictionary ) {
            System.out.println(find(word,dictionary));
        }
    }
}
