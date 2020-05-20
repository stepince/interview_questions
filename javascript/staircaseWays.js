
/*
 *  Number of ways to go down a straircae, 2 steps or one step
 *
*/
function num_ways(n, mem = []){
   if ( n <= 1 ) return 1;
   if ( mem[n-1] === undefined ) mem[n-1] = num_ways(n-1);
   if ( mem[n-2] === undefined ) mem[n-2] = num_ways(n-2);

   mem[n] = mem[n-2] + mem[n-1];
   return mem[n]; 
}

var n = parseInt( process.argv[2] );
console.log( n );

var compare = num_ways(n);
console.log(compare);