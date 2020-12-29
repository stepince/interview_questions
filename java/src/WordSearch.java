/*
Given an m x n board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where "adjacent"
cells are horizontally or vertically neighboring.
The same letter cell may not be used more than once.

https://leetcode.com/problems/word-search/

Time:
    O(N*3^N)

Space:
   O(L)
   length of the word (chars)
 */

public class WordSearch {
    public boolean exist(char[][] board, char[] chars, int idx, int x, int y) {
        if ( idx == chars.length ) return true;
        if ( x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != chars[idx++] ) return false;
        char ch = board[x][y];
        board[x][y] = '.';
        boolean ans =
                exist(board,chars,idx,x-1,y) ||
                exist(board,chars,idx,x+1,y) ||
                exist(board,chars,idx,x,y-1) ||
                exist(board,chars,idx,x,y+1);
        board[x][y] = ch;
        return ans;
    }

    public boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();
        for ( int x = 0; x < board.length;  ++x) {
            for ( int y = 0; y < board[0].length;  ++y) {
                if ( exist(board,chars,0,x,y) ) return true;
            }
        }
        return false;
    }

}
