import java.util.Arrays;

public class MissingInteger {


    public static int find(int[] nums1, int[] nums2  ){
        long sum = 0;
        int i = 0, j = 0;
        while ( i < nums1.length && j < nums2.length ){
            // preventing overflow
            if ( sum < 0 ){
                sum += nums1[i++];
            }
            else {
                sum -= nums2[j++];
            }
        }
        while ( i < nums1.length ){
            sum += nums1[i++];
        }
        while ( j < nums2.length ){
            sum -= nums2[j++];
        }
        return (sum == 0) ? -1 : (int)sum;
    }

    public static void main(String[] args ){

        int[] nums1= {4,8,12,9,3};
        int[] nums2= {4,8,9,3};
        int val = Arrays.stream(nums1).sum();
        System.out.println(find(nums1,nums2));
    }

}
