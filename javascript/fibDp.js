
/*
   fib
   0 1 1 2 3 5 8 13
*/

function fib(n, mem=[]){
    if ( n <= 1 ) return 0;
    if ( n === 2 ) return 1;
    if ( mem[n-1] === undefined ) mem[n-1] = fib(n-1,mem);
    if ( mem[n-2] === undefined ) mem[n-2] = fib(n-2,mem);
    mem[n] = mem[n-1] + mem[n-2];
    return mem[n];
}

var n = parseInt(process.argv[2]);
console.log(n);
console.log( fib(n));