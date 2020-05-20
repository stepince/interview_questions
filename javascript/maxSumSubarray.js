/*

A sub-array has one number of some continuous numbers. 
Given an integer array with positive numbers and negative numbers,
get the maximum sum of all sub-arrays. Time complexity should be O(n). 
For example, in the array {1, -2, 3, 10, -4, 7, 2, -5}, 
its sub-array {3, 10, -4, 7, 2} has the maximum sum 18.
*/


function maxSumSubarray(list, index, curr){

    if ( index === 0 ) return curr;

    var incl = maxSumSubarray( l, index-1, curr+l[index-1]);

    var excl = maxSumSubarray( l, index-1, l[index-1]);

    return Math.max( incl, excl , curr );

}

var l = process.argv.slice(2).map( (x) => parseInt(x) );
console.log(l);

console.log(maxSumSubarray(1,l.length,0));

