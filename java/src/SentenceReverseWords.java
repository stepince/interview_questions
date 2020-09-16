import java.util.Set;

public class SentenceReverseWords {

    static final Set<Character> SEP = Set.of(' ');
    static void reverseWords(char[] arr, int start, int end){
        while ( start < end ){
            char tmp = arr[start];
            arr[start]=arr[end];
            arr[end]=tmp;
            ++start;
            --end;
        }
    }

    public static char[] reverseWords(char[] arr){

        for ( int i = 0; i < arr.length; ){
            if ( SEP.contains(arr[i]) ){
                ++i;
                continue;
            }
            int start = i;
            int end = i;
            while ( i < arr.length && !SEP.contains(arr[i]) ) end = i++;
            reverseWords(arr,start,end);
        }
        return arr;
    }

    public static void main(String[] args){
        String str = "hello world";
        char[] words = str.toCharArray();
        System.out.println(new String(reverseWords(words)));
    }

}
