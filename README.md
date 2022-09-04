# Sum of Concatenation
This problem was inspired by a post on the Computer Science Educators group on Facebook. I suspect that the problem originated on Code Dojo or a similar site.

![img.png](img.png)

## Problem Statement

![img_1.png](img_1.png)

## Original Solution

This was the solution created by the original poster. Note that this soultion is correct but 'not optimal'. Typically in computer science we will measure an optimal solution using two factors, the amount of memory used and the performance.

The other thing to consider when evaluating if a solution is optimal is how it performs on larger data sets. 

![img_2.png](img_2.png)

This solution requires a fixed amount of storage. Changing the size of the input array does not affect the storage used. Therefore this solution is optimal when considering storage.

The performance of this solution can be expressed as O(n<sup>2</sup>). Big O notation expresses the order of magnitude of the solution and tells us how the solution will scale with larger data sets. The performance of this solution will increase geometrically with the size of the input array. This solution contains nested loops over the inout array, the line ```sum += Number(`${num}${secondNum}`);``` is executed n<sup>2</sup> times where n is the length of the array.

## Optimizing the Solution

### False Optimizations

Many attempts were posted on the thread that attempted to optimize this solution by reducing the number of variables used or the number of lines of code. None of these optimizations make a significant difference because they still describe an O(n<sup>2</sup>) solution.

My background has led me to eschew conversions between text and numbers. This led me to observe that $a[i] • a[j]$ can be expressed mathematically as $a[i] * 10^{Log_{10}a[j] + 1} + a[j]$. This optimization might improve performance but it's not a meaningful optimization.

### A True Optimization

My observation about the mathematical replacement of the concatenation operation allowed me to observe that we could replace the solution with one that is close to O(n). If we exapand the series of the sample case using the notation above and simplify we can see that we do not need to use a nested loop to find the solution.

  $a[0] * 10^{Log_{10}a[0] + 1} + a[0] + a[0] * 10^{Log_{10}a[1] + 1} + a[1] + a[1] * 10^{Log_{10}a[0] + 1} + a[0] + a[1] * 10^{Log_{10}a[1] + 1} + a[1]$
  
Rewrite as: 

  $a[0] + a[1] + a[0] + a[1] + a[0] * 10^{Log_{10}a[0] + 1} +  a[1] * 10^{Log_{10}a[0] + 1} + a[0] * 10^{Log_{10}a[1] + 1} +  a[1] * 10^{Log_{10}a[1] + 1} $
  
Combine terms:

 $2 * (a[0] + a[1]) + (a[0] + a[1]) * 10^{Log_{10}a[0] + 1} +  (a[0] + a[1]) * 10^{Log_{10}a[1] + 1} $
 
What may not be obvious is that $(a[0] + a[1]) \equiv \sum a_n$ and the 2 is the length of the list of integers, therefore:

 $\text{len}(a) * \sum a_n + \sum a_n * 10^{Log_{10}a[0] + 1} +  \sum a_n * 10^{Log_{10}a[1] + 1} $
 
 Although this formula is greatly simplified from where we began, it still contains all of the terms of the array combined. The key to simplifying this is to realize that $Log_{10}a[i] + 1$ represents the length of the i<sup>th</sup> term. Our next optimization will be to create a frequency map of the number of digits so that map<sub>i</sub> = the number of list entries with i digits. Once we have created that map we can then rewrite the formula above such that it does not require nested loops:
 
  $\text{len}(a) * \sum a_n + \sum a_n * map_1 * 10^{1} + \sum a_n * map_2 * 10^{2} + ... + \sum a_n * map_l * 10^{l}$

or:

  $\text{len}(a) * \sum a_n + \sum {(\sum a_n * map_i * 10^{i})}$
  
### Pseudocoding a Solution

* Sum the elements of the list
* Create a frequency map of the number of digits in the terms of the list
* Compute the final sum by multiplying the sum of the items in the list by the count of the list and then adding the product $\sum a_n * map_i * 10^{i}$

### Final Performance

A note on the size of the frequency map. The size of the frequency map is limited by the maximum value that can be represented in the list. For standard data types this is a known value. If a type like BigInteger is used for the input data this list may need to accomodate significantly more terms, but will be exponentially smaller than the list of integers. This number of elements in the frequency map is not truly dependent on the number of items in the list.

We will need to loop through the list to compute the sum of the terms of the list. *O(n)*
We will also need to loop through the list to create the frequency map. *O(n)*
We will need to loop over the frequency map to compute the final summation. *O(1)*

*O(n) + O(n) + O(1) = O(n)*

## Demonstrated Solutions

The solutions provided in this repository are intended to provide more insight into this optimization. The simple solution is an AP CSA compliant version of the O(n<sup>2</sup>). I am getting warnings on how my String concatenation is being done and would like feedback on how to write that expression. The optimal solution is a close to AP CSA compliant solution. Replacing the HashMap with an ArrayList may actually be an improvement. Finally a Java 8 streams solution is shown. I believe that the streams solution is much more understandable code and I look forward to an update from the College Board to add streams into the curriculum.

# Wonderings

Some additional research could be done to support this project. I would like to know if there is a formal way to analyze the initial equation and simplify it more directly. I was working out the math instictively in my head and had to come back to actually wrote the equations above to demonstrate to myself that the solution was correct.

There must be some concept of the concatenation operation in this case that allows us to process the combination of the elements separately. Any references or reading would be appreciated.
