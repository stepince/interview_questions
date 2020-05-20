/*

Given an array of n distinct elements, 
find length of the largest subset such that every pair in the subset
is such that the larger element of the pair is divisible by smaller element
*/


function divisble_subset(list){
    var mem ={}
    let max = 0;
    for ( let i = 0; i < list.length -1; ++i ) {
        for ( let j = i + 1; j < list.length; ++j ) {
            if ( list[j] % list[i] === 0 ) {
                mem[ list[j] ] = mem[ list[j] ] === undefined ? 2 :  mem[ list[j] ] + 1;
                max = Math.max(max, mem[ list[j] ]  ); 
            }
        }
    }
    return max;
}

var l = process.argv.splice(2).map(x => parseInt(x) ).sort( (a,b)=>a-b );
console.log( l );

var compare = divisble_subset(l);
console.log(compare);