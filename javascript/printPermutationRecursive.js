function printPermutation(s,prefix) {  
    if ( s == null || s.size == 0 ) {
        console.log( prefix );
        return;
    }   

    s.forEach( (ch) => {
        let s2 = new Set(s);
        s2.delete(ch);
        printPermutation(s2,prefix + ch);
    })
}

var l = process.argv[2].split("");
console.log( new Set(l) );
printPermutation(new Set(l),"");
