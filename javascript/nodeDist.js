function findNode(n,val) {
   if ( n == null ) return null;

   if ( n.val == val ) return n;

   if ( val < n.val ) {
      let node = findNode(n.left,val);
      if ( node != null ) return node;
   }
   
   if ( val > n.val ) {
      let node = findNode(n.right,val);
      if ( node != null ) return node;
   }

   return null;
}

function findPath(n,val,nodes) {
   if ( nodes == null ) nodes = [];
   if ( n == null ) return null;
   nodes.push(n);
   if ( n.val == val ) return nodes;
   if ( findPath(n.left,val,nodes) != null ) return nodes;
   if ( findPath(n.right,val,nodes) != null ) return nodes;
   nodes.pop();
   return null;  
}

function findNodeDescendantDistance(n,k,nodes, visited){
   if ( nodes == null ) nodes = [];
   if ( n == null ) return nodes;
   
   if ( k == 0 ) {
       nodes.push(n);
       return;
   }

   if ( (n.left != null) && (visited !== n.left) ) {
       findNodeDescendantDistance(n.left,k-1,nodes, n, null);
   }
   if ( n.right != null && (visited !== n.right ) ) {
       findNodeDescendantDistance(n.right,k-1,nodes, n, null);
   }
   return nodes;   
}

function findNodeDistance ( n, val, k ) {
   if ( n == null ) return;
   var nodes = [];
   var path = findPath(n,val);
   var visited = null;
   while ( path.length > 0 && k >= 0 ){
       let target = path.pop();
       findNodeDescendantDistance(target,k--,nodes,visited);
       visited = target;
   }
   return nodes;   
}

function printNodeList(l){
   var str = "";
   var comma = "";
   for(let i = 0; i < l.length; ++i ){
      str += comma + l[i].val;
      comma = ",";
   }
   console.log(str);   
}
var root = {val: 20, left: null, right: null };
root.left = {val: 8, left: null, right: null };
root.right = {val: 22, left: null, right: null };
root.left.left = {val: 4, left: null, right: null };
root.left.right = {val: 12, left: null, right: null };
root.left.right.left = {val: 10, left: null, right: null };
root.left.right.right = {val: 14, left: null, right: null };

var l = findNodeDistance ( root, 4, 3);

printNodeList(l);