# Testing
This markdown file documents the testing done to the program. There are multiple stages to the testing, firstly, writing and testing the base methods such as `fib(n)` and `gcd(a, b)` are high priority, as they determine the underlying output of the program. 

### `int calculateGCD(int a, int b)`

To test out GCD, 40 different sets of 2 numbers were used to test GCD. These numbers were passed through the GCD function such that the following equality was made. 

```
gcd(a, b) == gcd(b,a) && gcd(a,b) == solution.
```

Getting the random set of numbers was done in python
```python
import math
from random import randrange

As = []
Bs = []
solutions = []
for i in range(100):
    numA = randrange(1, 120)
    numB = randrange(1, 30)

    solution = math.gcd(numA, numB)

    if solution == 1: 
        if randrange(0, 10):
            continue

    As.append(str(numA))
    Bs.append(str(numB))
    solutions.append(str(solution))


print("{" + ", ".join(As) + "}")
print("{" + ", ".join(Bs) + "}")
print("{" + ", ".join(solutions) + "}")
```
Output:
```
{72, 108, 117, 37, 39, 102, 68, 53, 110, 87, 44, 118, 21, 80, 111, 36, 24, 21, 5, 12, 96, 22, 51, 54, 94, 30, 12, 14, 82, 64, 96, 16, 22, 24, 60, 54, 16, 2, 46, 54}
{18, 6, 10, 12, 15, 27, 20, 24, 11, 3, 14, 17, 24, 12, 27, 2, 16, 27, 7, 22, 27, 18, 17, 26, 8, 6, 4, 18, 24, 17, 8, 4, 16, 14, 25, 20, 18, 2, 28, 22}
{18, 6, 1, 1, 3, 3, 4, 1, 11, 3, 2, 1, 3, 4, 3, 2, 8, 3, 1, 2, 3, 2, 17, 2, 2, 6, 4, 2, 2, 1, 8, 4, 2, 2, 5, 2, 2, 2, 2, 2}
```

After testing for general use cases, 2 large primes were used to calculate the GCD, with the expected output as 1, which also passed the test.
Then, equal numbers were used to calculate the GCD, with the expected output as that number, which also passed the test.

Testing was done in the `GCDCalculator` class in the `void testGCD()` method.

Only positive numbers were considered, as such, Fibonacci numbers will be constrained to natural numbers, meaning that the minimum number that the user can choose to calculate the sum of all Fibonacci numbers will be 0.

Furthermore, testing was done to ensure the recursive function would still work for large primes to test for a long recursive function. Prime numbers `97505526` and `61205411` were used to test.

As all tests passed without error with output
```
Test Complete. Errors founds: 0
Test successful
Test successful
```


### `int fib(int n)`
Testing Fibonacci is similar to GCD, testing actual vs expected outputs. To test this, the class `FibCalculator` was used to perform all the actions spoken below.

#### Finding the Fibonacci upper bound
Java uses 2's complement as part of their integer system, hence, finding a maximum would be find the index `n` for `fib(n)` to be negative.
This was done in the `findLargestFib()` method, and was found to be at 47. To give the program some freedom, a maximum of 40 will be imposed for the user to request for.

```java
  public void findLargestFib() {
    int index = 0;

    while (true) {
      if (this.fib(index) < 0) {
        System.out.println("Maximum found at " + index + " at fib = " + this.fib(index));
        System.out.println("Previous fib number: " + this.fib(index - 1));
        return;
      }
      index++;
    }
  }
```

```
findLargestFib() output
Maximum found at 47 at fib = -1323752223
Previous fib number: 1836311903
```

#### Testing for expected vs actual output
To test this, a set of Fibonacci indexes and their corresponding Fibonacci number was used to compare the output from `int fib(int n)`. 
Numbers were chosen at random, but importantly, the lower and upper bounds {0, 40} were included.
Furthermore, the method of calculating the sum of Fibonacci numbers up to `n` used the property of `sum({fib(n), fib(n - 1), ..., fib(0)} = fib(n + 2) - 1`.
This meant that although the user was able to input 40 as their number, the maximum Fibonacci index was 42. Hence why it was included in the set of tests.

Testing occurred in the `void testFib()` method and printed the output of the Fibonacci number, and printed if an error had happened on any one of the numbers.

Code of output inside the for loop:
```java
fib = this.fib(n);
System.out.println(fib);
// If there is an error, then print out the error and stop
if (fib != solution) {
	System.err.println(
		String.format("Error occured at %d, expected %d got %d", n, solution, fib));
return;
}
```
Output: 
```
0
1
2
5
55
610
6765
75025
102334155
267914296
No error when calculating fib
```

#### Testing for efficiency
To test for efficiency, I timed the time it took for the recursive definition of Fibonacci, and compared it against the dynamic programming method, which was used for the previous Fibonacci tests.

