/*

Bloomberg interview implement a most recently used cache

 */


import java.util.*;

public class MRUCache {


    int capacity;
    int counter;

    Map<String,Integer> mruMap = new HashMap<>();
//    Queue<String> minQueue = new PriorityQueue<>( (a, b)->mruMap.get(a) - mruMap.get(b));
    // minQueue and maxQueue contains the same element, just in reverse order
    Queue<String> minQueue = new PriorityQueue<>(Comparator.comparingInt(a -> mruMap.get(a)));
    Queue<String> maxQueue = new PriorityQueue<>( (a, b)->mruMap.get(b) - mruMap.get(a));
    public MRUCache(int capactiy){
        this.capacity=capactiy;
    }

    String peek(){
        return maxQueue.peek();
    }

    List<String> values(){
        List<String> results = new ArrayList<>(maxQueue);
        results.sort((a, b) -> mruMap.get(b) - mruMap.get(a));
        return results;
    }

    void add(String str){
        minQueue.remove(str);
        maxQueue.remove(str);
        mruMap.put(str,counter++);
        minQueue.add(str);
        maxQueue.add(str);
        if ( minQueue.size() > capacity ){
            String evict = minQueue.poll();
            maxQueue.remove(evict);
            mruMap.remove(evict);
        }
    }

    public static void main(String[] args){
        int size = 5;
        MRUCache mruCache = new MRUCache(size);
        mruCache.add("bloomberg");
        mruCache.add("google");
        mruCache.add("facebook");
        System.out.println(mruCache.values());
        System.out.println(mruCache.peek());
    }
}
