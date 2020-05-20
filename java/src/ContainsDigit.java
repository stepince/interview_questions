/*
    print the numbers of a given range that contain a digit

    input:
        begin:2 , end: 88, digit: 3

    output:
        3 13 23 30 31 32 33 34 35 36 37 38 39 43 53 63 73 83
 */
public class ContainsDigit {

    private static boolean containsDigit( int digit, int num ){
        // handle 0 case
        if ( num == digit ) return true;
        for ( int i = num; i > 0; i/=10 ) {
            if ( i % 10 == digit ) return true;
        }
        return false;
    }

    public static String find(int digit, int begin, int end){
        StringBuilder sb = new StringBuilder();
        String space = "";
        for ( int i = begin; i <= end; ++i, space = " " ){
            if ( containsDigit(digit,i) ) {
                sb.append(space).append(i);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
        int begin = Integer.parseInt(args[0]);
        int end = Integer.parseInt(args[1]);
        int digit = Integer.parseInt(args[2]);
        System.out.println(find(digit,begin,end));
    }
}