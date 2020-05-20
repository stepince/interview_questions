/*

Given a distance ‘dist, count total number of ways to cover the distance with 1, 2 and 3 steps.
This is basically the same as the staircase problem.

*/

function num_ways(n) {
    if ( n <= 0 ) return 0;
    if ( n === 1 ) return 1;
    if ( n === 2 ) return 2;
    if ( n === 3 ) return 4;
    return num_ways(n-1) + num_ways(n-2) + num_ways(n-3);
}

var n = parseInt(process.argv[2]);

console.log( num_ways(n) );