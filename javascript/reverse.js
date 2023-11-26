
// init prev to null
// loop
// assign next to current, keep a holder
// point current next to the prev
// update prev
// current = next
function reverse(node) {
   var prev = null;
   var current = node;
   while(current != null) {
       let next = current.next;
       current.next = prev;
       prev = current;
       current = next;
   }
   return prev;
}

function reverseDouble(node) {
   var current = node;
   var prev = null;
   while(current != null) {
       //swap
       let next = current.next;
       current.next = current.prev;
       current.prev = next;
       prev = current;     
       current = next;
   }
   return prev;
}