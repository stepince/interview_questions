

/*

Given n dice each with m faces, numbered from 1 to m, 
find the number of ways to get sum X. X is the summation of values 
on each face when all the dice are thrown.

java DiceThrow  6 3 8

O(f^(dices+amount))
where is the number of faces
/\
*/


public class DiceThrow {

    // the number of dices and throws are the same thing.
    public static int combinations( int faces, int dices, int value){
       int combos = 0;
       if ( value < 0 ) return 0;
       if ( dices == 0 && value == 0 ) return 1;
       if ( dices == 0 ) return 0;

       for ( int j = 1; j <= faces; ++j ) {
           int total = combinations( faces, dices-1, value-j);
           combos += total;
       }
       return combos;
    }

     public static void main( String[] args ) {
        int faces = Integer.parseInt(args[0]);
        int dices = Integer.parseInt(args[1]);
        int value = Integer.parseInt(args[2]);
        System.out.println(combinations( faces, dices, value));
     }



}