
/*
Given a rope of length n meters, cut the rope in different parts of integer lengths 
in a way that maximizes product of lengths of all parts. 
You must make at least one cut. Assume that the length of rope is more than 2 meters.

Examples:
Input: n = 2
Output: 1 (Maximum obtainable product is 1*1)


*/

const reducer = (accumulator, currentValue) => accumulator * currentValue;

function maxRopeProduct(n, list=[]) {

   if ( n === 0 ) {
       console.log(list);
       return list.reduce(reducer);
   }
 
   if ( n < 0 ) return 0;
   let max = 0;
   for ( let i = 1; i <= n; ++i ) {
       list.push(i);
       max = Math.max( max, maxRopeProduct(n-i,list) );
       list.pop();
   }
   return max;
}


function maxRopeProduct2(n, total=1){

   if ( n === 0 ) return total;
 
   if ( n < 0 ) return 0;
   let max = 0;
   for ( let i = 1; i <= n; ++i ) {
       max = Math.max( max, maxRopeProduct(n-i, total*i) );
   }
   return max;
}

var len = parseInt( process.argv[2] );


console.log(len);


console.log(maxRopeProduct(len));