//import java.util.*;

/*

Note: This is really a hard problem, to solve under n^2. I would not study this problem.
find the number of sub-arrays of having negative sums.
input: 1 -2 4 -5 1

output: 9
[1, -2, 4, -5, 1]
[-2, 4, -5, 1]
[-5, 1]
[1, -2, 4, -5]
[-2, 4, -5]
[4, -5]
[-5]
[1, -2]
[-2]
9


 */
public class SubArrayNegativeCount {


    public static int merge(int[] arr, int left1, int right1, int left2, int right2) {
        int[] temp = new int[right2 - left1 + 1];
        int index1 = left1;
        int index2 = left2;
        int temp_index = 0;
        int inversion_count = 0;
        while(index1 <= right1 && index2 <= right2) {
            if(arr[index1] <= arr[index2]) {
                temp[temp_index] = arr[index1];
                index1++;
                temp_index++;
            }
            else {
                //In this case we add to our inversion count:
                inversion_count += right1 - index1 + 1;
                temp[temp_index] = arr[index2];
                index2++;
                temp_index++;
            }
        }

        while(index1 <= right1) {
            temp[temp_index] = arr[index1];
            index1++;
            temp_index++;
        }

        while(index2 <= right2) {
            temp[temp_index] = arr[index2];
            index2++;
            temp_index++;
        }

        if (temp.length >= 0) System.arraycopy(temp, 0, arr, left1, temp.length);

        return inversion_count;
    }

    public static int findNegativeSubarrays(int[] arr, int left, int right) {
        if(right < left) {
            return 0;
        }
        if(right == left) {
            if(arr[left] < 0) {
                return 1;
            }
            else{
                return 0;
            }
        }

        int mid = (left + right)/2;
        int num1 = findNegativeSubarrays(arr, left, mid);
        int num2 = findNegativeSubarrays(arr, mid+1, right);
        int num3 = merge(arr, left, mid, mid+1, right);

        return num1 + num2 + num3;
    }

    public static int find(int[] arr){
        int[] accumulator = new int[arr.length];
        accumulator[0] = arr[0];
        for(int i = 1; i < arr.length; i++) {
            accumulator[i] = accumulator[i-1] + arr[i];
        }

        return findNegativeSubarrays(accumulator, 0, arr.length-1);
    }

    public static void main(String[] args){
//       int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int[] arr = {1, -2, 4, -5, 1};

        System.out.println(find(arr));

    }
}