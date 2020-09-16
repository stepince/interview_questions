public class NumberToBinary {

    public static String numberToBinary(int num){
        StringBuilder sb = new StringBuilder();
        while ( num > 0 ){
            sb.insert(0, num % 2);
            num /= 2;
        }
        return sb.toString();
    }

    public static void main(String[] args){
        int num = 64;
        System.out.println(numberToBinary(num));
    }
}
