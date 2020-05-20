function printList(l){
    for ( let i = 0; i < l.length; ++i) {
       console.log(l[i]);
    }   
}

function isWords(list) {
    for ( let i = 1; i < list.length; ++i) {
        let val = parseInt(list[i]);
        if ( val > 0 ) {
           return false;
        } 
    }
    return true;
}

function sortLogFile(entries) {   
   var sortedEntries = entries.slice(0);
   sortedEntries.sort( function(a,b) {
      var listA = a.split(' ');
      var listB = b.split(' ');

      var isWordsA = isWords(listA);
      var isWordsB = isWords(listB);

      var subListA = listA.splice(1).join(' ');
      var subListB = listB.splice(1).join(' ');
      if ( isWordsA && isWordsB ) {
          let compareVal = subListA.localeCompare(subListB);
          if ( compareVal == 0 ) return listA[0].localeCompare(listB[0]);
          return compareVal;   
      }
      else if ( isWordsA ) {
          return -1
      }
      else if ( isWordsB ) {
          return 1;
      }
      return -1;
   });
   return sortedEntries;
}

var l = [];
l.push('ij 22 33 44 55');
l.push('ik 22 33 44 55');
l.push('il hello world');
l.push('im good bye');
var sorted = sortLogFile(l);
printList(sorted );