import java.util.Arrays;
import java.util.Collection;
import java.util.Stack;
import java.util.stream.Collectors;

/*
 Design a Data Structure SpecialStack that supports all the stack operations like
 push(), pop(), isEmpty(), isFull() and an additional operation getMin() which
 should return minimum element from the SpecialStack. All these operations of
 SpecialStack must be O(1). To implement SpecialStack, you should only use standard
 Stack data structure and no other data structure like arrays, list, .. etc.


Time:
    O(1)

Space:
    O(N);
 */
public class StackMin extends Stack<Integer> {

    private Stack<Integer> min = new Stack<>();

    public Integer pop(){
        if ( min.size() == 0 ) return null;
        min.pop();
        return super.pop();
    }

    public Integer push(Integer item){
        if ( min.isEmpty() ){
            min.push(item);
        }
        else {
            // this is keeping track of state
            min.push( Math.min(min.peek(),item) );
        }
        super.push(item);
        return item;
    }

    public boolean addAll(Collection<? extends Integer> coll){
        for( Integer item: coll){
            this.push(item);
        }
        return true;
    }

    public Integer min(){
        if ( this.min.size() == 0 ) return null;
        return this.min.peek();
    }

    public static void main(String[] args){
        int[] arr = {6,3,1,7,0,9};
        StackMin st = new StackMin();
        st.addAll(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        while( !st.empty() ){
            System.out.printf("%d, %d\n",st.min(),st.pop());
        }
    }
}
