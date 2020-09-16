/*

https://leetcode.com/problems/task-scheduler/
 */

import java.util.*;

public class TaskScheduler {

    // brute force can be used with memoization
    static int find2( List<Character> tasks, int n, Map<Character,Integer> lastMap , int total ){
        if ( tasks.size() == 0 ) return total;
        int minTime = Integer.MAX_VALUE;
        for ( int i = 0; i < tasks.size(); ++i ) {
            Character task = tasks.remove(i);

            int newTotal = total + 1;
            if ( lastMap.containsKey(task) ) newTotal = Math.max(newTotal, lastMap.get(task) + n + 1);
            Integer last = lastMap.put(task,total + 1);

            int time = find2(tasks, n, lastMap, newTotal);
            if ( last == null ) lastMap.remove(task);
            else lastMap.put(task,last);

            tasks.add(i,task);
            minTime = Math.min(minTime,time);
        }
        return minTime;
    }

    static int find1( char[] tasks, int n){
        // you can also use an array
        Map<Character,Integer> freqMap = new HashMap<>(tasks.length);
        int maxFreq = 0;
        int maxFreqCount = 0;
        for ( Character task: tasks) {
            int freq = freqMap.merge(task,1,Integer::sum);
            if ( freq > maxFreq ){
                maxFreq = freq;
                maxFreqCount = 1;
            }
            else if ( freq == maxFreq ) {
                ++maxFreqCount;
            }
        }
        int slots = maxFreq*(n+1);
        // calculate slots usage
        // case: tasks greater than slots
        //       return tasks;
        // case: tasks less than slots
        //    example
        //    n=2
        //    AB AB AB
        //    slots = 9
        //    we don't need all 9
        //    calculate freq-1 and add maxFreqCount == ((maxFreq-1)*(n+1)) + maxFreqCount
        //    this calculate can not be lower that the number of tasks. Math.max(total,tasks.length)
        //
        //    example
        //    n=3
        //    ABCDABV
        //    slots = 8
        //    we don't need all 8
        //    ((maxFreq-1)*(n+1)) + maxFreqCount == 6  --> this is less then task
        //    Math.max(tasks.length,  ((maxFreq-1)*(n+1)) + maxFreqCount );
        int total = ( tasks.length >= slots ) ? tasks.length : ((maxFreq-1)*(n+1)) + maxFreqCount;
        // calculate slots
        return Math.max(total,tasks.length);
    }

    static int find2( char[] tasks, int n){
        List<Character> l = new ArrayList<>();
        for ( Character ch: tasks) l.add(ch);
        return find2( l,  n, new HashMap<>(), 0 );
    }

    public static void main(String[] args){
//        char[] tasks = {'A','B','A','B','A','B'};
//        int n = 2;
        char[] tasks = {'A','B','C','D','A','B','V'};
        int n = 3;

//        char[] tasks  = {'A','A'};
        System.out.println(find2(tasks,n));
        System.out.println(find1(tasks,n));
    }
}
