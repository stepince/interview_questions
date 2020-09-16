public class SimpleAtoi {

    public static int atoi(String str){
        int num = 0;
        char[] arr = str.toCharArray();
        int place = 0;
        for ( int i = arr.length -1 ; i >= 0; --i ){
            num += Math.pow(10,place++)*( arr[i]-'0');
        }
        return num;
    }

    public static void main(String[] args){
        String numStr = "123";
        System.out.println(atoi(numStr));
    }
}
