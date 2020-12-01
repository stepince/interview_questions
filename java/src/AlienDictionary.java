import java.util.*;
/*

get the alphabetical order of an alien dictionary

input:  bgg fbg fqf ffq gfg
output: [b, q, f, g]

input:  "baa" "abcd" "abca" "cab" "cad"
Time:
  O(V+E)
  O(N+A)
  where N is the number of words A and is the size of the alphabet
Space:
  O(V+E)  ( The sum of the all the characters )

  Topological sorting
 */

public class AlienDictionary {

    Map<Character,Set<Character>> graph = new HashMap<>();
    public void add(Character ch1, Character ch2) {
        graph.computeIfAbsent(ch1, (key)->new HashSet<>()).add(ch2);
    }

    void topSortUtil( Character node, Deque<Character> results){
        for ( Character child : graph.getOrDefault(node,Collections.emptySet() ) ){
            topSortUtil(child,results);
        }
        results.addFirst(node);
    }

    /* return the alphabet in sorted order */
    public List<Character> topSort(){
        LinkedList<Character> results = new LinkedList<>();
        for( Character node: graph.keySet() ){
            topSortUtil( node, results );
        }
        return results;
    }

    /* the words are in alphabetical order */
    public void addWords( List<String> words ){
        for ( int i = 1; i < words.size(); ++i ){
            String word1 = words.get(i - 1);
            String word2 = words.get(i);
            for ( int j = 0 ; j < Math.min(word1.length(),word2.length()) ; ++j ) {
                Character ch1 = word1.charAt(j);
                Character ch2 = word2.charAt(j);
                if ( !ch1.equals(ch2) ) {
                    add(ch1,ch2);
                    break;
                }
            }
        }
    }

    public static void main(String[] args){
        AlienDictionary alienDictionary = new AlienDictionary();
        //List<String> words = Arrays.asList(args);
        long t = System.currentTimeMillis();
        List<String> words = Arrays.asList("bgg", "fbg", "fqf", "ffq", "gfg");
        alienDictionary.addWords(words);
        System.out.println(alienDictionary.topSort());
        System.out.println("time: " + ( System.currentTimeMillis() - t));
    }
}
