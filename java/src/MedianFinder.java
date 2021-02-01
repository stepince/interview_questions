import java.util.PriorityQueue;
import java.util.Queue;
/*

   Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
   So the median is the mean of the two middle value.

   For example,
   [2,3,4], the median is 3

   [2,3], the median is (2 + 3) / 2 = 2.5
 */
public class MedianFinder {
    Queue<Integer> largeQ = new PriorityQueue<>();
    Queue<Integer> smallQ = new PriorityQueue<>( (a,b)->(b-a));

    /** initialize your data structure here. */
    public MedianFinder() {

    }

    public void addNum(int num) {
        if ( smallQ.size() > largeQ.size() ){
            largeQ.add(num);
        }
        else {
            smallQ.add(num);
        }
        if ( smallQ.size() > 0 && largeQ.size() > 0  && smallQ.peek() > largeQ.peek() ) {
            smallQ.add(largeQ.remove());
            largeQ.add(smallQ.remove());
        }
    }

    public double findMedian() {
        if ( smallQ.size() == 0 && largeQ.size() == 0 ) {
            return 0;
        }
        else if ( smallQ.size() > largeQ.size() ){
            return smallQ.peek();
        }
        else if ( largeQ.size() > smallQ.size() ){
            return largeQ.peek();
        }
        else {
            return (double)(smallQ.peek() + largeQ.peek() )/2;
        }
    }
}
