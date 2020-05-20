function parseNum(num) {
   let str = "";
   for ( let i = 0; i < num.length; ++i) {
       str += num[i];
   }
   return parseInt( str );
}

function topStack(st) {
   if ( st.length == 0 ) return null;
   return st[st.length-1];
}

function isOperator(ch){
   return ch == '*' || ch == '/' || ch == '+' || ch == '-';
}

function calcStack(st) {
    var sum = 0;
    st = st.reverse();
    while ( st.length > 0 ) {
        let operand1 = st.pop();
        let operator = st.pop();
        let operand2 = st.pop();
        if ( !isOperator(operator) ) {
            console.log('invalid token: ',operator);
            break;
        }
        if ( operator == '*' ) {
            st.push( operand1 * operand2 );
        }
        else if ( operator == '/' ) {
            st.push( operand1 / operand2 );
        }
        else if ( operator == '+' || operator == '-') {
            st.push ( operand2 );
            stAdd.push( operand1 );
            stAdd.push( operator );
        }
        else {
            console.log('invalid token: ',operator);
            break;
        }

        if ( st.length == 1 ) {
           stAdd.push(st.pop()); 
        }
    }

    if ( stAdd.length > 0 ) {
        sum = stAdd.pop();
    }
    while ( stAdd.length > 0 ) {
        let operator = stAdd.pop();
        let operand = stAdd.pop();
        if ( !isOperator(operator) ) {
            console.log('invalid token: ',operator);
            break;
        }
        if ( operator == '+' ) {
            sum += operand;
        }
        else if ( operator == '-' ) {
            sum -= operand;
        }
    }
    return sum;    
}

function calc(str) {
    str = str.trim();
    var st = [];
    var stAdd = [];
    var num = [];
    for( let i = 0; i < str.length; ++i) {
        let ch = str.charAt(i);
     
        if ( ch == ' ' || ch == 't') {
            continue;
        }
        if ( /\d/.test(ch) && i == str.length -1 ) {
            num.push(ch);
            let n = parseNum(num);
            if( n === null ) {
                console.log('invalid null num');
                break;
            }
            st.push(n);
            continue;
        }
        if ( /\d/.test(ch) ) {
            num.push(ch);
            continue;
        }
        if ( ch == '(' ) {
            st.push(ch);
        }
        else if ( ch == ')' ) {
            let lastIndexOf = st.lastIndexOf('(');
            let parenSt = st.slice(lastIndexOf+1);
            var val = calc(parenSt);
            st = st.slice(0,lastIndexOf);
            st.push(val);
        }
        else if ( ch == '-' && i == 0 && num.length == 0 ) {
            num.push(ch);
        }
        else if ( ch == '-' && num.length == 0 && isOperator(topStack(st)) ) {
            num.push(ch);
        }   
        else if ( isOperator(ch) && num.length > 0 ) {
            let n = parseNum(num);
            if( n === null ) {
                console.log('invalid null num');
                break;
            }
            st.push(n);
            num = [];
            st.push(ch);
        }
        else {
            console.log('invalid: ',ch);
            break;
        }
    }
    return calcStack(st);
}

var sum = calc(process.argv[2]);
console.log ( 'sum: ', sum );