/*

Problem statement: Consider a row of n coins of values v1 . . . vn, where n is even. 
We play a game against an opponent by alternating turns. 
In each turn, a player selects either the first or last coin from the row, 
removes it from the row permanently, and receives the value of the coin. 
Determine the maximum possible amount of money we can definitely win if we move first.

Note: The opponent is as clever as the user.
Awesome each user takes the max between the end
5, 3, 7, 10 : The user collects maximum value as 15(10 + 5)
*/

if (!Array.prototype.first) {
  Array.prototype.first = function() {
    return this[0];
  }
}

if (!Array.prototype.last) {
  Array.prototype.last = function() {
    return this[this.length-1];
  }
}

if (!Array.prototype.sliceFirst) {
  Array.prototype.sliceFirst = function() {
    return this.slice(1);
  }
}

if (!Array.prototype.sliceLast) {
  Array.prototype.sliceLast = function() {
    return this.slice(0,this.length-1);
  }
}

function maxCoinValue(list, sum=0 ){
    if ( list.length <= 1 ) return sum;

    // first user
    var user1firstCompare = maxCoinValue( list.sliceFirst(), sum + list.first() );
    var user1lastCompare = maxCoinValue( list.sliceLast(), sum + list.last() );
   
    // simulate second user
    if ( user1firstCompare > user1lastCompare ) {
        sum += list.first();
        var newList = list.slice(1);
        var user2firstCompare = maxCoinValue( newList.sliceFirst(), sum + newList.first() );
        var user2lastCompare = maxCoinValue( newList.sliceLast(), sum + newList.last() );
    }
    else {
        sum += list.last();
        var newList = list.slice(0,list.length-1);
        var user2firstCompare = maxCoinValue( newList.sliceFirst(), sum + newList.first() );
        var user2lastCompare = maxCoinValue( newList.sliceLast(), sum + newList.last() );
    }

    // note: don't add second user's coin selection to the sum
    return ( user2firstCompare > user2lastCompare ) 
           ? maxCoinValue( newList.sliceFirst(), sum )
           : maxCoinValue( newList.sliceLast(), sum );
}

var l = process.argv.slice(2).map( x=> parseInt(x) );
console.log( l );

console.log( maxCoinValue(l)  );
