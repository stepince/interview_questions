
/*
   fib
   0 1 1 2 3 5 8 13
*/

function fib(n){
    if ( n <= 1 ) return 0;
    if ( n === 2 ) return 1;
    var n1 = 0;
    var n2 = 1;
    for ( let i = 1; i < n-2; ++i ) { 
        console.log('n:', n1, n2);
        let oldN2 = n2;
        n2 = n2 + n1;
        n1 = oldN2;
    }
    return n2
}

var n = parseInt(process.argv[2]);
console.log(n);
console.log( fib(n));