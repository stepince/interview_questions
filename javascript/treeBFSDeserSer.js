function serializeTree(n){
    var l = [];
    if ( n == null ) {
        return l;
    }
    let q = [];
    q.push(n);
    while ( q.length != 0 ) {
       let node = q.shift(); 
       if ( node != null ) {
           l.push(node.val);
           q.push(node.left);
           q.push(node.right);
       }
       else {
           l.push(null);
       }
    }
    return l;
}

deserializeTree(l){
   if ( l == null || l.length == 0 || l[0] == null ) return null;

   var root =  { val: l[0],  left: null, right: null);
   for ( let = i = 1; i < l.length; ++i ) {

       let p = (i-1) / 2;
       if ( i%2 == 1 ) {
           p.left = { val: l[i], left: null, right: null);
       }
       else {
           p.right = { val: l[i], left: null, right: null);
       }
   }   
   return root;
}