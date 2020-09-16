import java.util.LinkedList;

/*
   https://leetcode.com/problems/decode-ways/

   number of ways to decode message
   a = "1"
   b = "2"
   c = "3"

   l = "12"
   z 26

   find("12")
   ab
   l

   total = 2

   note: 0 is an illegal character
 */
public class MessageNumWaysDp {

    static int find(char[] chars, int idx, Integer[] dp){
        if ( idx == chars.length ) return 1;
        if ( chars[idx] == '0' ) return 0;
        if ( dp[idx] != null ) return dp[idx];
        int result = find(chars, idx+1, dp);
        // second path 10-19, 20-26
        boolean secondPath = ( (idx + 1) < chars.length ) && ( chars[idx] == '1' || ( chars[idx] == '2' && chars[idx+1] <= '6'));
        return dp[idx] = secondPath ? ( result + find(chars,idx+2,dp)  ) : result;
    }

    static int find(String s){
        char[] chars = s.toCharArray();
        Integer[] dp = new Integer[chars.length];
        return find(chars,0, dp);
    }

    static int find(int[] nums, int idx, Integer[] dp){
        if ( idx == nums.length ) return 1;
        if ( nums[idx] == 0 ) return 0;
        if ( idx == nums.length - 1 ) return 1;

        if ( dp[idx] != null ) return dp[idx];
        if ( nums[idx] == 1 || ( nums[idx] == 2 && nums[idx+1] <= 6 )  ){
            return dp[idx] = find(nums,idx+2,dp) + find(nums,idx+1,dp);
        }
        else {
            return dp[idx] = find(nums, idx+1,dp);
        }
    }

    static int find(int num){
        LinkedList<Integer> l = new LinkedList<>();
        for ( int i = num; i!= 0;  i/=10  ){
            l.addFirst(i%10);
        }
        int[] nums = l.stream().mapToInt(x->x).toArray();
        Integer[] dp = new Integer[nums.length];
        return find( nums ,0,dp);
    }

    public static void main(String[] args){
        String str = "12";
        int num = 12;
        System.out.println(find(str));
        System.out.println(find(num));
    }
}
