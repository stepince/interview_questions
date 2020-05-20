/*
Shortest Common Supersequence

Input:   str1 = "geek",  str2 = "eke"
Output: "geeke"

Input:   str1 = "AGGTAB",  str2 = "GXTXAYB"
Output:  "AGXGTXAYB"

*/

function longestSubstring(str1, str2) {
   var mem = [];
   var start = 0, end = 0, max = 0;

   for ( let i = 0; i < str1.length + 1 ; ++i ) {
       mem[i] = [];
       for ( let j = 0; j < str2.length + 1; ++j ) {
           if ( i == 0 || j == 0 ) {
               mem[i][j] = 0;
           }
           else if ( str1.charAt(i-1) === str2.charAt(j-1) ) {
               mem[i][j] = mem[i-1][j-1] + 1;
               max = Math.max(max,mem[i][j]);; 
           }
           else {
                mem[i][j] = 0
           }
           if ( mem[i][j] === max ) {
               maxI = i;
               maxJ = j;
           }
       }
   }
   return str1.substring(  maxI - mem[maxI][maxJ], maxI );
}

function scs(str1, str2){
    var substr = longestSubstring(str1,str2);
    var idx1 = str1.indexOf(substr);
    var idx2 = str2.indexOf(substr);
    if ( idx1 === 0 && idx2 === 0 ) {
        return str1.length > str2.length ? str1 : str2;
    }
    else if ( idx1 === 0 && !str2.endsWith(substr) ) {
        return str1 + str2;
    }
    else if ( idx2 === 0 && !str1.endsWith(substr) ) {
        return str1 + str2;
    }
    else if ( idx1 === 0 ) {
        return str2 + str1.substring(idx1 + substr.length);
    }
    else if ( idx2 === 0 ) {
        return str1 + str2.substring(idx2 + substr.length);
    }
    else {
        // can also be str2 + str2 
        return str1 + str2;
    }
}

var s1 = process.argv[2];
console.log(s1);
var s2 = process.argv[3];
console.log(s2);
console.log( scs(s1,s2)  );

