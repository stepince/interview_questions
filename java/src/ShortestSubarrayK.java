import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
/*


https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/

windowing problem
 */
public class ShortestSubarrayK {


    static int find(int[] A, int K) {
        int counter = Integer.MAX_VALUE;
        Deque<Integer> dq = new LinkedList<>();
        for ( int i = 0; i < A.length; ++i ){

            if ( i > 0 ) A[i] += A[i-1];
            if ( A[i] < 0 ) continue;
            if ( A[i] >=  K ) counter = Math.min(counter, i+1);

            // check and remove all front elements in the new prefix sum give the deq elements a sum >= k
            while (!dq.isEmpty() && A[i] - A[dq.peek()] >= K) {
                counter = Math.min(counter, i - dq.peek());
                dq.remove();
            }

            // make sure we are in ascending order
            while (!dq.isEmpty() && A[i] <= A[dq.peekLast()]) dq.removeLast();
            dq.add(i);
        }

        return counter == Integer.MAX_VALUE ? -1 : counter;
    }

    public static void main(String[] args) throws Exception {

//        int[] arry = {-484,743,-42,110,-294,-324,610,25,-19,638,382,444,-57,-29,19,780,914,641,558,-354,-410,773,-175,-322,564,-74,699,-412,-313,316,-2,287,-440,298,732,751,758,743,-457,129,339,152,651,695,461,-13,112,469,788,-320,143,-50,816,293,-305,553,-461,370,-318,609,494,134,235,891,825,157,149,715,-442,623,24,428,-329,-353,312,-178,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-353,312,-178,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-353,312,-178,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-353,312,-178,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-353,312,-178,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-353,312,-178,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-353,312,-178,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-353,312,-178,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-353,312,-178,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-353,312,-178,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46, 537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-164,570,470,-28,-55,716,46,552,387,537,-459,-154,-164,570,470,-28,-55,716,46,-80,767,-367,246,114,638,552,387,537,-459,-154,-164,570,470,-28,-55,716,46 };
//        int K = 789999;
//        int[] arry = {2,-1,2};
//        int K = 3;

//        int[] arry = {-28,81,-20,28,-29};
//        int K = 89;

        int[] arry = {-2,4,-1,6,11,15,2,12,9,3,};
        int K = 10;

        URL url = ClassLoader.getSystemResource("ShortestSubarrayK.txt");
        List<String> lines = Files.readAllLines(Paths.get(url.toURI()));
        String[] values = lines.get(0).split(",");

//        int[] arry = Arrays.stream(values).mapToInt(Integer::parseInt).toArray();
//        int K = Integer.parseInt(lines.get(1));

        long t = System.currentTimeMillis();
        System.out.println( find(arry,K));
        System.out.println("time: " + (System.currentTimeMillis() - t));
    }
}
