
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
   var last = null;
   var current = node;
   while(current != null) {
       // swap
       let next = current.next;
       current.next = current.prev;
       current.prev = next;
       // store last
       last = current;     
       // move to the next 
       current = next;
   }
   return last;
}