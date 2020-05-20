public class ReverseString {
    
    public static String reverseString(String str){
        if ( str == null || str.length() == 0 ) return str;
        char[] arr = str.toCharArray();
        for ( int i = 0; i < arr.length/2; ++i){
            arr[i] = str.charAt(arr.length-i-1);
            arr[arr.length-i-1] = str.charAt(i);
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        //String str = args[0];
        String str = "hello";
        System.out.println(reverseString(str));
    }
}