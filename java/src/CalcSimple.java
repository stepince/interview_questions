import java.util.*;
import java.util.stream.Collectors;

/*
notes:
   algorithm
   parse str into tokens
   convert "-" to +"+-" , the "-" is now only used in the operand
   makes it easier to tokenize based on "/*+"
   use one stack (tokens)
   first stack "tokens" is used process all multiplication/division operations and add to the addStack
 */
public class CalcSimple {
    enum TokenType {operator,operand}
    static class Token {
        final static Set<?> OPERATORS = Set.of("/","+","*","-");
        final TokenType type;
        char operator;
        Float operand;
        Token( Float operand ){
             this.operand = operand;
             this.type = TokenType.operand;
        }
        Token( char operator ){
            this.operator = operator;
            this.type = TokenType.operator;
        }
        static Token parseToken(String str){
            return  OPERATORS.contains(str) ? new Token(str.charAt(0)) : new Token(Float.parseFloat(str));
        }
    }

    private static List<Token> tokenize ( String expr ){
        expr = expr.replace(" ","");
        // convert all "-" operator to "+-" operations,"-" can be used as an operand and operator, eliminate the operator
        while( expr.matches(".*\\d-\\d.*") ) {
            expr = expr.replaceAll("(\\d)-(\\d)","$1+-$2");
        }
        // use look ahead and look behind to return back the delimiters
        String[] tokenList = expr.split("(?=[/*+])|(?<=[/*+])");
        return Arrays.stream(tokenList).map(Token::parseToken).collect(Collectors.toList());
    }

    public static Float calc(String expr){
        Stack<Token> tokens =  new Stack<>();
        tokens.addAll(tokenize(expr));
        if ( tokens.size() == 0 ) return null;

        Float curr = 0f;
        // "+" operation takes two items off of stack, multiplication takes 3 items and results back on stack
        while( !tokens.isEmpty() ){
            Token operandToken1 = tokens.pop();
            if ( tokens.isEmpty()) {
                curr += operandToken1.operand;
                break;
            }
            Token token = tokens.pop();
            if ( token.operator == '/' ){
                if ( tokens.isEmpty()) return null;
                Token operandToken2 = tokens.pop();
                tokens.push( new Token(operandToken2.operand / operandToken1.operand) );
            }
            else if ( token.operator == '*' ){
                if ( tokens.isEmpty()) return null;
                Token operandToken2 = tokens.pop();
                tokens.push( new Token(operandToken2.operand * operandToken1.operand) );
            }
            else if ( token.operator == '+' ){
                curr += operandToken1.operand;
            }
            else {
                // error
                return null;
            }
        }
        return curr;
    }

    public static void main(String[] args) {
        //System.out.println( calc(args[0]));
        System.out.println(calc("5+7*7+5"));
    }
}