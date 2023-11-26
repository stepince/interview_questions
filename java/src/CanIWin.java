import java.util.*;

/*

In the "100 game" two players take turns adding, to a running total, any integer from 1 to 10. The player who first causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers from 1 to 15 without replacement until they reach a total >= 100.

Given two integers maxChoosableInteger and desiredTotal, return true if the first player to move can force a win, otherwise return false. Assume both players play

      https://leetcode.com/problems/can-i-win/

 */

public class CanIWin {

    public final boolean canIWinTwo(Deque<Integer> choices, int desiredTotal, int key, Map<Integer,Boolean> mem) {
        if ( mem.containsKey(key) ) return mem.get(key);
        // Test prior play one results
        if ( desiredTotal <= 0 ) return mem.merge(key,true,(prev,curr)->true);

        boolean result = true;
        for ( int i = 0; i < choices.size() ; ++i ) {
            int choice = choices.remove();
            int newKey = (key | 1 << (choice-1));
            // any loss is a loser, stay in loop to return choices to original state
            // minimax pruning
            result = result && canIWinOne(choices, desiredTotal - choice, newKey, mem);
            choices.add(choice);
        }

        // if any loss from player 1, return false (fail)
        return mem.merge(key,result,(prev,curr)->curr);
    }

    public final boolean canIWinOne(Deque<Integer> choices, int desiredTotal, int key, Map<Integer,Boolean> mem) {
        if ( mem.containsKey(key) ) return mem.get(key);

        // Test prior play two results
        if ( desiredTotal <= 0 ) return mem.merge(key,false,(prev,curr)->false);
        boolean result = true;
        for ( int i = 0; i < choices.size() ; ++i ) {
            int choice = choices.remove();
            int newKey = (key | 1 << (choice-1));
            // any win is a winner, stay in loop to return choices to original state
            // minimax pruning
            result = result && !canIWinTwo(choices, desiredTotal - choice, newKey, mem);
            choices.add(choice);
        }
        // if any win from player 2, return false (fail)
        return mem.merge(key,!result,(prev,curr)->curr);
    }

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        Deque<Integer> choices = new ArrayDeque<>();
        if ( desiredTotal == 0 ) return true;
        for ( int i = 1; i <= maxChoosableInteger; ++i ){
            choices.add(i);
        }
        if ( maxChoosableInteger * ( 1 + maxChoosableInteger)/2 < desiredTotal  ) return false;
        return canIWinOne(choices,desiredTotal,0, new HashMap<>());
    }

    public static void main(String[] args){
        int maxChoosableInteger = 10;
        int desiredTotal = 40;
        System.out.println( new CanIWin().canIWin(maxChoosableInteger, desiredTotal ) );
    }
}
