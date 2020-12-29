import java.util.*;
/*
   https://leetcode.com/problems/insert-delete-getrandom-o1

 */
class RandomizedSet {



    /** Initialize your data structure here. */
    Map<Integer,Integer> map = new HashMap<>();
    List<Integer> list = new ArrayList<>();
    Random rand = new Random();

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean result = !map.containsKey(val);
        if ( result ){
            list.add(val);
            map.put(val,list.size()-1);
        }
        return result;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean result = map.containsKey(val);
        if ( result ){
            int idx = map.get(val);
            int lastVal = list.get(list.size()-1);
            map.put(lastVal,idx);
            map.remove(val);
            list.set(idx,lastVal);
            list.remove(list.size()-1);
        }
        return result;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
