/*
Partition problem is to determine whether a given set can be partitioned into two subsets 
such that the sum of elementsin both subsets is same.



arr[] = {1, 5, 11, 5}
Output: true 
The array can be partitioned as {1, 5, 5} and {11}

arr[] = {1, 5, 3}
Output: false 
The array cannot be partitioned into equal sum sets.

*/

const reducer = (accumulator, currentValue) => accumulator + currentValue;

function isPartitionSubset(list, total = list.reduce(reducer), count = 0){
     
    if ( total%2 === 1) return false;

    if ( count === total - count ) return true;

    if ( list.length === 0 ) return false;

    return isPartitionSubset(list.slice(1),total, count+list[0]) || isPartitionSubset(list.slice(1),total,count);
}


var list = process.argv.slice(2).map( (x)=>parseInt(x) );
console.log(list);

console.log(isPartitionSubset(list));