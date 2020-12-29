public class SimpleAtoi {

    public static int atoi(String str){
        int val = 0;
        char[] chars =  str.toCharArray();
        for ( int i = chars.length-1, pow = 0; i>= 0; --i, ++pow ) {
            int digit = chars[i] - '0';
            val += digit * Math.pow(10,pow);
        }
        return val;
    }

    public static void main(String[] args){
        String numStr = "123";
        System.out.println(atoi(numStr));
    }
}
