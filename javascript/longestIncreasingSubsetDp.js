/*

We have discussed Overlapping Subproblems and Optimal Substructure properties.

Let us discuss Longest Increasing Subsequence (LIS) problem as an example problem 
that can be solved using Dynamic Programming.
The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given
sequence such that all elements of the subsequence are sorted in increasing order.
For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 
Ans: 6
*/


function largest_subset(list){
    var max = 0;
    for ( let i = 0; i  < list.length - 1; ++i ) {
        let prev = l[i];
        prevCount = 0;
        for ( let j = i + 1; j < list.length; ++j) {
            if ( prev < l[j] ) {
                prev = l[j];
                prevCount = prevCount === 0 ? 2 : (prevCount+1);
            } 
            max = Math.max(max,prevCount); 
        }
    }
    return max;
}

var l = process.argv.splice(2).map(x => parseInt(x) );
console.log( l );

var compare = largest_subset(l);
console.log(compare);