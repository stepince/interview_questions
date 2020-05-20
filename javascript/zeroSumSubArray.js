
function is_subset(l) {
    var mem = {};
    var sum = 0;
    for ( let i = 0; i < l.length; ++i ) {
        sum += l[i]; 
        if ( l[i] === 0 || sum === 0 || mem[sum] === true ) return true; 
        mem[sum] = true;
    }   
    return false;
}

var l = process.argv[2].split(" ").map(x => parseInt(x));
console.log( l );
var s = is_subset(l);

console.log ( s );