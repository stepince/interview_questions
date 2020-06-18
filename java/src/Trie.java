import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
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

    final private Map<Character,Trie> map = new HashMap<>();
    private boolean word = false;

    public void add(String word){
        if ( word == null || word.length() == 0 ) return;
        Trie currTrie = this;
        for( char ch: word.toCharArray() ){
            currTrie.map.putIfAbsent(ch, new Trie());
            currTrie = currTrie.map.get(ch);
        }
        currTrie.word = true;
    }

    public void addAll(String[] words){
        for(String word: words) add(word);
    }

    private List<String> getAllWords(Trie t, String prefix){
        List<String> words = new ArrayList<>();
        if ( t.word ) words.add(prefix);
        for ( Character ch : t.map.keySet() ){
            Trie currTrie =  t.map.get(ch);
            words.addAll( getAllWords(currTrie,prefix + ch ));
        }
        return words;
    }

    public List<String> search(String prefix){
        Trie currTrie = this;
        for( char ch: prefix.toCharArray() ){
            if ( !currTrie.map.containsKey(ch) ) return null;
            currTrie = currTrie.map.get(ch);
        }
        return getAllWords(currTrie,prefix);
    }

    public static void main(String[] args){
        String[] words = args[0].split("[\\s,]+");
        String prefix = args[1];
        Trie trie = new Trie();
        trie.addAll(words);
        System.out.println(trie.search(prefix));
    }
}