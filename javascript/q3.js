function shiftChar(a,o){
  for ( let i = o; i < a.length -1; ++i) {
      a[i] = a[i+1];
  }
  delete a[a.length-1];
}

function removeDuplicate(a){ 
console.log('array length: ' + a.length );
  var map = {};
  for ( let i = 0; i < a.length; ++i) {
     if ( map[a[i]] ) {
         delete map[a[i]];
         for ( let j = i; j < a.length -1; ++j) {
             a[j] = a[j+1];
         }
         a[a.length -1] = '\0';
     }
     else {
         map[a[i]] = true;
     }   
  }
  return a.join("");
}

var val = removeDuplicate(process.argv.slice(2)[0].split('') );
console.log( 'word: ' + val );
