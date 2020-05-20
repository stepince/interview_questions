function findMaxSum(a) {
    var incl = a[0];
    var excl = 0;
    var excl_new;
 
    for ( let i = 1; i < a.length; ++i){
        /* current max excluding i */
        excl_new = (incl > excl) ? incl : excl;
 
        /* current max including i */
        incl = excl + a[i];
        excl = excl_new;     
    }
    /* return max of incl and excl */
    return ((incl > excl) ? incl : excl);
    
}

var l = process.argv.slice(2);
var a = [];
for ( let i = 0; i < l.length; ++i ) {
   a.push(parseInt(l[i]));
}

console.log(findMaxSum(a));