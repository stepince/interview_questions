function serializeTree(n,l){
    if ( l == null ) l = [];
    if ( n == null ) {
        l.push(null);
        return l;
    }
    l.push(n.val);
    serializeTree(n.left,l);
    serializeTree(n.right,l);
    return l;
}

deserializeTree(l){
   if ( l == null || l.length == 0 || l[0] == null ) return null;

   var n = {val: l.shift(), left:null, right:null};
   
   n.left = deserializeTree(l);
   n.right = deserializeTree(l);

   return n;
}