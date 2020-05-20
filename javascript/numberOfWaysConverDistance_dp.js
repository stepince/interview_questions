/*

Given a distance ‘dist, count total number of ways to cover the distance with 1, 2 and 3 steps.
This is basically the same as the staircase problem.

*/

function num_ways(n, mem=[]) {
    if ( n <= 0 ) return 0;
    if ( n === 1 ) return 1;
    if ( n === 2 ) return 2;
    if ( n === 3 ) return 4;
    if ( mem[n-1] === undefined ) mem[n-1] = num_ways(n-1,mem);
    if ( mem[n-2] === undefined ) mem[n-2] = num_ways(n-2,mem);
    if ( mem[n-3] === undefined ) mem[n-3] = num_ways(n-3,mem);
    mem[n] = mem[n-1] + mem[n-2] + mem[n-3];
    return mem[n];
}

var n = parseInt(process.argv[2]);

console.log( num_ways(n) );