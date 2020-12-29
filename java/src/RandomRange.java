import java.util.Random;
/*
  Google youtube question

 */
public class RandomRange {
    public static int findRandom( int[][] ranges ){
        Random rand = new Random();
        int total = 0;
        for ( int[] range: ranges ) {
            total += (range[1]-range[0]+1);
        }
        int offset = rand.nextInt(total);
        for ( int[] range: ranges ) {
            int currRange = (range[1]-range[0]+1);
            if ( offset < currRange ) return range[0] + offset;
            offset -= currRange;
        }
        return 0;
    }

    public static void main(String[] args){
        int[][] ranges = new int[3][2];
        ranges[0] = new int[]{ 1,2 };
        ranges[1] = new int[]{ 34,36 };
        ranges[2] = new int[]{ 39,50 };
        System.out.println(findRandom(ranges));
    }

}
