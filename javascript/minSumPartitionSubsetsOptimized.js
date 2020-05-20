
function min_subsetUtil(list, subset, index, total, calculatedSum) {

    if ( index === 0 ) return {diff: Math.abs(total - 2*calculatedSum), subset: subset};

    var compareInclude = min_subsetUtil(list, [...subset,list[index-1]], index-1, total, calculatedSum+list[index-1]);
    var compareExclude = min_subsetUtil(list, subset, index-1, total, calculatedSum );    
    return compareInclude.diff < compareExclude.diff ? compareInclude : compareExclude;
}

function min_subset(list) {
    var total = list.reduce( (acc,item)=>acc+item );
    return min_subsetUtil(list, [], list.length, total, 0 );
}

var l = process.argv.slice(2).map(x => parseInt(x) );
console.log( l );
var compare = min_subset(l);
console.log(compare);