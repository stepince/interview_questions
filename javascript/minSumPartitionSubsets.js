function swap(list,a,b){
    var temp = list[a];
    list[a] = list[b];
    list[b] = temp;
}

function diffCompare(list1,list2) {
    var sum1 = list1.length === 0 ?  0 : list1.reduce( (acc,item)=>acc+item );
    var sum2 = list2.length === 0 ?  0 : list2.reduce( (acc,item)=>acc+item );
    return Math.abs( sum1 - sum2 );    
}

function min_subsetUtil(partA, partB, compare) {
    if ( partA.length === 0 ) return compare;
    if ( compare.diff === 0 ) return compare;
    var currentCompare = { partA:partA, partB:partB, diff: diffCompare(partA,partB) }

    if ( currentCompare.diff === 0 ) return currentCompare;

    if ( currentCompare.diff < compare.diff ) compare = currentCompare;
    for ( let i = 1; i < partA.length; ++i ){
        swap(partA,0,i);
        let newPartA = partA.slice(1);
        let newPartB = [...partB,partA[0]];
        compare = min_subsetUtil( newPartA, newPartB, compare );
        swap(partA,0,i);
    }
    return compare;
}

function min_subset(list) {
    return min_subsetUtil(list,[], { partA:list, partB:[], diff: list.reduce( (acc,item)=>acc+item ) } );
}

var l = process.argv.slice(2).map(x => parseInt(x) );
console.log( l );
var compare = min_subset(l);
console.log(compare);