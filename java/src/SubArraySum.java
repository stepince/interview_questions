import java.util.List;

/*

 practice problem for sub-arrays

  1,            1
  1 , 3         (1) ; (1) , (1,3)  ,  (3)
  1 , 3, 4      (1) ; (1) , (1,3)  ,  (3) ;   (1,3,4) ,  (3,4),  (4)

      1

 */
public class SubArraySum {

    public static int sum1(List<Integer> arr){
        int len = arr.size();
        int sum = 0;

        //     sum(i-1)
        for ( int i = 0; i < len ; ++i ) {
            for ( int j = i; j < len; ++j ) {
                sum += arr.subList(i,j+1).stream().reduce(0,Integer::sum);
            }
        }
        return sum;
    }

    /*
    1
    1 3
      3
    1 3 4
      3 4
        4
    1 3 4 5
      3 4 5
        4 5
          5
     */
    public static int sum2(List<Integer> arr) {
        int sum = 0;
        for ( int i = 0, incr = 0; i < arr.size(); ++i ) {
            sum += incr += arr.get(i) * (i+1);
        }
        return sum;
    }

    public static void main(String[] args){
        // string to char
//        List<Character> arr = Stream.of(args[0].split("\\s*,\\s*")).map(x->x.charAt(0)).collect(Collectors.toList());
        List<Integer> arr = List.of( 1, 3, 1, 4 );
        System.out.println(sum1(arr));
        System.out.println(sum2(arr));
    }
}
