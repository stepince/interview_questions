import java.util.HashMap;
import java.util.Map;

/*

Given n dice each with m faces, numbered from 1 to m, 
find the number of ways to get sum X. X is the summation of values 
on each face when all the dice are thrown.

java DiceThrow  6 3 8

O(f*dices*amount)
where f is the number of faces
*/


public class DiceThrowDp {

    public static int combinations( int faces, int dices, int value){
        return combinations( faces, dices, value, new HashMap<>() );
    }

    private static int combinations( int faces, int dices, int sum, Map<String,Integer> mem){
       int combos = 0;
       if ( sum < 0 ) return 0;
       if ( dices == 0 && sum == 0 ) return 1;
       if ( dices == 0 ) return 0;

       // only changing values are sum and dices
       String key = dices + ":" + sum;
       if ( mem.containsKey(key) ) return mem.get(key) ;

       for ( int i = 1; i <= faces; ++i ) {
           if ( i > sum ) break;
           combos += combinations( faces, dices-1, sum-i, mem);
       }
       return mem.merge( key, combos, (prev,curr)->curr );
    }

     public static void main( String[] args ) {
//        int faces = Integer.parseInt(args[0]);
//        int dices = Integer.parseInt(args[1]);
//        int value = Integer.parseInt(args[2]);
         int faces = 6;
         int dices = 2;
         int value = 7;
        System.out.println(combinations( faces, dices, value ));
     }
}