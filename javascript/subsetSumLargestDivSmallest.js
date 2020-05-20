/*

Given an array of n distinct elements, 
find length of the largest subset such that every pair in the subset
is such that the larger element of the pair is divisible by smaller element

This works because all you need to do is check that the path all meet the criteria,
so you just need to increat the count by one
*/

function isDivisble(list, item1){
    if ( list.length == 0 ) return false;
    for ( let item2 of list ) {
        if ( item2%item1 !== 0 ) {
            return false;
        }   
    } 
    return true;     
}

function divisble_subsetUtil(list, index, subset, count ){
    if ( index === 0 ) return count;
      
    var divisble = isDivisble(subset, list[index-1] );
    var includeCount = count;
    if ( divisble ) {
        includeCount = (count == 0 ) ? 2 : (count+1);    
    }
    var includeSubset = [list[index-1],...subset];

    var includeCompare = divisble_subsetUtil(list, index-1, includeSubset, includeCount ); 
    var excludeCompare = divisble_subsetUtil(list, index-1, subset, count ); 
    return Math.max(includeCompare,excludeCompare);
}


function divisble_subset(list){
    return divisble_subsetUtil(list, list.length, [], 0);
}

var l = process.argv.splice(2).map(x => parseInt(x) ).sort( (a,b)=>a-b );
console.log( l );

var compare = divisble_subset(l);
console.log(compare);