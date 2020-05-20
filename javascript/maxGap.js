function maxGap(a) {
   
   if ( a == null || a.length == 0 ) return null;
   let minima = [];
   let maxima = [];
   minima[0] = parseInt(a[0]);
   maxima[a.length-1] = parseInt(a[a.length-1]);

   for ( let i = 1; i < a.length;++i) {
       minima.push( Math.min(minima[minima.length-1], parseInt(a[i])));
   }
 
   for ( let i = a.length -2; i >=0 ; --i) {
       maxima[i] = ( Math.max(maxima[i+1], parseInt(a[i])));
   }

   let i = 0, j = 0, maxGap = -1;
   while (j < a.length && i < a.length){
        if (minima[i] < maxima[j]){
            maxGap = Math.max(maxGap, j-i);
            j = j + 1;
        }
        else {
            i = i+1;
        }
    }
   return maxGap ;
}


var val = maxGap(process.argv.slice(2) );
console.log( 'maxGap: ', val);
