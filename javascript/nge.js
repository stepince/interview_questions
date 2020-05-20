function nge ( a ) {
   var n = [];
   var s = [];
   s.push( { val: a[0], index: 0} );
   var elem = null;
   for ( let i = 1; i < a.length; ++i ) {
       let next = { val: a[i], index: i };
       if ( s.length > 0 ) {
           elem = s.pop();
           while ( elem.val < next.val ) {
              n[elem.index] = next.val;
              if( s.length == 0  ) break;
              elem = s.pop();
           }
           if (elem.val > next.val) {
               s.push(elem);
           }
       }
       s.push(next);
   } 
   while ( s.length > 0 ) {
      next = s.pop();
      n[next.index] = -1;      
   }
   return n;
}

var l = process.argv.slice(2);
var a = [];
for ( let i = 0; i < l.length; ++i ) {
   a.push(parseInt(l[i]));
}

console.log(nge(a));


