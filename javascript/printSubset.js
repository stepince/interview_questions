function generateSubset(l, subset, result, index) {
   index = index || 0;
   subset = subset || [];
   result = result || [];
   for ( let i = index; i < l.length; ++i ) {
       let s = subset.slice(0);
       s.push(l[i]);
       //console.log('splicing: ',l, subset, s);
       result.push(s);
       generateSubset(l, s, result, i+1);
   }
   return result;
}

var l = process.argv.splice(2);
console.log( l );
console.log( generateSubset(l) );
