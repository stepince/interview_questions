/*

Given n dice each with m faces, numbered from 1 to m, 
find the number of ways to get sum X. X is the summation of values 
on each face when all the dice are thrown.


FIXME: this is not corre
*/

function getKey(list){
   var newList = list.slice(0);
   newList.sort();
   let key = "";
   for ( let k of newList ) {
      key += ':' + k;
   }
   return key;
}

function diceThrow(n,faces,sum, list=[], mem={}) {

   if ( sum === 0 && list.length == n ) {
       let key = getKey(list);
       if ( mem[key] === undefined ) {
           console.log(list);
           mem[key] = true;
           return 1;
       }
       else {
           return 0;
       }
   }
    
   if ( sum <= 0 ) return 0;
   var count = 0;
   for ( let i = 0; i < n; ++i ) {
       for ( let j = 1; j <= faces; ++j ) {
          list.push(j);
          count += diceThrow(n,faces,sum - j,list,mem);
          list.pop();
       }
   }
   return count;
}

var num = parseInt(process.argv[2]);

var faces = parseInt(process.argv[3]);

var sum = parseInt(process.argv[4]);

console.log(num);
console.log(faces);
console.log(sum);
console.log(diceThrow(num,faces,sum) );