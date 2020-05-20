

function is_subset(l,n,sum,mem) {
   mem = mem || new Map();
   if ( sum === 0 ) return true;
   
   if ( n == 0 ) return false;

   var exclude_key = sum + ":" + (n-1);
   var include_key = (sum - l[n-1]) + ":" + (n-1);
     
   if ( mem.has(exclude_key) ) {
       var exclude_val = mem.get(exclude_key);
   }
   else {
       var exclude_val = is_subset(l,n-1,sum,mem); 
       mem.set(exclude_key,exclude_val);
   }

   if ( l[n-1] > sum ) {
       return exclude_val;
   }

   if ( mem.has(include_key) ) {
       var include_val = mem.get(include_key);
   }
   else {
       var include_val = is_subset(l,n-1,sum - l[n-1], mem); 
       mem.set(include_key,include_val);
   }

   return exclude_val || include_val;
}

var l = process.argv[2].split(" ").map(x => parseInt(x) ).sort();
var sum = parseInt( process.argv[3]);
console.log( l, sum );
var s = is_subset(l,l.length,sum);

console.log ( s );
