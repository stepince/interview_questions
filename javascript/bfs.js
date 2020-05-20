function bfsTree (  t, callback ) {
  if ( t == null ) return;
  var q = [];
  q.push(t) ;
  while ( q.length > 0 ) {
      let n = q.shift();
      if ( n.left != null ) q.push(n.left);
      if ( n.right != null ) q.push(n.right);
      callback(n);
  }
}


function bfsGraph (  t, callback ) {
  var visited = new Map();
  if ( t == null ) return;
  var q = [];
  q.push(t) ;
  while ( q.length > 0 ) {
      let n = q.shift();
      for ( let i = 0; n.children; ++i ) {
          if ( !visited.has(n.children[i]) ) {
              visited.set(n,true);
              callback(n);
          }
      }
      
  }
}
