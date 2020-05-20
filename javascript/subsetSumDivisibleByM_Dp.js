
function divisble_subsetUtil(list, index, total, m, mem = {}){
    if ( index === 0 ) return (total !== 0 && total%m === 0);

    if ( list[index-1] === 0 ) return true;

    var includeKey = list.slice(0,index-1).sort( (a,b)=>a-b).join(',') + ':' + total+list[index-1] );
    if ( mem[includeKey] === undefined ) {
        mem[includeKey] = divisble_subsetUtil(list, index-1, total+list[index-1], m, mem);
    }

    var excludeKey = list.slice(0,index-1).sort( (a,b)=>a-b).join(',') + ':' + total );
    if ( mem[excludeKey] === undefined ) {
        mem[excludeKey] = divisble_subsetUtil(list, index-1, total, m, mem );
    }

    return  mem[includeKey] || mem[excludeKey];
}

function divisble_subset(list, m){
    return divisble_subsetUtil(list, list.length, 0, m);
}

var l = process.argv[2].split(' ').map(x => parseInt(x) );
console.log( l );
var m = parseInt(process.argv[3]);
console.log( m );
var compare = divisble_subset(l,m);
console.log(compare);