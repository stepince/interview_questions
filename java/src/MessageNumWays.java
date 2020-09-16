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
public class MessageNumWays {

    static int find(char[] chars, int idx){
        if ( idx == chars.length ) return 1;
        if ( chars[idx] == '0' ) return 0;
        if ( idx == chars.length - 1 ) return 1;

        if ( chars[idx] == '1' || ( chars[idx] == '2' && chars[idx+1] <= '6') ){
            return find(chars,idx+2) + find(chars,idx+1);
        }
        else {
            return find(chars, idx+1);
        }
    }

    static int find(String s){
        return find(s.toCharArray(),0);
    }

    static int find( int[] nums, int idx){
        if ( idx == nums.length ) return 1;
        if ( nums[idx] == 0 ) return 0;
        if ( idx == nums.length - 1 ) return 1;

        if ( nums[idx] == 1 || ( nums[idx] == 2 && nums[idx+1] <= 6 ) ){
            return find(nums,idx+2) + find(nums,idx+1);
        }
        else {
            return find(nums, idx+1);
        }
    }

    static int find(int num){
        LinkedList<Integer> l = new LinkedList<>();
        for ( int i = num; i!= 0;  i/=10 ){
            l.addFirst(i%10);
        }
        return find( l.stream().mapToInt(x->x).toArray() ,0);
    }

    public static void main(String[] args){
        String str = "100";
        int num = 100;
        System.out.println(find(str));
        System.out.println(find(num));
    }
}
