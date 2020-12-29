import java.util.ArrayList;
import java.util.List;
/*
  find all the math express that will equal a sum
 */
public class SumTo {

    public static List<String> find(char[] chars, int sum, int idx, String curr, List<String> results ){
        if ( idx == chars.length ){
            if ( sum == 0 ) results.add(curr);
            return results;
        }
        String str = "";
        for ( int i = idx; i < chars.length; ++i ){
            str += chars[i];
            int strInt = Integer.parseInt(str);
            find(chars, sum+strInt, i+1, curr+"-"+strInt, results);
            find(chars, sum-strInt, i+1, curr+"+"+strInt, results);
        }
        return results;
    }

    public static List<String> find(char[] chars, int sum){
        return find(chars, sum, 0, "", new ArrayList<>() );
    }

    public static void main(String[] args){
        long t = System.currentTimeMillis();
        String str = "123456789";
        int sum = 100;
        System.out.println(find(str.toCharArray(),sum));
        System.out.println("time: " + ( System.currentTimeMillis() - t));
    }
}
