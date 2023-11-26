/*

https://leetcode.com/problems/surrounded-regions/

Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

O(N)
  where N is the number of cells.
  implementation is sufficiently fast.
 */

public class SurroundedRegions {

    int ROWS;
    int COLS;

    void dfs(char[][] board, int row, int col) {
        if (row == this.ROWS || col == this.COLS || row < 0 || col < 0 || board[row][col] == 'X' || board[row][col] == '1')
            return;
        board[row][col] = '1';
        dfs(board, row - 1, col);
        dfs(board, row + 1, col);
        dfs(board, row, col - 1);
        dfs(board, row, col + 1);
    }

    void flip(char[][] board, int row, int col) {
        if (row == this.ROWS || col == this.COLS || row < 0 || col < 0 || board[row][col] == 'X' || board[row][col] == '1')
            return;
        board[row][col] = 'X';
        flip(board, row - 1, col);
        flip(board, row + 1, col);
        flip(board, row, col - 1);
        flip(board, row, col + 1);
    }

    public void solve(char[][] board) {
        this.ROWS = board.length;
        this.COLS = board[0].length;

        // do a dfs on all border cells.
        for (int col = 0; col < this.COLS; ++col) {
            dfs(board, 0, col);
        }
        for (int col = 0; col < this.COLS; ++col) {
            dfs(board, this.ROWS - 1, col);
        }
        for (int row = 1; row < this.ROWS - 1; ++row) {
            dfs(board, row, 0);
        }
        for (int row = 1; row < this.ROWS - 1; ++row) {
            dfs(board, row, this.COLS - 1);
        }

        for (int row = 0; row < this.ROWS; ++row) {
            for (int col = 0; col < this.COLS; ++col) {
                if (board[row][col] == '1') {
                    board[row][col] = 'O';
                } else if (board[row][col] == 'O') {
                    flip(board, row, col);
                }
            }
        }
    }
}
