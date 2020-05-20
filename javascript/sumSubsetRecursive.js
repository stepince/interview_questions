function is_subset(l,n,sum) {
   if ( sum === 0 )return true;

   if ( n == 0 ) return false;
   
   if ( l[n-1] > sum ) return is_subset(l,n-1,sum);

   return is_subset(l,n-1,sum) || is_subset(l,n-1,sum - l[n-1]);
}

var l = process.argv[2].split(" ").map(x => parseInt(x) ).sort();
console.log( l );
var s = is_subset(l,l.length,9);

console.log ( s );