

 
function combinatonPrefixString(set, prefix, k){
    var comb = [];
   
    if ( k == 0 ) {
        comb.push(prefix);
        return comb;
    }
    for (let i = 0; i < set.length; ++i) {
        let newPrefix = prefix + set[i];
        let newComb = combinatonPrefixString(set, newPrefix, k - 1);
        comb = comb.concat(newComb);
    }
    return comb;
}

function combinatonString(set, k){
    return combinatonPrefixString(set, "", k);
}


function isValidBinary(str, k) {
   var l = combinatonString(['0','1'], k);
   for ( let i = 0; i < l.length; ++i ) {
       if ( str.indexOf(l[i]) == -1) {
           return false;
       }    
   }
   return true;
}
console.log ( isValidBinary(process.argv[2],2) );