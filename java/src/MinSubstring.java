import java.util.Set;

/*

Input: str = “111112”,  array = "11", "111", "11111", "2"
Output: 2

input: str = “123456”, arr[] = {“1”, “12345”, “2345”, “56”, “23”, “456”}
output: 3

O(n^2)
Dynamic could help but it looks it would still be o(N^2)
 */

public class MinSubstring  {

    private static int counter = 0;
    public static int find(String str, Set<String> substrings, int idx, int count){

        if ( idx == str.length() ) {
            return Integer.MAX_VALUE;
        }
        if ( substrings.contains(str) ){
            return count + 1;
        }
        ++counter;
        String substr = str.substring(0,idx);
        if ( !substrings.contains(substr) ) {
            return find(str, substrings, idx+1, count);
        }

        String suffix = str.substring(idx);
        int include = find(suffix, substrings, 0, count+1);
        int exclude = find(str, substrings, idx+1, count );
        return Math.min(include,exclude);
    }

    public static int find(String str, Set<String> substrings){
        int val = find(str, substrings, 0, 0 );
        return val == Integer.MAX_VALUE ? 0 : val;
    }

    public static void main(String[] args){
        String str = args[0];
        String[] substrings = args[1].split("[\\s,]+");
        //System.out.println(find(str, new HashSet<String>( Arrays.asList(substrings) )));
        System.out.println(find(str, Set.of(substrings)));
        System.out.println(counter);
    }
}