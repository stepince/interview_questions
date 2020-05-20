

/*

Runtime
O(N^2)

  (n/n+1)/2
 */
public class PrintSubstrings {

    private static void print(String str, int begin, int end){
        if (begin == str.length()) return;
        if ( begin == end) {
            print(str, 0, end+1);
        }
        else {
            System.out.println(str.substring(begin,end));
            print(str, begin+1, end);
        }
    }

    public static void print(String str){
        print(str, 0, 1);
    }

    public static void print2(String str){
        for ( int i = 0; i < str.length(); ++i ){
            int end = str.length() - i;
            System.out.println(str.substring(0,end));
            for ( int j = 1; j < end; ++j ) {
                System.out.println(str.substring(j, end));
            }
        }
    }

    static public void main(String[] args){
        String str = args[0];
        print(str);
        System.out.println("-------------------");
        print2(str);
    }
}