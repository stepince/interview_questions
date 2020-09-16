import java.util.*;

/*


Time:
   O(NlogK)

Space:
   O(N)
 */
public class TopKFrequentElements {


    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
//        Queue<Integer> queue = new PriorityQueue<Integer>((a, b) -> freqMap.get(a) - freqMap.get(b));
        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(freqMap::get));
        for (int num : nums) {
            freqMap.merge(num, 1, Integer::sum);
        }
        for (int num : freqMap.keySet()) {
            queue.add(num);
            if (queue.size() > k) queue.remove();
        }
        return queue.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args){
//        int[] arr = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//        int k = Integer.parseInt(args[1]);

        int[] arr = {1,1,1,1,2,2,3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(arr,k)));
    }
}