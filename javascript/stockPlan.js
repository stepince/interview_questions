function stockPlan( a ) {
  var s = [];
  var st = [];
  st.push(0);
  s[0] = 1;

  for (let i = 1; i < a.length; ++i){
     while ( (st.length > 0) && a[st[st.length-1]] <= a[i]) st.pop();

     s[i] = (st.length == 0)? (i + 1) : (i - st[st.length-1]);

     st.push(i);
  }
  return s;
}

var l = process.argv.slice(2);
var a = [];
for ( let i = 0; i < l.length; ++i ) {
   a.push(parseInt(l[i]));
}

console.log( a );
console.log(stockPlan(a));