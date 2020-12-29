import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/design-add-and-search-words-data-structure

 */
class WordDictionary {

    static class Trie {
        Map<Character,Trie> nodes = new HashMap<>();
        boolean word = false;
        void add( String word ){
            Trie curr = this;
            for ( char ch : word.toCharArray() ){
                curr = curr.nodes.computeIfAbsent(ch, (key)-> new Trie()  );
            }
            curr.word=true;
        }
    }

    Trie trie =  new Trie();

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        this.trie.add(word);
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search( word.toCharArray(), 0, this.trie);
    }

    private boolean search(char[] chars, int idx, Trie curr) {
        for ( int i = idx;  i < chars.length ; ++i ){
            char ch = chars[i];
            if ( ch == '.' ){
                for ( Trie sub: curr.nodes.values() ){
                    if ( search( chars, i+1, sub) ) return true;
                }
                return false;
            }
            else if ( !curr.nodes.containsKey(ch) ){
                return false;
            }
            else {
                curr = curr.nodes.get(ch);
            }
        }
        return curr.word;
    }

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */
    public static void main(String[] args){
//        String[] words = args[0].split("[\\s,]+");
//        String prefix = args[1];

        String[] words = {"the", "a", "there", "answer", "any", "by", "bye", "their"};
        String search = "the";
        WordDictionary dict = new WordDictionary();
        for ( String word: words ){
            dict.addWord(word);
        }
        System.out.println(dict.search(search));
    }
}