

function editDist(str1, str2, count = 0) {

    console.log('strings: ', str1, str2, count);
    if ( str1 === str2 ) return count;

    if ( str1 == null ) str1 = "";
    if ( str2 == null ) str2 = "";

    // delete
    if ( str1.length > str2.length ) {
       return editDist(str1.substring(0, str2.length), str2,  str1.length - str2.length );
    }

    // insert
    if ( str2.length > str1.length ) {
       return editDist(str1, str2.substring(0, str1.length),  str2.length - str1.length );
    }
    
    // replace
    for ( let i = 0; i < str1.length; ++i ) {
        if ( str1.charAt(i) !== str2.charAt(i) ) ++count;
    }
    return count;
}

var str1 = process.argv[2];
var str2 = process.argv[3];
var count = editDist( str1,str2);

console.log ( count );