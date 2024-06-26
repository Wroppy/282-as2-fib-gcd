# Fibonacci GCD Calculator

Git Hub Link: https://github.com/Wroppy/282-as2-fib-gcd

This program takes 2 numeric inputs `A` and `B` and calculates the greatest common divisor (GCD) of the sum of the first `A` Fibonacci numbers and the sum of the **first** `B` Fibonacci numbers. 

##### Input:
Two positive integers A and B. 
##### Output:
The GCD of the sum of the **first** A Fibonacci numbers and the sum of the **first** B Fibonacci numbers

#### Example:
##### Input: 
`A` = 10
`B` = 20

##### Output
Sum of the first `10` Fibonacci numbers
0 + 1 + 1 + 2 + 3 + 5 + 8 + 13 + 21 + 34 = 88
Sum of the first `20` Fibonacci numbers
0 + 1 + 1 + 2 + 3 + 5 + 8 + 13 + 21 + 34 + ... + 4181 = 10945

GCD(10945, 88) = 11
## Constraints
Inputs `a` and `b` must be between in the interval of \[0, 40\].

## Reasoning

#### Calculating the sum of Fibonacci numbers up to `n`
This program uses the Fibonacci property stating that:
```
f(0) + f(1) + ... + f(n) = f(n + 2) - 1
```
Where `f(n)` is the Fibonacci number at index `n`.

***
Note that the program states:

>the sum of the ***first*** `n` Fibonacci numbers

This means that although `fib(0)=0`, if the user requests for the ***first*** `n` Fibonacci numbers, the initial condition `fib(0)=0` would actually start at **1**. 

This is changed in the program to reflect this, as the formula is changed to reflect the change in the starting index.
```
f(0) + f(1) + ... + f(n) = f(n + 1) - 1
```
***

To calculate `f(n)`, a dynamic programming method was used where the first 2 initial conditions of the Fibonacci sequence were initially in an array, `fibs = [0, 1]`. Then, to calculate the next value, would be `fibs(length(fibs)) = fib(length(fibs - 1)), fib(length(fibs) - 2)`. Assuming the array is 0 indexed.
To calculate up to `n` in a `for` statement, this would be:
```java
  public int fib(int n) {
    for (int i = this.fibs.size(); i <= n; i++) {
      this.fibs.add(this.fibs.get(i - 1) + this.fibs.get(i - 2));
    }
    return this.fibs.get(n);
  }
```
in Java.

This also removes the need to compute Fibonacci numbers multiple times, which the recursive function does.
Memorization is also used to help compute Fibonacci numbers faster, as the `this.fibs` variable is shared throughout the class and can be used whenever `int fib(int n)` is called. Furthermore, this means that if the user wants to select a Fibonacci number that has already been computed previously, it doesn't have to run through the `for` loop and instead has `O(1)` time complexity.

#### Calculating GCD
This program uses the recursive definition of the Euclidean algorithm, stating that:
```
GCD(a, b) = GCD(b, r)
where a = bq + r, q ∈ ℤ and a > b
```
Terminating and returning when `r=0`, or `a=b`.

This is done by:
1) Check that `a != b`. If `a=b` return `a`
2) Set the larger number to variable `m`, set the smaller number to variable `n`.
3) Compute `r = m % n`
4) if `r=0` return `n`.
5) Set `m=n`, `n=r`
6) Repeat steps 3-4 until `r=0`
Recursively, this can be translated to:

```java
  public int calculateGCD(int a, int b) {
    int m, n;
    if (a > b) {
      m = a;
      n = b;
    } else {
      m = b;
      n = a;
    }

    // Base case as remainder is 0
    if (n == 0) {
      return m;
    }

    int r = m % n;
    return this.calculateGCD(n, r);
  }
```

The program then combines the GCD and the Fibonacci methods to calculate the GCD of the sum of the first `a` and `b` numbers.
# Running the program
This calculator is programmed in Java and uses Apache Maven as its build tool
#### Dependencies
1) Java version 17
2) Apache Maven 3.9.6

#### Compilation
Compiling the program requires the 2 listed dependencies above. After extracting the project folder `softeng_282_as2_fib_gcd`, `cd` into the project folder and use the terminal command:

```
mvn clean compile exec:java@run
```

The program will start running in the terminal after the command.

#### Example Output
Input:
`A` = 10
`B` = 20

