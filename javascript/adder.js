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

function linkedListToString(n){
   var str = "";
   while( n != null) {
      str += n.val;
      n = n.next;
   }
   return str
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

function add(l1,l2) {
    var r1 = reverse(l1);
    var r2 = reverse(l2);
    var curr1 = r1;
    var curr2 = r2;
    var carry = 0;
    var tail = null, head = null;
    while ( curr1 != null && curr2 != null ) {
        let sum = parseInt(carry) + parseInt(curr1.val) + parseInt(curr2.val);
        if ( sum > 9 ) {
            carry = 1;
            sum = sum - 10;
        }
        else {
            carry = 0;
        }
        let n = { val: sum, next: null};
        if ( tail == null ) {
            head = tail = n;
        }
        else {
            tail.next = n;
            tail = tail.next;
        }
        curr1 = curr1.next;
        curr2 = curr2.next; 
    }

    if ( curr1 != null ) {
        let sum = carry + parseInt(curr1.val);
        if ( sum > 9 ) {
            sum = 0;
            carry =  1;
        }
        else {
            carry = 0;
        }
        tail.next = { val: sum, next: null };
        tail = tail.next;
        if ( carry == 1 ) {
            tail.next = { val: 1, next: null };
        }
    }
    else if ( curr2 != null ) {
        let sum = carry + parseInt(curr2.val);
        if ( sum > 9 ) {
            sum = 0;
            carry =  1;
        }
        else {
            carry = 0;
        }
        tail.next = { val: sum, next: null };
        tail = tail.next;
        if ( carry == 1 ) {
            tail.next = { val: 1, next: null };
        }
    }
   
    return linkedListToString(reverse(head));   
}

function reverse(node) {
   var prev = null;
   var current = node;
   var next = null; 
   while(current != null) {
       next = current.next;
       current.next = prev;
       prev = current;
       current = next;
   }
   node = prev;
   return node
}

var n1 = process.argv[2];
var a1 = n1.split('');

var n2 = process.argv[3];
var a2 = n2.split('');

var l1 = createLinkList(a1);


var l2 = createLinkList(a2);


//printLinkedList(r1);
//printLinkedList(r2);

var sum = add(l1,l2);
console.log(sum);