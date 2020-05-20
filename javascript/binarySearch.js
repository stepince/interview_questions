function binarySearch (list, value) {
    let start = 0
    let end = list.length - 1
    let middle = Math.floor((start + end) / 2)

    while (list[middle] !== value && start < end) {
       if (value < list[middle]) {
           end = middle - 1
       }
       else {
           start = middle + 1
       }
       middle = Math.floor((start + end) / 2)
    }
    return (list[middle] !== value) ? -1 : middle
}

var l = process.argv.slice(2);
var m = {};

for ( let i = 0; i < l.length; ++i ) {
   var val = parseInt(l[i]);
   var val2 = Math.pow(val,2);
   m[ val2 ] = true;
}

console.log(m);
var triplets = [];
for ( let i = 0; i < l.length; ++i ) {
   for ( let j = i + 1; j  < l.length; ++j ) {
       let c2 = l[i]*l[i] + l[j]*l[j];
       if ( m[c2] == true ) {
          triplets.push ( { a: l[i] , b:l[j], c: Math.sqrt(c2) });
       }
   }
}

console.log(triplets);

