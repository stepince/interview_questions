import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MatrixWords {

    private final static Set<String> DICTIONARY = Set.of("ice", "cow", "crate", "carter");
    static class Index {
       final int x;
       final int y;
       Index( int x, int y) {
          this.x = x;
          this.y = y;
       }

       @Override
       public boolean equals( Object o ){
           if ( o == null ) return false;
           if ( o == this ) return true;
           if ( !(o instanceof Index) ) return false;
           Index other = (Index)o;
           return this.x == other.x && this.y == other.y;
       }

        @Override
        public int hashCode() {
            return Integer.valueOf(this.x).hashCode() + Integer.valueOf(this.y).hashCode();
        }

        public String toString(){
            return "{" + this.x + "," + this.y + "}";
        }
    }

    private static List<Index> generateIndexList(int x, int y){
        return Arrays.asList(
                new Index( x-1, y),
                new Index( x+1, y),
                new Index( x, y-1),
                new Index( x-1, y-1),
                new Index( x+1, y-1),
                new Index( x, y+1),
                new Index( x-1, y+1),
                new Index( x+1, y+1)
        );
    }

    private static Set<String> findWords( char[][] board, Set<String> words, Set<Index> visited, Index currIndex, String currWord) {
        if ( visited.contains(currIndex) ) {
            return words;
        }

        currWord += board[currIndex.x][currIndex.y];
        visited.add(currIndex);

        if ( DICTIONARY.contains(currWord) ){
            words.add(currWord);
        }
        
        for ( Index idx : generateIndexList(currIndex.x, currIndex.y) ) {
            if ( idx.x >= 0 && idx.y >= 0 && idx.x < board.length && idx.y < board[idx.x].length ) {
                findWords( board, words, visited, idx, currWord);
            }
        }
        visited.remove(currIndex);
        return words; 
    }

    public static Set<String> find( char[][] board) {
        Set<String> words = new HashSet<>();
        for ( int i = 0; i < board.length; ++i ) {
            for ( int j = 0; j < board[i].length; ++j ) {
                words.addAll(findWords( board, words, new HashSet<>(),  new Index(i,j), ""));
            }
        }
        return words;
    }

    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("MatrixWords");
        ClassLoader classLoader = cls.getClassLoader();
        String input = "MatrixWords.txt";
        URL url = classLoader.getResource(input);
        assert url != null;
        String[] lines = Files.lines(Paths.get(url.toURI())).filter(s -> !s.isEmpty()).toArray(String[]::new);
        char[][] board = new char[lines.length][];
        for ( int i = 0; i < lines.length; ++i ){
            board[i] = lines[i].replaceAll("\\s","").toLowerCase().toCharArray();
        }
        Set<String> words = find( board );
        System.out.println(words);
    }
}
