/*
  Given a value N, if we want to make change for N cents, 
and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins, 
how many ways can we make the change? The order of coins doesn’t matter.

For example, for N = 4 and S = {1,2,3}, there are four solutions:
{1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4. 
For N = 10 and S = {2, 5, 3, 6}, there are five solutions: 
{2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}. So the output should be 5.

node coinChangeDp.js 4 "1 2 3"
node coinChangeDp.js 10  "2  5 3 6"

note: modified to print the solution
*/

function coinChange(n,coins, index=0, list=[], mem={}){
   if ( n == 0 ) return 1;

   if ( n < 0 ) return 0;
   var total = 0;
   for ( let i = index; i < coins.length; ++i ) {
       if ( coins[i] > n ) break;
       let key = (n - coins[i]) + ':' + i;
       list.push(coins[i]);
       if ( mem[key] === undefined ) {
           mem[key] = coinChange( n - coins[i], coins, i, list, mem );
       }
       if ( (n - coins[i]) === 0 ) console.log(list);
       list.pop();
       total += mem[key];
   }
   return total;
}

var total = parseInt(process.argv[2]);

var coins = process.argv[3].split(" ").map( (x) => parseInt(x) ).sort( (a,b)=>a-b );
console.log(coins);
console.log(total);

console.log( coinChange(total,coins) );