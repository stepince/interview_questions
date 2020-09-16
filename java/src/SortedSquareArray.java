import java.util.Arrays;

public class SortedSquareArray {

    public static int[] find(int[] arr){
        int[] squareArr = new int[arr.length];
        int left = 0;
        int right = arr.length-1;
        for( int i = arr.length-1; 0 <= i ; --i ){
            if ( Math.abs(arr[left]) < Math.abs(arr[right]) ) {
                squareArr[i] = arr[right] * arr[right--];
            }
            else {
                squareArr[i] = arr[left] * arr[left++];
            }
        }
        return squareArr;
    }

    public static void main(String[] args){
        int[] arr = {-1, 0,4};
        System.out.println(Arrays.toString(find(arr)));
    }
}
