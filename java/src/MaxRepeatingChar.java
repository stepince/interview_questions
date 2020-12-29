/*




 */

public class MaxRepeatingChar {

    public static Character find( String str ){
        if ( str == null || str.length() == 0 ) return null;
        int max = 1;
        char[] chars = str.toCharArray();
        char ch = chars[0];
        int count = 1;
        for ( int i = 1; i < chars.length;++i ){
            if ( chars[i-1] != chars[i] ){
                count = 1;
            }
            else if ( ++count > max){
                ch = chars[i];
                max = count;
            }
        }
        return ch;
    }
    public static void main(String[] args){
        String str  = "geeekkkkkkkkeeeeeeeeeffffffffff";
        System.out.println(find(str));
    }
}
