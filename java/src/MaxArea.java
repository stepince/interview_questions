/*

https://leetcode.com/problems/container-with-most-water/
 */
public class MaxArea {

    static int find(int[] heights){
        int maxArea = 0;
        int i = 0;
        int j = heights.length-1;
        while ( i < j ){
            int height = Math.min(heights[i],heights[j]);
            int width = j - i;
            int area = height * width;
            maxArea = Math.max( maxArea, area );
            if ( heights[i] < heights[j] ){
                ++i;
            }
            else {
                --j;
            }
        }
        return maxArea;
    }

    public static void main(String[] args){
        int[] heights = {1,8,6,2,5,4,8,3,7};
        System.out.println(find(heights));
    }
}
