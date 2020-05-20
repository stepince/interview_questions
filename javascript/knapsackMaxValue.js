/*
Given weights and values of n items,
put these items in a knapsack of capacity W to get the maximum
total value in the knapsack. In other words, 
given two integer arrays val[0..n-1] and wt[0..n-1] 
which represent values and weights associated with n items respectively. 
Also given an integer W which represents knapsack capacity, 
find out the maximum value subset of val[] such that sum of the weights 
of this subset is smaller than or equal to W. 
You cannot break an item, either pick the complete item, or don’t pick it (0-1 property)

node knapsackMaxValue.js "60 100 120" "10 20 30" 50
answer: 120
*/

function maxValue(values, weights, weight, sum = 0, index = 0){
    if ( index === values.length ) return sum;

    if ( weights[index] > weight ) {
        return maxValue(values,weights,weight, sum, index+1);
    }

    var compareInclude = maxValue(values, weights, weight-weights[index], sum+values[index], index+1);
    var compareExclude = maxValue(values, weights, weight, sum, index+1);
    return Math.max(compareInclude, compareExclude);
}

var values = process.argv[2].split(" ").map( (x) => parseInt(x) );
var weights = process.argv[3].split(" ").map( (x) => parseInt(x) );
var weight = parseInt( process.argv[4] )
console.log( values );

console.log( weights );

console.log( weight );
var max = maxValue(values, weights, weight);


console.log( max );