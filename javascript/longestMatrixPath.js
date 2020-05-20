/*

Given a n*n matrix where all numbers are distinct, 
find the maximum length path (starting from any cell)
such that all cells along the path are in increasing order with a difference of 1.

We can move in 4 directions from a given cell (i, j),
 i.e., we can move to (i+1, j) or (i, j+1) or (i-1, j) or 
(i, j-1) with the condition that the adjacent cells have a difference of 1.


Input:  mat[][] = {{1, 2, 9}
                   {5, 3, 8}
                   {4, 6, 7}}
Output: 4
The longest path is 6-7-8-9. 
"1 2 9" "5 3 9" "4 6 7"
*/

function longestPath(matrix,i,j, visited=[]){
    let rows = matrix.length;
    let cols = matrix[0].length; 
    if ( rows === 0 || cols === 0 ) return 0;

    if ( visited[ i + ':' + j] === true ) return 0;
    visited[ i + ':' + j] = true;
    let left = 0, right = 0, up = 0, down = 0;

    if ( j > 0 &&  Math.abs( matrix[i][j-1] - matrix[i][j]) <= 1 ) {
        left = longestPath( matrix,i,j-1,visited ) + 1; 
    }
    if ( j < cols-1 && Math.abs(matrix[i][j+1] - matrix[i][j]) <= 1 ) {
        right = longestPath( matrix, i, j + 1, visited) + 1;
    }
    if ( i < rows-1 && Math.abs(matrix[i+1][j] - matrix[i][j]) <= 1 ) {
        down = longestPath( matrix, i+1, j, visited ) + 1;
    }
    if ( i > 0 && Math.abs(matrix[i-1][j] - matrix[i][j]) <= 1 ) {
        up = longestPath( matrix, i-1, j, visited ) + 1;
    }
    return Math.max( up, down, left, right);
}

function longestMatrixPath(matrix){
    let max = 0;
    for ( let i = 0; i < matrix.length; ++i ) {
        let row = matrix[i];
        for ( let j = 0; j < row.length; ++j ) {
           max = Math.max(max,longestPath(matrix,i,j));
        }
    }
    return max;
}

var matrix = []
for ( let i = 0; i < process.argv.length -2; ++i ) {
    matrix.push(process.argv[i+2].split(' ').map( x=>parseInt(x) ));
}
console.log(matrix);

console.log(longestMatrixPath(matrix));