The testing method was to calculate the highest possible Fibonacci number allowable by the constraints set previously `(n = 41)` and was timed to calculate how long it would take. 
Furthermore, it repeated the calculation twice to see whether the fastest calculation time of the dynamic programming method (as the value would already be calculated and stored in an array) was faster than the recursive definition.

This was done in the `void timeFib()` method.
Output:
```
Time taken for fib: 11800 ns
Time taken for recursive fib: 1241747100 ns
Time taken for fib: 5400 ns
Time taken for recursive fib: 1304681900 ns
```

As seen from the output, the dynamic programming method is much more efficient in timing than the recursive method.

### Putting it all together
Putting it all together required the following steps.
1) Show menu
2) Get input `a` from user
3) Get input `b` from user
4) Calculate `fib(n + 1) - 1` for `a` and `b`
5) Compute and display `gcd(a, b)` of the 2 outputs above
6) Go back to menu

Note that the function `fib(n+2)-1` was changed to `fib(n+1)-1` as the fib sequence starts from 0, but the first `n` fib numbers starts from 1.
All testing is still valid after this change.
#### Testing `getNum(String numLabel)`
The possible values for user input can be:
1) Not a number
2) Number is less than 0
3) Number is greater than 40
If these happen, an error message should be displayed to the user and told to re-input a number.
Input and output:
```
Please input a number between 0 and 40 for number A: a
Please input a number between 0 and 40 for number A: b
Please input a number between 0 and 40 for number A: wndawodnoiaw diopawnido awiopd aw
Please input a number between 0 and 40 for number A: -10
Please input a number between 0 and 40 for number A: 50 
Please input a number between 0 and 40 for number A: 40
```

Edge Case: Input 0
```
Please input a number between 0 and 40 for number A: 0
(Program ended)
```

#### Testing all possible combinations of `a` and `b`
To test the program works with all combinations, a nested for loop is used to test all numbers from 0 to 40 inclusive. This is done to make sure that the GCD function didn't encounter any stack overflow errors when computing GCD due to long recursion chains.
This was done in the `void testAllPossibleCases()` method.
```java
public void testAllPossibleCases() {
    int i, j;
    for (i = 0; i <= 40; i++) {
      for (j = 0; j <= 40; j++) {
        try {
          int sumA = this.fibCalculator.fib(i + 1) - 1;
          int sumB = this.fibCalculator.fib(j + 1) - 1;

          int gcd = this.gcdCalculator.calculateGCD(sumA, sumB);
        } catch (Exception e) {
          System.err.printf("Error at %d %d, GCD=%d\n", i, j);
          return;
        }
      }
    }
    System.out.println("All test cases passed");
  }
```

Output: 
```
All test cases passed
```

#### Testing the Fibonacci sum and GCD together
To test this, expected and actual results were compared, using the numbers:
1) 0, 0
2) 1, 0
3) 2, 0
4) 1, 2
5) 1, 3
6) 2, 3
7) 2, 2
8) 5, 10
9) 10, 20
10) 20, 30
11) 30, 40
12) 38, 40
13) 39, 40

Code:
```java
// Tests the program on some test cases
  public void testProgram() {
    // Edge tests
    this.calculateFibGCD(0, 0); // Expected: 0
    this.calculateFibGCD(0, 1); // Expected: 0
    this.calculateFibGCD(0, 2); // Expected: 1
    this.calculateFibGCD(1, 2); // Expected: 1
    this.calculateFibGCD(1, 3); // Expected: 2
    this.calculateFibGCD(3, 2); // Expected: 1
    this.calculateFibGCD(2, 2); // Expected: 1
    
    this.calculateFibGCD(5, 10); // Expected: 1
    this.calculateFibGCD(10, 20); // Expected: 11
    this.calculateFibGCD(20, 30); // Expected: 11
    this.calculateFibGCD(30, 40); // Expected: 132
    
    this.calculateFibGCD(38, 40); // Expected: 6765
    this.calculateFibGCD(39, 40); // Expected: 2
  }
```
Output:
```java
GCD of the sum of fibonacci numbers up to 0 and 0 is 0
GCD of the sum of fibonacci numbers up to 0 and 1 is 0
GCD of the sum of fibonacci numbers up to 0 and 2 is 1
GCD of the sum of fibonacci numbers up to 1 and 2 is 1
GCD of the sum of fibonacci numbers up to 1 and 3 is 2
GCD of the sum of fibonacci numbers up to 3 and 2 is 1
GCD of the sum of fibonacci numbers up to 2 and 2 is 1
GCD of the sum of fibonacci numbers up to 5 and 10 is 1
GCD of the sum of fibonacci numbers up to 10 and 20 is 11
GCD of the sum of fibonacci numbers up to 20 and 30 is 11
GCD of the sum of fibonacci numbers up to 30 and 40 is 132
GCD of the sum of fibonacci numbers up to 38 and 40 is 6765
GCD of the sum of fibonacci numbers up to 39 and 40 is 2
```
