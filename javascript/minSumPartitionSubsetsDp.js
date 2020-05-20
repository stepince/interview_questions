var counter = 0;
var memCounter = 0;
function swap(list,a,b){
    if ( a === b ) return;
    var temp = list[a];
    list[a] = list[b];
    list[b] = temp;
}

function diffCompare(list1,list2) {
    var sum1 = list1.length === 0 ?  0 : list1.reduce( (acc,item)=>acc+item );
    var sum2 = list2.length === 0 ?  0 : list2.reduce( (acc,item)=>acc+item );
    return Math.abs( sum1 - sum2 );    
}

function getCompareKey(compareA,compareB){
    var keyA1 = compareA.partA.sort((a,b)=>a-b).join(',') 
    var keyA2 = compareA.partB.sort((a,b)=>a-b).join(',');
    var keyA = [ keyA1, keyA2 ].sort().join(':');

    var keyB1 = compareB.partA.sort((a,b)=>a-b).join(',') 
    var keyB2 = compareB.partB.sort((a,b)=>a-b).join(',');
    var keyB = [ keyB1, keyB2 ].sort().join(':');

    return [ keyA, keyB ].sort().join('!');
}

function min_subsetUtil(partA, partB, compare, mem = {}) {
    ++counter;
    if ( partA.length === 0 ) return compare;
    if ( compare.diff === 0 ) return compare;

    var currentCompare = { partA:partA, partB:partB, diff: diffCompare(partA,partB) }

    if ( currentCompare.diff === 0 ) return currentCompare;

    if ( currentCompare.diff < compare.diff ) compare = currentCompare;
    for ( let i = 0; i < partA.length; ++i ){
        swap(partA,0,i);
        let newPartA = partA.slice(1);
        let newPartB = [...partB,partA[0]];
        let key = getCompareKey(compare, {partA:newPartA, partB:newPartB} );
        if ( mem[key] === undefined ) {
            mem[key] = compare = min_subsetUtil(newPartA, newPartB, compare,mem);
        }
        else {
            ++memCounter;
            //console.log('found key: ', key);
            compare = mem[key];
        }
        swap(partA,0,i);
    }
    return compare;
}

function min_subset(list) {
    var mem = {};
    var result = min_subsetUtil(list,[], { partA:list, partB:[], diff: list.reduce( (acc,item)=>acc+item ) }, mem );
    //console.log('mem size: ', Object.keys(mem).length );
    return result;
}

var l = process.argv.slice(2).map(x => parseInt(x) );
console.log( l );
var compare = min_subset(l);
console.log(compare);
console.log('mem lookup counter:', memCounter );
console.log('runtime: ', counter);