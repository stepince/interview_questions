import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MostCommonWord {
    final static Set<String> BANNED_WORDS = Set.of("TEST");

    static String find(String paragraph){

        Map<String,Integer> freq = new HashMap<>();
        //PriorityQueue<String> queue =  new PriorityQueue<>( (a,b)-> freq.get(b) - freq.get(a) );
        String maxWord = null;
        String[] words = paragraph.split("[\\s.,!?]");
        for ( String word: words ){
            word = word.trim();
            if ( word.length() == 0 || BANNED_WORDS.contains(word) ) continue;
//            freq.put( word, freq.getOrDefault(word,0)+1);
            freq.merge(word, 1, Integer::sum);
            if ( freq.get(word) > freq.getOrDefault(maxWord,0) ) {
                maxWord = word;
            }
        }
        return maxWord;
    }

    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("MostCommonWord");
        ClassLoader classLoader = cls.getClassLoader();
        URL url = classLoader.getResource("MostCommonWord.txt");
        assert url != null;
        List<String> lines = Files.readAllLines(Paths.get(url.toURI()));
        long t = System.currentTimeMillis();
        String paragraph = String.join(System.lineSeparator(),lines);
        System.out.println(find(paragraph));
        System.out.println("time: " + ( System.currentTimeMillis() - t));
    }
}
