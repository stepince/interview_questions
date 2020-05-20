var dict =  new Set([ "i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "icecream", "man", "go", "mango"]);

function composeSentence(str,indices){
    var words = [];
    let prev = indices[0];
    for ( let i = 1; i < indices.length; ++i ) {
        words.push( str.substring(prev,indices[i]) );
        prev = indices[i];
    }
    return words.join(' ');
}

function getSetences(str,wb){
    var results = [];
    var keys = Array.from( wb.keys() ).sort( (a,b)=>a-b );
    if ( keys.length == 0 ) return null;
  
    var edges = []; 
    for ( let k of keys ) {
        for ( let v of wb.get(k) ) {
            edges.push( {index: k - v.length, value:v} );
        }
    }

    var nodes = new Map();
    for ( let e of edges ) {
        if ( !nodes.has(e.index) ) nodes.set(e.index, {index: e.index, values: []} );
        nodes.get(e.index).values.push(e.value);
    }
    // add the last node(!no children)
    nodes.set(str.length,{index:str.length,values:[]});
    var paths = [[0]];
    while( paths.length > 0 ){
        let currentPath = paths.shift();
        let idx = currentPath[currentPath.length - 1]; 
        if ( idx === str.length ){
            results.push( composeSentence(str, currentPath) );
        }
        
        for ( let e of nodes.get(idx).values ){
            let nextIndex = idx + e.length;
            // clone if multiple paths
            let newPath = nodes.get(idx).values.length > 1 ? currentPath.slice(0) : currentPath;
            newPath.push(nextIndex);
            paths.push(newPath);
        }
    }
    return results;
}

function wordbreak_sentence(str) {
    var wb = new Map();
    for (let i=0; i <= str.length; ++i) {
        for (let j=i+1; j <= str.length; ++j) {
            let substr = str.substring(i, j);
            if ( dict.has(substr) && (wb.has(i) || i === 0 )) {
                if ( wb.has(j) === false ) wb.set(j,[]);
                wb.get(j).push( substr );
            }
        }
    }
    console.log('wb: ', wb);
    return wb.has(str.length) ? getSetences(str,wb) : [];
}

var l = process.argv[2];
console.log( l );
var s = wordbreak_sentence(l);

console.log ( s );
