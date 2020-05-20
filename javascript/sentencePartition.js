var dict =  new Set([ "i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "icecream", "man", "go", "mango"]);

function wordbreak_sentence(str, index = 0, prefix = "", results = []) {
    // other than the initial call, a recursive call will only make it here if the 
    // previous call was a word, now check if it is finished
    if ( str.length == index ) results.push(prefix.trim());

    for (let i=index+1; i <= str.length; ++i) {
        let substr = str.substring(index, i);
        
        if ( dict.has(substr) ) {
            wordbreak_sentence(str, i, prefix + substr + ' ', results); 
        }       
    }
    return results;
}

var l = process.argv[2];
console.log( l );
var s = wordbreak_sentence(l);

console.log ( s );
