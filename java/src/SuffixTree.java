import java.util.HashMap;
import java.util.Map;
/*


naive implementation
O(N^2);
 */
public class SuffixTree {

    final private Map<Character, SuffixTree> nodes = new HashMap<>();

    private SuffixTree(){}

    public String toString(){
        return this.nodes.toString();
    }

    public SuffixTree(String str){
        str += "$";
        char[] chars = str.toCharArray();
        for ( int i = 0; i < chars.length; ++i ){
            add(this,chars,i);
        }
    }

    boolean hasPattern(char[] chars, int idx, SuffixTree tree ){
        if ( idx == chars.length ) return true;
        SuffixTree suffixTree = tree.nodes.get(chars[idx]);
        return (suffixTree != null) && hasPattern(chars, idx + 1, suffixTree);
    }

    public boolean hasPattern(String str){
        return hasPattern(str.toCharArray(),0,this);
    }

    private void add(SuffixTree tree, char[] chars, int idx){
        SuffixTree suffixTree = tree.nodes.computeIfAbsent(chars[idx], (key)-> new SuffixTree());
        if ( idx + 1 < chars.length ) {
            add(suffixTree,chars, idx+1);
        }
    }

    public static void main(String[] args){
//        String str = args[0].trim();
        String str = "GeeksForGeeksGeeksQuizQuizQuizQuizQuizQuizQuizQuizQuiz";
        SuffixTree tree  = new SuffixTree(str);
        String pattern = "QuizQuizQuizQuizQuizQuizQuizQuizQuiz";
        System.out.println(tree.hasPattern(pattern));
    }
}