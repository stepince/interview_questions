/*

We have discussed Overlapping Subproblems and Optimal Substructure properties.

Let us discuss Longest Increasing Subsequence (LIS) problem as an example problem 
that can be solved using Dynamic Programming.
The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given
sequence such that all elements of the subsequence are sorted in increasing order.
For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 
Ans: 6
*/


function lis_util(list, index, next, counter ){

   if ( index <= 0 ) return counter;

   var includeCounter = counter;;

   if ( list[index-1] < next ) {
      includeCounter = includeCounter === 0 ? 2 : (includeCounter + 1);
   }
   else {
       includeCounter = 0;
   }
   var includeCompare = lis_util(list,index-1,list[index-1], includeCounter  );
   var excludeCompare = lis_util(list,index-1,next, counter );
   return Math.max (includeCompare, excludeCompare );
}

function lis(list){
   return lis_util(list,list.length-1,list[list.length-1], 0);
}

var l = process.argv.slice(2).map(x => parseInt(x) );
console.log( l );

var compare = lis(l);
console.log(compare);