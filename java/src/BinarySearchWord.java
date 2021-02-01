import java.util.Arrays;

public class BinarySearchWord {

    public static int binarySearch( String[] words, String word) {
        int lo = 0;
        int hi = words.length;
        while ( lo < hi ) {
            int mid = (lo + hi)/2;
            int compareTo = word.compareTo(words[mid]);
            if ( compareTo == 0 ) {
                return mid;
            }
            else if ( compareTo < 0 ) {
                hi = mid;
            }
            else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] word){
        String[] words = { "alright", "hello", "that", "this"};
        Arrays.sort(words);
        System.out.println(binarySearch(words,"that"));
    }
}
