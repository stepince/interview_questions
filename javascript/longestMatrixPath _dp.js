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

function longestPath(matrix,i,j, mem, visited=[]){
    let rows = matrix.length;
    let cols = matrix[0].length; 
    if ( rows === 0 || cols === 0 ) return 0;
    var mainKey = i + ':' + j =
    if ( visited[mainKey] === true ) return 0;
    visited[mainKey] = true;
    let left = 0, right = 0, up = 0, down = 0;
    if ( mem[ mainKey ] !== unefined ) return  mem[ mainKey ];
    if ( j > 0 &&  Math.abs( matrix[i][j-1] - matrix[i][j]) <= 1 ) {  
        let key = i + ':' + (j-1);
        if ( mem[key]  ===  undefined ) mem [key] = longestPath( matrix,i,j-1, mem, visited ) + 1; 
        left = mem[key];
    }
    if ( j < cols-1 && Math.abs(matrix[i][j+1] - matrix[i][j]) <= 1 ) {
        let key = i + ':' + (j+1);
        if ( mem[key]  ===  undefined ) mem [key] = longestPath( matrix, i, j + 1, mem, visited) + 1; 
        right = mem[key];
    }
    if ( i < rows-1 && Math.abs(matrix[i+1][j] - matrix[i][j]) <= 1 ) {
        let key = i + ':' + (i+1);
        if ( mem[key]  ===  undefined ) mem[key] = longestPath( matrix, i+1, j, mem, visited ) + 1;
        down = mem[key];
    }
    if ( i > 0 && Math.abs(matrix[i-1][j] - matrix[i][j]) <= 1 ) {
        let key = i + ':' + (i-1);
        if ( mem[key]  ===  undefined ) mem[key] = longestPath( matrix, i-1, j, mem, visited ) + 1;
        up = mem[key];
    }
    mem[  i + ':' + j ] = Math.max( up, down, left, right);
    return mem[  i + ':' + j ];
}

function longestMatrixPath(matrix){
    let max = 0;
    var mem = {};
    for ( let i = 0; i < matrix.length; ++i ) {
        let row = matrix[i];
        for ( let j = 0; j < row.length; ++j ) {
           if ( mem[ i+":"+j ] === undefined ) mem[ i+":"+j ] =  Math.max(max,longestPath(matrix,i,j));
           max = Math.max(max, mem[ i+":"+j ]);
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