Output:
```
Welcome! This program that takes 2 numbers (a) and (b) and computes the greatest common
divisor of the FIRST A and B fibonacci numbers.
Please input a number between 0 and 40 for number A: 10
Please input a number between 0 and 40 for number B: 20
10945 = 88 * q + 33, now finding gcd(88, 33)
88 = 33 * q + 22, now finding gcd(33, 22)
33 = 22 * q + 11, now finding gcd(22, 11)
22 = 11 * q + 0, now finding gcd(11, 0)
gcd(11, 0) = 11
GCD of the sum of fibonacci numbers up to 10 and 20 is 11
Would you like to run the program again? (y/n): n
```


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
This meant that although the user was able to input 40 as a number, the maximum Fibonacci index was 42. Hence why it was included in the set of tests.

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

#### Testing the final program 
To test the final program, these test cases were checked to ensure validity of the user inputs:
1) Number `a` is negative
2) Number `a` is over 40
3) Number `b` is not a number
Output:
```java
Welcome! This program that takes 2 numbers (a) and (b) and computes the greatest common
divisor of the fibonacci numbers at (a) and (b)
Please input a number between 0 and 40 for number A: -5
Number out of bounds
Please input a number between 0 and 40 for number A: 50
Number out of bounds
Please input a number between 0 and 40 for number A: 10
Please input a number between 0 and 40 for number B: abcd
Please input a number between 0 and 40 for number B: 30
GCD of the sum of fibonacci numbers up to 10 and 30 is 44
Would you like to run the program again? (y/n):
```

To test whether the program outputs the correct GCD,  2 test cases were used.
1) `a` = 10, `b` = 20
2) `a` = 38, `b` = 40
Output:
```java
Welcome! This program that takes 2 numbers (a) and (b) and computes the greatest common
divisor of the fibonacci numbers at (a) and (b)
Please input a number between 0 and 40 for number A: 10
Please input a number between 0 and 40 for number B: 20
10945 = 88 * q + 33, now finding gcd(88, 33)
88 = 33 * q + 22, now finding gcd(33, 22)
33 = 22 * q + 11, now finding gcd(22, 11)
22 = 11 * q + 0, now finding gcd(11, 0)
GCD of the sum of fibonacci numbers up to 10 and 20 is 11
Would you like to run the program again? (y/n): y
Welcome! This program that takes 2 numbers (a) and (b) and computes the greatest common
divisor of the fibonacci numbers at (a) and (b)
Please input a number between 0 and 40 for number A: 38
Please input a number between 0 and 40 for number B: 40
165580140 = 63245985 * q + 39088170, now finding gcd(63245985, 39088170)
63245985 = 39088170 * q + 24157815, now finding gcd(39088170, 24157815)
39088170 = 24157815 * q + 14930355, now finding gcd(24157815, 14930355)
24157815 = 14930355 * q + 9227460, now finding gcd(14930355, 9227460)
14930355 = 9227460 * q + 5702895, now finding gcd(9227460, 5702895)
9227460 = 5702895 * q + 3524565, now finding gcd(5702895, 3524565)
5702895 = 3524565 * q + 2178330, now finding gcd(3524565, 2178330)
3524565 = 2178330 * q + 1346235, now finding gcd(2178330, 1346235)
2178330 = 1346235 * q + 832095, now finding gcd(1346235, 832095)
1346235 = 832095 * q + 514140, now finding gcd(832095, 514140)
832095 = 514140 * q + 317955, now finding gcd(514140, 317955)
514140 = 317955 * q + 196185, now finding gcd(317955, 196185)
317955 = 196185 * q + 121770, now finding gcd(196185, 121770)
196185 = 121770 * q + 74415, now finding gcd(121770, 74415)
121770 = 74415 * q + 47355, now finding gcd(74415, 47355)
74415 = 47355 * q + 27060, now finding gcd(47355, 27060)
47355 = 27060 * q + 20295, now finding gcd(27060, 20295)
27060 = 20295 * q + 6765, now finding gcd(20295, 6765)
20295 = 6765 * q + 0, now finding gcd(6765, 0)
GCD of the sum of fibonacci numbers up to 38 and 40 is 6765
Would you like to run the program again? (y/n): n
```

The end result stated that the GCD of the sum of Fibonacci numbers `10` and `20` is `11`, which was the expected output, and `38` and `40` is `6765`.
