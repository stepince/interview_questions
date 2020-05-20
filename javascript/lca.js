function findPath( root, val1, path ) {
   if ( path == null ) path = [];
   if ( root == null ) return null;

   path.push(root);
   if ( root.val == val1 ) return path;
   if ( root.val < val1 ) return findPath( root.left, val1, path );
   if ( root.val > val1 ) return findPath( root.right, val1, path );
}

function findLca( root, val1, val2){
   var p1 = findPath(root,val1);
   var p2 = findPath(root,val2); 

   if ( p1 == null || p2 == null ) return null;
   for ( var i = 0, j = 0; i < p1.length, j < p2.length; ++i, ++j ) {
       if ( p1[i].val != p2[j].val ) break;
   }

   if ( i == 0 ) return null;
   return p1[i-1];
}