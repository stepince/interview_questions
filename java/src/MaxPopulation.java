import java.util.HashMap;
import java.util.Map;

/*
 * Given a list of people with their birth year andh death years,
 * Find the year with the highest population
 *
 */
public class MaxPopulation {


    static int find(int[] birth, int[] death){
        Map<Integer,Integer> popMap = new HashMap<>();
        int pop = 0;
        int highestYear = 0;
        for ( int year: death){
//            popMap.merge(year+1,-1,(prev,curr)->prev-1);
            popMap.merge(year+1,-1,Integer::sum);
        }
        for ( int year: birth){
//            int yearPop = popMap.merge(year,1,(prev,curr)->prev+1);
            int yearPop = popMap.merge(year,1,Integer::sum);
            if ( yearPop > pop ) highestYear = year;
        }
        return highestYear;
    }

    public static void main(String[] args){
        int[] birth = { 1894, 1803, 1840, 1750, 1803, 1975, 1975, 2000 };
        int[] death = { 2010, 2005, 2003, 1809, 1869, 1935, 1921, 1921 };
        System.out.println(find(birth,death));
    }
}
