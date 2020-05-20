var dict =  new Set([ "i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "icecream", "man", "go", "mango"]);

function wordbreak_sentence(str) {
    var mem = {};
    for (let i=0; i <= str.length; ++i) {
        for (let j=i+1; j <= str.length; ++j) {
            let substr = str.substring(i, j);
            if ( dict.has(substr) && (mem[i] !== undefined || i === 0 )) {
                if ( mem[j] === undefined ) mem[j] = [];
                mem[j].push( substr );
            }
        }
    }
    return mem;
}

var l = process.argv[2];
console.log( l );
var s = wordbreak_sentence(l);

console.log ( s );