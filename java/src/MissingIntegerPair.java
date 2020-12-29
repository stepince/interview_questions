import java.util.Arrays;
/*
    Find the missing integer pair

 */
public class MissingIntegerPair {

    public static int find(int[] nums ){
        int val = 0;
        for ( int num: nums ){
            val ^= num;
        }
        return val;
    }

    public static void main(String[] args ){
        int[] nums = {4,4,2,2,5};
        System.out.println(find(nums));
    }

}
