function createLinkList(l){
   var head = null, tail = null;
   for ( let i = 0; i  < l.length; ++i ) {
      var n = { val: l[i], next: null};
      if ( tail == null ) {
          head = tail = n;
      }
      else {
          tail.next = n;
          tail = n;
      }
   }
   return head;
}

function printLinkedList(n){
   var str = "";
   var comma = "";
   while( n != null) {
      str += comma + n.val;
      comma = ",";
      n = n.next;
   }
   console.log(str);
}

function reverseSegment(node, k) {
   var prev = null;
   var current = node;
   var tail = node;
   for(let i = 0; current != null && i < k; ++i) {
       let next = current.next;
       current.next = prev;
       prev = current;
       current = next;
   }
   node = prev;
   if ( current ) {
       tail.next = current;
   }   
   return [node,tail];
}

function reverse(node, k) {
   var current = node;
   var head = null;
   var current_s = null;
   var prev_s = null;
   while ( current != null ) {
      prev_s = current_s;
      current_s = reverseSegment(current, k)
      if ( head == null ) {
          head = current_s[0]; 
      }
      current = current_s[1].next;
      if ( prev_s != null ) {
          prev_s[1].next = current_s[0];
      }
   }
   return head;
}

var l = process.argv.slice(2);
var n = createLinkList(l);

console.log(l)
var n2 = reverse(n, 3);

printLinkedList(n2);