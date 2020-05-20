import java.util.HashMap;
import java.util.Map;

public class SuffixTrie {

    final private Map<Character,SuffixTrie> map = new HashMap<>();

    private SuffixTrie(){}

    public String toString(){
        return this.map.toString();
    }

    public SuffixTrie(String str){
        str += "$";
        char[] array = str.toCharArray();
        for ( int i = 0; i < array.length; ++i ){
            add(this,array,i);
        }
    }

    private void add(SuffixTrie trie, char[] array, int idx){
        char ch = array[idx];
        if ( !trie.map.containsKey(ch) ){
            trie.map.put(ch, new SuffixTrie());
        }
        if( idx + 1 < array.length ) {
            add(trie.map.get(ch),array, idx+1);
        }
    }

    public static void main(String[] args){
        String str = args[0].trim();
        SuffixTrie trie  = new SuffixTrie(str);
        System.out.println(trie);
    }
}