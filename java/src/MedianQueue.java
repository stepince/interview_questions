
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MedianQueue extends LinkedList<Integer> {

    Queue<Integer> minQueue = new PriorityQueue<>();
    Queue<Integer> maxQueue = new PriorityQueue<>( (a,b)->b-a);

    public boolean add(Integer item){
        if ( minQueue.size() == 0 && maxQueue.size() == 0 ) {
            minQueue.add(item);
        }
        // it is important that large values go in the minQueue
        else if ( minQueue.size() != 0 && item > minQueue.peek() ) {
            minQueue.add(item);
        }
        else {
            maxQueue.add(item);
        }
        // adjust
        if( maxQueue.size() - minQueue.size() > 1) minQueue.add(maxQueue.remove());
        if( minQueue.size() - maxQueue.size() > 1) maxQueue.add(minQueue.remove());
        return super.add(item);
    }

    public Integer remove(){
        Integer item = super.remove();
        if (minQueue.contains(item)){
            minQueue.remove(item);
        }
        else {
            maxQueue.remove(item);
        }

        if( maxQueue.size() - minQueue.size() > 1) minQueue.add(maxQueue.remove());
        if( minQueue.size() - maxQueue.size() > 1 ) maxQueue.add(minQueue.remove());

        return item;
    }

    public Integer median(){
        if ( minQueue.size() == 0 && maxQueue.size() == 0 ) return null;
        if ( minQueue.size() > maxQueue.size() ) return minQueue.peek();
        if ( maxQueue.size() > minQueue.size() ) return maxQueue.peek();
        // == case
        return (maxQueue.peek() + minQueue.peek())/2;
    }

    public boolean isEmpty(){
        return minQueue.isEmpty() && maxQueue.isEmpty();
    }

    public int size(){
        return minQueue.size() + maxQueue.size();
    }

    public static void main(String[] args){
        MedianQueue queue = new MedianQueue();
        queue.add( 13);
        queue.add( 7);
        queue.add( 9);
        queue.add( 11);
        System.out.println(queue.median());
        queue.remove();
        System.out.println(queue.median());
        queue.remove();
        System.out.println(queue.median());
    }
}
