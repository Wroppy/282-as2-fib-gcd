# Fibonacci GCD Calculator

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

