var k = 2;
function printList(l){
    for ( let i = 0; i < l.length; ++i) {
       console.log(l[i]);
    }   
}

function topK(list) {
    var m = new Map();
    var topKList = [];
    for ( let i = 0; i < list.length; ++i) {
        let count = 1;
        if ( m.has(list[i]) ) {
            count = m.get(list[i]) + 1;
            m.set(list[i],count);
        }
        else {
            m.set(list[i],1);
        }
    }

    var sortedList = Array.from(m);

    sortedList.sort ( (a,b) => b[1] - a[1] );
    printList( sortedList );

    for ( let i = 0; i < k; ++i ) {
        topKList.push(sortedList.shift());
    }

    while( (sortedList.length > 0) && (sortedList[0][1] == topKList[topKList.length - 1][1]) ) {
        topKList.push(sortedList.shift());
    }

    return topKList;     
}

var l = process.argv.slice(2);
console.log(l);
var maxList = topK(l);
console.log('>>>>>>');
printList( maxList );



