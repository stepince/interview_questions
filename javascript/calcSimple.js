
function processExprs(str) {
     var tokens = str.split(/([*/])/);
     var result = parseInt(tokens.shift());
     if ( result === null ) return null;
     while ( 0 < tokens.length ) {
          let operator = tokens.shift();
          let operand = parseInt(tokens.shift());
          if ( operand == null ) return null;
          if ( operator == '/' ) {
              result /= operand;
          }
          else if ( operator == '*' ) {
              result *= operand;
          }
     };
     return result;
}
function calc(str) {
    str = str.trim();
    while(str.match(/\d-\d/)) {
        str = str.replace(/(\d)-(\d)/g,"$1+-$2");
    }
    var tokens = str.split("+");
    var operands = [];
    if ( tokens.length > 0 && tokens[0] == '+') tokens.shift();
    if ( tokens.length == 0) {
       console.log('No tokens');
       return null;
    }
    for ( let i = 0 ;i < tokens.length; ++i) {
        let val = null;
        if ( tokens[i].indexOf('/') > -1 || tokens[i].indexOf('*') > -1 ) {
             val = processExprs(tokens[i]);
        }
        else {
             val = parseInt(tokens[i]);
        }
        if ( val === null ) {
            console.log('Invalid token:',val);
            return null;
        }
        operands.push(val);
    }
    var result = 0;
    for ( let i = 0 ;i < operands.length; ++i) {
         result += operands[i];
    }
    return result;
}

var sum = calc(process.argv[2]);
console.log ( 'sum: ', sum );