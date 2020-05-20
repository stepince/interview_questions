import java.util.HashMap;
import java.util.Map;

/*

Given n dice each with m faces, numbered from 1 to m, 
find the number of ways to get sum X. X is the summation of values 
on each face when all the dice are thrown.

java DiceThrow  6 3 8 
*/


public class DiceThrowDp {

    public static int combinations( int faces, int dices, int value){
        return combinations( faces, dices, value, new HashMap<>() );
    }

    private static int combinations( int faces, int dices, int value, Map<String,Integer> mem){
       int combos = 0;
       if ( value < 0 ) return 0;
       if ( dices == 0 && value == 0 ) return 1;
       if ( dices == 0 ) return 0;
           
       String key = dices + ":" + value;
       if ( mem.containsKey(key) ) return mem.get(key) ;

       for ( int j = 1; j <= faces; ++j ) {
           int total = combinations( faces, dices-1, value-j, mem);
           combos += total;
           key = (dices-1) + ":" + (value-j);
           mem.put( key, total );
       }
       return combos;
    }

     public static void main( String[] args ) {
        int faces = Integer.parseInt(args[0]);
        int dices = Integer.parseInt(args[1]);
        int value = Integer.parseInt(args[2]);
        System.out.println(combinations( faces, dices, value ));
     }
}