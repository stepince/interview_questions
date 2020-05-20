function printList(l){
    for ( let i = 0; i < l.length; ++i) {
       console.log(l[i]);
    }   
}

function maxFreq(list) {
    var m = new Map();
    var curMax = 0;
    var maxList = [];
    for ( let i = 0; i < list.length; ++i) {
        let count = 1;
        if ( m.has(list[i]) ){
            count = m.get(list[i]) + 1;
            m.set(list[i],count);
        }
        else {
            m.set(list[i],1);
        }
        if ( curMax == count ) {
            maxList.push(list[i]);
        }
        else if ( count > curMax ) {
            curMax = count;
            maxList = [];
            maxList.push(list[i]);
        }
    }
    return maxList;      
}

var l = process.argv.slice(2);
var maxList = maxFreq(l);
printList( maxList );