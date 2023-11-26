import java.util.HashMap;
import java.util.Map;

/*

In the "100 game" two players take turns adding, to a running total, any integer from 1 to 10. The player who first causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers from 1 to 15 without replacement until they reach a total >= 100.

Given two integers maxChoosableInteger and desiredTotal, return true if the first player to move can force a win, otherwise return false. Assume both players play

      https://leetcode.com/problems/can-i-win/

Note: This solution is actually slower and more cryptic than CanIWin.
      It demonstrates how to use bit operations
 */

public class CanIWin2 {

    int size;
    int choices;
    public final boolean canIWinTwo(int desiredTotal, int key, Map<Integer,Boolean> mem) {
        if ( mem.containsKey(key) ) return mem.get(key);
        if ( desiredTotal <= 0 ) return mem.merge(key,true,(prev,curr)->true);

        int myChoices = this.choices & ~key;

        for (int i = 0; i < size; ++i) {
            int selected = (myChoices >> i) & 1;

            // if not selected then skip
            if ( selected == 0 ) continue;

            int choice = i + 1;

            // remove selected choice from choices (unset)
            myChoices &= ~(1 << i);

            // keep track of all the choices selected
            int newKey = (key | 1 << i);

            // any loss is a loser
            if ( !canIWinOne(desiredTotal - choice, newKey, mem) ) return false;

            // reset a choice
            myChoices |= 1 << i;
        }
        return mem.merge(key,true,(prev,curr)->true);
    }

    public final boolean canIWinOne(int desiredTotal, int key, Map<Integer,Boolean> mem) {
        if ( mem.containsKey(key) ) return mem.get(key);
        if ( desiredTotal <= 0 ) return mem.merge(key,false,(prev,curr)->false);

        int myChoices = this.choices & ~key;
        for (int i = 0; i < size; ++i) {
            int selected = (myChoices >> i) & 1;

            // if not selected then skip
            if ( selected == 0 ) continue;

            int choice = i + 1;

            // remove selected choice from choices (unset)
            myChoices &= ~(1 << i);

            // keep track of all the choices selected
            int newKey = (key | 1 << i);

            // any win is a winner
            if ( canIWinTwo(desiredTotal - choice, newKey, mem) ) return true;

            // reset a choice
            myChoices |= 1 << i;
        }
        return mem.merge(key,false,(prev,curr)->false);
    }

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if ( desiredTotal == 0 ) return true;
        this.size = maxChoosableInteger;

        for ( int i = 0; i < maxChoosableInteger; ++i ){
            this.choices |= 1 << i;
        }
        if ( maxChoosableInteger * ( 1 + maxChoosableInteger)/2 < desiredTotal ) return false;
        return canIWinOne(desiredTotal,0, new HashMap<>());
    }

    public static void main(String[] args){
        int maxChoosableInteger = 10;
        int desiredTotal = 40;
        System.out.println( new CanIWin2().canIWin(maxChoosableInteger, desiredTotal ) );
    }
}
