import java.util.HashSet;
import java.util.Set;
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


 */
public class FriendCircles {


    boolean dfsVisit( int[][] M, int row, Set<Integer> visited ){
        if ( visited.contains(row) ) return false;
        visited.add(row);
        boolean doCount = false;
        for ( int col = 0 ; col < M[row].length; ++col ){
            if ( M[row][col] == 1 ) {
                doCount = true;
                dfsVisit(M,col,visited);
            }
        }
        return doCount;
    }

    public int findCircleNum(int[][] M) {
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        for ( int row = 0 ; row < M.length ; ++row ){
            if ( dfsVisit(M,row,visited)  ) ++count;
        }
        return count;
    }
}
