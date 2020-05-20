var counter = 0;
var memCounter = 0;

function getComplimentSubset(list,subset){
    var s = [...list];
    for ( let item of subset ) {
        let idx = s.indexOf(item);
        s.splice(idx,1);
    }
    return s;
}

function min_subsetUtil(list, subset, index, total, calculatedSum, mem = {}) {
    ++counter;
    if ( index === 0 ){
        let diff = Math.abs(total - 2*calculatedSum);
        let subsetB = getComplimentSubset(list,subset);
        return {diff: Math.abs(total - 2*calculatedSum), subsetA: subset.sort( (a,b)=>a-b), subsetB: subsetB};
    }
  
    let includeKey = list.slice(0,index-1).sort( (a,b)=>a-b ).join(',') + ':' + calculatedSum+list[index-1];
    if ( mem[includeKey] === undefined ) {
        mem[includeKey] = min_subsetUtil(list, [...subset,list[index-1]], index-1, total, calculatedSum+list[index-1], mem);
    }
    else {
        ++memCounter;
    }
    var compareInclude = mem[includeKey];

    let excludeKey = list.slice(0,index-1).sort( (a,b)=>a-b ).join(',') + ':' + calculatedSum;
    if ( mem[excludeKey] === undefined ) {
        mem[excludeKey] = min_subsetUtil(list, subset, index-1, total, calculatedSum, mem); 
    }
    else {
        ++memCounter;
    }
    var compareExclude = mem[excludeKey];
 
    return compareInclude.diff < compareExclude.diff ? compareInclude : compareExclude;
}

function min_subset(list) {
    var total = list.reduce( (acc,item)=>acc+item );
    var subset = [];
    var mem = {};
    return min_subsetUtil(list, subset, list.length, total, 0, mem );
}

var l = process.argv.slice(2).map(x => parseInt(x) );
console.log( l );
var compare = min_subset(l);
console.log(compare);
console.log('mem lookup counter:', memCounter );
console.log('runtime: ', counter);