function checkStack(st) {
    if ( st.length >= 2 && st[st.length - 2] == 'a' && st[st.length - 1] == 'b' ) {
        st.pop();
        st.pop();
    }
}

function isValid(l){
   if ( l.length == 0 ) return false;
   var st = [];
   for ( let i = 0; i < l.length; ++i) {
       if ( l[i] == 'a' ) {
           st.push('a');
       }
       else if ( l[i] == 'b' ) {
           st.push('b');
           checkStack(st);
       }
       else {
           return false;
       }
   };
   return i == l.length && st.length == 0;
}

var l = process.argv[2].split('');
console.log(isValid(l));