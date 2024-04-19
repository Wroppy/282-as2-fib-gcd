package com.wroppy;

public class GCDCalculator {
  /**
   * Calculates the greatest common divisor using recursion and the euclidean
   * algorithm
   *
   * @param a - a number
   * @param b - a number
   * @return The greatest common divisor of numbers a and b
   */
  public int calculateGCD(int a, int b) {
    // Ensures that GCD(m, n), m > n
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
      String output = String.format("gcd(%d, %d) = %d", m, n, m);
      // System.out.println(output);
      return m;
    }

    // gcd(a, b) = gcd(m, r) where a = bq + r
    // Prints output code
    int r = m % n;
    String output = String.format("%d = %d * q + %d, now finding gcd(%d, %d)", m,
    n, r, n, r);
    System.out.println(output);
    return this.calculateGCD(n, r);
  }

  /**
   * This is used to test the input and output of the GCD method using
   * pre-computed values and
   * comparing it with the GCD method
   */
  public void testGCD() {
    // First set of a values
    int[] As = {
        72, 108, 117, 37, 39, 102, 68, 53, 110, 87, 44, 118, 21, 80, 111, 36, 24, 21, 5, 12, 96, 22,
        51, 54, 94, 30, 12, 14, 82, 64, 96, 16, 22, 24, 60, 54, 16, 2, 46, 54
    };
    // Second set of b values
    int[] Bs = {
        18, 6, 10, 12, 15, 27, 20, 24, 11, 3, 14, 17, 24, 12, 27, 2, 16, 27, 7, 22, 27, 18, 17, 26, 8,
        6, 4, 18, 24, 17, 8, 4, 16, 14, 25, 20, 18, 2, 28, 22
    };
    // Solutions
    int[] solutions = {
        18, 6, 1, 1, 3, 3, 4, 1, 11, 3, 2, 1, 3, 4, 3, 2, 8, 3, 1, 2, 3, 2, 17, 2, 2, 6, 4, 2, 2, 1,
        8, 4, 2, 2, 5, 2, 2, 2, 2, 2
    };

    int a, b, gcd1, gcd2, solution;
    int errors = 0;
    for (int i = 0; i < As.length; i++) {
      a = As[i];
      b = Bs[i];
      solution = solutions[i];

      // Gets gcd of a and b, testing it in the reverse order as well
      gcd1 = this.calculateGCD(a, b);
      gcd2 = this.calculateGCD(b, a);

      if (!(gcd1 == gcd2 && gcd1 == solution)) {
        System.err.println(String.format("Error at gcd(%d, %d)", a, b));
        System.err.println(String.format("Got GCD = %d and %d, expected %d", gcd1, gcd2, solution));
        errors++;
      }
    }

    System.out.println("Test Complete. Errors founds: " + String.valueOf(errors));

    this.largePrime();
    this.equalNumber();
  }

  /***
   * Used to test the calculateGCD method on 2 large primes
   * 
   */
  public void largePrime() {
    int primeA = 97505526;
    int primeB = 61205411;

    int gcd = this.calculateGCD(primeA, primeB);

    if (!(gcd == 1)) {
      System.err.println(String.format("Error at gcd(%d, %d)", primeA, primeB));
      System.err.println(String.format("Got GCD = %d, expected %d", gcd, 1));
      return;
    }

    System.out.println("Test successful");
  }

  /***
   * Used to test the calculateGCD method on 2 equal numbers
   * 
   */
  public void equalNumber() {
    int num = 2318651;
    int gcd = this.calculateGCD(num, num);

    if (!(gcd == num)) {
      System.err.println(String.format("Error at gcd(%d, %d)", num, num));
      System.err.println(String.format("Got GCD = %d, expected %d", gcd, num));
      return;
    }

    System.out.println("Test successful");
  }
}
