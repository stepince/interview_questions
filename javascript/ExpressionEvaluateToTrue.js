/*
Count the number of ways we can parenthesize the expression so 
that the value of expression evaluates to true.

Let the input be in form of two arrays one contains the symbols (T and F) in order 
and other contains operators (&, | and ^}

*/

function xor(operand){
   return ^operand;
}
function andOp(operand1, operand2){
   return operand1 && operand2
}
function orOp(operand1, operand2){
   return operand1 || operand2;
}

functin evaluate ( operand1, operator, operand2 ) {
    if 
}
function num_ways(operands, operators, count=0) {
    if ( operands.length === 2 ) {
        if ( operator == '&'
    }
    
}

var operands = process.argv[2].split(" ").map( (x)=> x==="T" )
var operators = process.argv[3].split(" ");
console.log(operands);
console.log(operators);
console.log( num_ways(operands,operators) );
