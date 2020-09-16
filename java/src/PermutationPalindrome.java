public class PermutationPalindrome {

    static void swap(char[] arr, int i , int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] =  temp;
    }

    static boolean isPalindrome(char[] arr){
        for( int i = 0, j = arr.length-1; i < j; ++i, --j ){
            if ( arr[i] != arr[j] ) return false;
        }
        return true;
    }

    static boolean find(char[] arr, int idx){
        if ( idx == arr.length ) return isPalindrome(arr);

        for ( int i = idx; i < arr.length; ++i ){
            swap(arr,idx,i);
            if ( find(arr, idx+1) ) return true;
            swap(arr,idx,i);
        }
        return false;
    }

    static boolean find(String str){
        return find(str.toCharArray(),0);
    }

    public static void main(String[] args){
        String str = "aabbaac";
        System.out.println(find(str));
    }
}
