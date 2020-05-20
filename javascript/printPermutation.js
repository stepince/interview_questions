function swap(l,a,b) {
   let t = l[a];
   l[a] = l[b]
   l[b] = t;
}

function printPermutation(l,index = 0){  
    if ( l == null || l.length == 0 ){
        return;
    }   
    else if ( index == l.length ){
        console.log( l.join("") );
        return;
    }
    else {
        for ( let i = index; i < l.length; ++i ){
            swap(l,index,i);
            printPermutation(l,index+1);
            swap(l,index,i);
        }
    }
}

var l = process.argv[2].split("");
console.log( l );
printPermutation(l);
