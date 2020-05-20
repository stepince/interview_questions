
function MinStack() {
    this.stack = [];
    this.minStack = [];
    this.push = function (obj) {
        this.stack.push(obj);
        if ( this.minStack.length == 0 ) {
            this.minStack[0] = obj;
        }
        else if ( this.minStack[this.minStack.length -1] >= obj  ) {
            this.minStack.push(obj);
        }
    }

    this.getMin = function () {
        if ( this.minStack.length == 0 ) {
            return null;
        }
        return this.minStack[this.minStack.length -1];
    }

    this.pop = function() {
        var item = this.stack.pop();
        if ( item == this.minStack[this.minStack.length-1] ) {
            this.minStack.pop();
        }
        return item;
    }

    this.getStack = function(){
        return this.stack;
    }
}


var st = new MinStack();


st.push( 5 );
st.push( 4 );
st.push( 3 );
st.push( 2 );
st.push( 1 );
st.push( 1 );
console.log( st.getStack() );

st.pop();

console.log( st.getStack() );

console.log( st.getMin() );
st.pop();

console.log( st.getMin() );
