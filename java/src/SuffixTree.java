import java.util.HashMap;
import java.util.Map;

public class SuffixTree {

    final private Map<Character, SuffixTree> map = new HashMap<>();

    private SuffixTree(){}

    public String toString(){
        return this.map.toString();
    }

    public SuffixTree(String str){
        str += "$";
        char[] array = str.toCharArray();
        for ( int i = 0; i < array.length; ++i ){
            add(this,array,i);
        }
    }

    private void add(SuffixTree trie, char[] array, int idx){
        char ch = array[idx];
        trie.map.putIfAbsent(ch,  new SuffixTree());
        if( idx + 1 < array.length ) {
            add(trie.map.get(ch),array, idx+1);
        }
    }

    public static void main(String[] args){
        String str = args[0].trim();
        SuffixTree trie  = new SuffixTree(str);
        System.out.println(trie);
    }
}