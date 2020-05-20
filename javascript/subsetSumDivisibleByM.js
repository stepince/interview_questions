
function divisble_subsetUtil(list, index, total, m){
    if ( index === 0 ) return (total !== 0 && total%m === 0);

    if ( list[index-1] === 0 ) return true;

    var compareInclude = divisble_subsetUtil(list, index-1, total+list[index-1], m);
    var compareExclude = divisble_subsetUtil(list, index-1, total, m ); 
    return compareInclude || compareExclude;
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