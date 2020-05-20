
function removeDuplicate(a){ 
  var m = {};

  var first = "a", last = "z";
  for(var i = first.charCodeAt(0); i <= last.charCodeAt(0); ++i) {
      var t = eval("String.fromCharCode(" + i + ")");
      m[t] = 0;
  }
  m[' '] = 0;
  for ( var i = 0, j=0; i < a.length; ++i) {
     if ( i != j ) {
        a[j] = a[i]; 
     }
     if ( m[a[i]] == 0 ) {
         m[a[i]] = 1;
         ++j;
     }
     else {
         m[a[i]] = 0;
     }
  }
  return a.splice(0,j).join("");
}

var val = removeDuplicate(process.argv.slice(2)[0].split('') );
console.log( 'word: ' + val );
