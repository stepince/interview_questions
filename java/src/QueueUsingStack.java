import java.util.*;

public class QueueUsingStack<E> {

    final private Stack<E> stackA = new Stack<>();
    final private Stack<E> stackB = new Stack<>();

    // or push
    public void add( E e ) {
        stackA.push(e);
    }

    // or poll
    public E remove() {
        if ( stackB.isEmpty() ) {
            while( !stackA.isEmpty() ) {
                stackB.push(stackA.pop());
            }
        }
        return stackB.pop();
    }

    public boolean isEmpty(){
        return stackA.isEmpty() && stackB.isEmpty();
    }
    
    public static void main(String[] args){
        int[] arr = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        QueueUsingStack<Integer> queue = new QueueUsingStack<>();
        for ( int i : arr ){
            queue.add(i);
        }
        while ( !queue.isEmpty() ){
            System.out.println(queue.remove());
        }
    }

    /*
    push 1 2 3 4 5

    a)
    b)

    remove 3
    a)
    b) 1 2 3 4 5

     */
}


