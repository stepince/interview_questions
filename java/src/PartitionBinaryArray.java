import java.util.Arrays;

/*
https://www.geeksforgeeks.org/divide-binary-array-into-three-equal-parts-with-same-value/

 */
public class PartitionBinaryArray {

    static boolean checkPartitions( int[] arry, int j, int k, int l, int ones){
        int count = 0;
        for ( ;count < ones && j < arry.length; ++j, ++k, ++l){
            if ( !(arry[j] == arry[k] && arry[k] == arry[l]) )break;
            if ( arry[j] == 1 ) ++count;
        }
        return (count == ones);
    }

    static int endZeros( int[] arry ){
        int counter = 0;
        for( int i = arry.length-1;  i >= 0 ;  --i ){
            if ( arry[i] == 0 ) ++counter;
            else break;
        }
        return counter;
    }


    public static int[] find(int[] arry){
        final int[] IMP = new int[]{-1,-1};
        if ( arry == null || arry.length == 0) return IMP;
        int sum = Arrays.stream(arry).sum();
        int j=0,k=0,l=0;
        // fail early
        if ( sum%3 != 0 ) return IMP;
        int ones = sum/3;
        if ( ones == 0 ) return new int[]{0,arry.length-1};
        int idx1=-1, idx2=-1;

        int count = 0;
        int zeros = endZeros(arry);

        for ( int i =0; i < arry.length; ++i ){
            if ( arry[i] == 0 ) continue;
            if ( ++count == 1 ) j = i;
            else if ( count == ones + 1 ) k = i;
            else if ( count == 2*ones + 1 ) l = i;

            if ( count == ones ) idx1 = i;
            else if ( count == 2*ones ) idx2 = i+zeros+1;
        }

        if ( !checkPartitions( arry,j,k,l,ones) ) return IMP;
        return new int[]{idx1,idx2};
    }

    public static void main(String[] args){
//        int[] arry = {1,1,1,1,1,1};
        int[] arry = {1,0,0,1,0,1};
        int[] result = find(arry);
        System.out.println( Arrays.toString(result));
    }
}
