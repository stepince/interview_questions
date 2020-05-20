function is_subset(l,sum,index = l.length) {
   // success
   if ( sum === 0 ) return true;
   
   // base case if we made it here than return false
   if ( index === 0 ) return false;
 
   return  is_subset(l,sum,index-1) || is_subset(l, (sum === undefined ? 0 : sum) - l[index-1], index-1);
}

var l = process.argv[2].split(" ").map(x => parseInt(x));
console.log( l );
var s = is_subset(l);

console.log ( s );