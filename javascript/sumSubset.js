
function is_subset(l,sum,index) {

   if ( l == null || l.length == 0 ) return false;
   
   index = (index === undefined) ?  l.length: index;

   if ( sum === 0 ) return true;

   if ( index === 0 ) return false;
   
   return is_subset(l,sum,index-1) || is_subset(l,sum- l[index-1],index-1);
}

var l = process.argv[2].split(" ").map(x => parseInt(x) ).sort();
var sum = parseInt( process.argv[3]);
console.log( l, sum );
var s = is_subset(l,sum);
console.log(s);