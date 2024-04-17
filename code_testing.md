# Testing
This markdown file documents the testing done to the program

### `int CalculateGCD(int a, int b)`

To test out GCD, 40 different sets of 2 numbers to test GCD on. Then, I ran through all the sets of numbers and compared the solution from a GCD calculator to my own programmed GCD

Getting it was done in python
```
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

Then, 2 large primes were used to calculate the GCD, with the expected output as 1, which also passed the test.
Then, equal numbers were used to calculate the GCD, with the expected output as that number, which also passed the test.

Testing was done in the GCDCalculator class file.
Fortunately, no errors were found with the code.
Only positive numbers were considered, as such, fibonacci numbers will be constrained to natural numbers.

