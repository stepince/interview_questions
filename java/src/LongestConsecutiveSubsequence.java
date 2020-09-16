public class LongestConsecutiveSubsequence {

    static Integer max(Integer a, Integer b){
        if ( a == null ) return b;
        if ( b == null ) return a;
        return Math.max(a,b);
    }

    public static int find(int[] arr, int idx, Integer curr){
        if ( idx == arr.length ) return 0;
        // exclude
        Integer val = find(arr,idx+1,curr);

        if ( curr == null || arr[idx] == curr + 1 ){
            val = max( val , 1 + find(arr,idx+1,arr[idx] ));
        }
        return val;
    }

    public static int find(int[] arr) {
        return find(arr,0,null);
    }

    public static void main(String[] args){
        int[] arr = {1,2,5,4,8,9,10};
//        int[] arr = {};
        System.out.println(find(arr));
    }
}
