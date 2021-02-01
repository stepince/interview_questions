public class SimpleAtoi {

    public static int atoi(String str){
        int val = 0;
        for ( char ch: str.toCharArray() ) {
            val =  val * 10 + ch -'0';
        }
        return val;
    }

    public static void main(String[] args){
        String numStr = "531";
        System.out.println(atoi(numStr));
    }
}
