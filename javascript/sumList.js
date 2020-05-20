function isSum(l, sum) {
   var s = {};
   for ( let i = 0; i < l.length; ++i ) {
       s[l[i]] = true;
       let c = sum - l[i];
       if ( s[c] === true ) {
           return true;
       }    
   }
   return false;
}

var l = process.argv[2].split(' ');
var a = [];
for ( let i = 0; i < l.length; ++i) {
    a.push( parseInt(l[i]));
}
var sum = parseInt(process.argv[3]);
console.log(a);
console.log ( isSum(a,sum) );