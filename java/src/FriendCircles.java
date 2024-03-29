/*

https://leetcode.com/problems/friend-circles/

There are N students in a class. Some of them are friends, while some are not.
Their friendship is transitive in nature. For example, if A is a direct friend of B,
and B is a direct friend of C, then A is an indirect friend of C. And we defined a
friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class.
If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not.
And you have to output the total number of friend circles among all the students.

Time:
  O(N^2)
https://leetcode.com/problems/number-of-provinces/

 */
public class FriendCircles {

    int[][] isConnected;
    boolean[] visited;
    boolean dfsVisit(int row) {
        if ( visited[row] ) return false;
        visited[row] = true;
        boolean visit = false;
        for( int col = 0; col < isConnected[0].length; ++col ) {
            if ( isConnected[row][col] == 1 ) {
                visit = true;
                dfsVisit(col);
            }
        }
        return visit;
    }

    public int findCircleNum(int[][] M) {
        this.isConnected = M;
        this.visited = new boolean[isConnected.length];
        int count = 0;
        for ( int row = 0 ; row < M.length ; ++row ){
            if ( dfsVisit(row)  ) ++count;
        }
        return count;
    }
}
