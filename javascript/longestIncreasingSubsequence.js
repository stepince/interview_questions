/*
LCS for input Sequences "ABCDGH" and "AEDFHR" is "ADH" of length 3.
LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4

node longestIncreasingSubsequence.js "ABCDGH" and "AEDFHR"
*/

function longestSubsequence( s1, s2 ) {
   if ( s1.length == 0 || s2.length == 0 ) return 0;

   if ( s1[0] == s2[0] ) {
       return 1 + longestSubsequence(s1.slice(1), s2.slice(1) );
   }
   return Math.max( longestSubsequence(s1.slice(1),s2), longestSubsequence(s1,s2.slice(1) ) );
}

var s1 = process.argv[2].split("");
console.log(s1);

var s2 = process.argv[3].split("");
console.log(s2);

console.log(longestSubsequence( s1, s2 ));

