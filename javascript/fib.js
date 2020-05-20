
/*
   fib
   0 1 1 2 3 5 8 13
*/

function fib(n){
    if ( n <= 1 ) return 0;
    if ( n === 2 ) return 1;
    return fib(n-1) + fib(n-2);
}

var n = parseInt(process.argv[2]);
console.log(n);
console.log( fib(n));