import java.util.*;
import java.util.stream.Collectors;

/*

get the alphabetical order of an alien dictionary

input:  bgg fbg fqf ffq gfg
output: [b, q, f, g]

input:  "baa" "abcd" "abca" "cab" "cad"
Runtime:
  O(V+E)
  O(N+A)
  where is the number of words and is the size of the alphabet
Space
  O(V+E)  ( The sum of the all the characters )
 */

public class AlienDictionary {

    Map<Character,Set<Character>> vertices = new HashMap<>();

    public void add(Character ch1, Character ch2) {
        vertices.putIfAbsent(ch1, new HashSet<>());
        vertices.get(ch1).add(ch2);
    }

    void dfs( List<Character> l ){
        Set<Character> visited = new HashSet<>();
        for ( Character node : vertices.keySet() ){
            if ( !visited.contains(node) ){
                dfsVisit(node,visited,l);
            }
        }
    }

    void dfsVisit( Character node, Set<Character> visited, List<Character> l){
        visited.add(node);
        for ( Character child : vertices.get(node) ){
            if ( !visited.contains(child) ){
                dfsVisit(child,visited,l);
            }
        }
        l.add(node);
    }

    /* return the alphabet in sorted order */
    public List<Character> topSort(){
        List<Character> l = new ArrayList<>();
        dfs(l);
        Collections.reverse(l);
        return l;
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
        List<String> words = Arrays.stream(args).collect(Collectors.toList());
        alienDictionary.addWords(words);
        System.out.println(alienDictionary.topSort());
    }
}
