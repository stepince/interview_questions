import java.util.*;
import java.util.stream.Collector;

/*
https://www.geeksforgeeks.org/trie-insert-and-search/
https://leetcode.com/problems/implement-trie-prefix-tree/

   add
   O(M)
   where M is the maximum string length

   addAll
   O(N*L)
   where L is the average string length and
   and N is the number of words

   search
  O(M);
   where M is the maximum string length

   space
   O(K^N)
   where K is the reference size (alphabet) and N is the number of words

   Note:
       All the descendants of tree share a common prefix
 */
public class Trie {

    final private Map<Character,Trie> nodes = new HashMap<>();
    private boolean word = false;

    public void add(String word){
        if ( word == null || word.length() == 0 ) return;
        Trie currTrie = this;
        for( char ch: word.toCharArray() ){
            currTrie = currTrie.nodes.computeIfAbsent(ch, (k)-> new Trie() );
        }
        currTrie.word = true;
    }

    public void addAll(String[] words){
        for(String word: words) add(word);
    }

    private List<String> getAllWords(Trie t, String prefix){
        List<String> words = new ArrayList<>();
        if ( t.word ) words.add(prefix);
        for ( Character ch : t.nodes.keySet() ){
            Trie currTrie =  t.nodes.get(ch);
            words.addAll( getAllWords(currTrie,prefix + ch ));
        }
        return words;
    }

    public List<String> search(String prefix){
        Trie currTrie = this;
        for( char ch: prefix.toCharArray() ){
            if (  (currTrie = currTrie.nodes.get(ch)) == null ) return Collections.emptyList();
        }
        return getAllWords(currTrie,prefix);
    }

    public static void main(String[] args){
//        String[] words = args[0].split("[\\s,]+");
//        String prefix = args[1];

        String[] words = {"the", "a", "there", "answer", "any", "by", "bye", "their"};
        String prefix = "the";
        Trie trie = new Trie();
        trie.addAll(words);
        System.out.println(trie.search(prefix));
    }
}