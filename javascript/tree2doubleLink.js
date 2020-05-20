function dfsInoder(n,callback){
   if ( n == null ) return;
   dfsInoder(n.left,callback);
   callback(n);
   dfsInoder(n.right,callback);  
}

function doubleLinkedList(t){
   if ( t == null ) return null;
   var head = null, tail = null;

   var f = function ( n ) {
       if ( tail == null ) {
          head = tail = n;
          return;
       }
       tail.right = n;
       n.left =  tail;
       tail = n;
   }
   dfsInoder( t , f );
   return head;
}

function printList(t) {
  var str = "";
  var comma = "";
  while( t != null ) {
     str += comma + t.val;
     t = t.right;
     comma = ",";
  }
  console.log(str);
}

var root = {val: 5, left: null, right: null };
root.left = {val: 3, left: null, right: null };
root.right = {val: 6, left: null, right: null };
root.left.left = {val: 1, left: null, right: null };
root.left.right = {val: 4, left: null, right: null };
root.right.right = {val: 8, left: null, right: null };
root.left.left.left = {val: 0, left: null, right: null };
root.left.left.right = {val:2, left: null, right: null };
root.right.right.left = {val:7, left: null, right: null };
root.right.right.right = {val:9, left: null, right: null };

var h = doubleLinkedList(root);
printList(h);
