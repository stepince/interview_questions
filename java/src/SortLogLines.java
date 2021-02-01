import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
/*
 *   Sort log lines
 *   logs are in the format
 *   id<space>(alphaNumber|digit)+
 *   rule:
 *      sort alphaNumeric first and if ties sort by id
 *      digits do not sort
 *
 *  e.g.     1d1 abc efg
 *           id2 efg
 *           id3 567
 *           id4 123
 */
public class SortLogLines {

    static class MyComparator implements Comparator<String> {

        boolean isAlphaNumeric(String[] words){
            for ( String word: words) {
                Integer val = null;
                try {
                    val = Integer.parseInt(word);
                }
                catch (Exception ignore) {
                    //ignore
                }
                if ( val != null ) return false;
            }
            return true;
        }

        int compareWords(String[] wordsA, String[] wordsB){
            for ( int i = 0, j = 0; i < wordsA.length && j < wordsB.length; ++i, ++j ){
                if ( wordsA[i].equals(wordsB[j])) continue;
                return wordsA[i].compareTo(wordsB[j]);
            }
            return wordsA.length - wordsB.length;
        }

        @Override
        public int compare(String a, String b) {
            int indexA = a.indexOf(" ");
            String idA = a.substring(0,indexA);
            String lineA = a.substring(indexA).trim();
            String[] wordsA =  lineA.split("\\s+");
            boolean isAlphaNumericA = isAlphaNumeric(wordsA);

            int indexB = b.indexOf(" ");
            String idB = b.substring(0,indexB);
            String lineB = b.substring(indexB).trim();
            String[] wordsB =  lineB.split("\\s+");
            boolean isAlphaNumericB = isAlphaNumeric(wordsB);

            if ( isAlphaNumericA && isAlphaNumericB ){
                return lineA.equals(lineB) ? idA.compareTo(idB) : compareWords(wordsA,wordsB);
            }
            else if ( isAlphaNumericA ){
                return -1;
            }
            else {
                return isAlphaNumericB ? 1: 0;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        String input = "SortLogLines.txt";
        URL url = ClassLoader.getSystemResource(input);
        List<String> sortedLines = Files.lines(Paths.get(url.toURI())).filter(s -> !s.isEmpty()).sorted(new MyComparator()).collect(Collectors.toList());
        for (String sortedLine : sortedLines) {
            System.out.println(sortedLine);
        }
    }
}
