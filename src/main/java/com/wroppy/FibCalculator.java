package com.wroppy;

import java.util.ArrayList;

public class FibCalculator {
  public ArrayList<Integer> fibs;

  /**
   * Constructor sets up the 2 initial conditions for the fibonacci sequence f0 =
   * 0 f1 = 1
   */
  public FibCalculator() {
    fibs = new ArrayList<>();
    fibs.add(0);
    fibs.add(1);
  }

  /**
   * Given an integer, returns the fibonacci
   *
   * @param n the fibonacci index
   * @return the fibonacci number at the given index
   */
  public int fib(int n) {
    for (int i = this.fibs.size(); i <= n; i++) {
      this.fibs.add(this.fibs.get(i - 1) + this.fibs.get(i - 2));
    }
    return this.fibs.get(n);
  }

  /**
   * Recursively calculates the fibonacci number with the given index
   * 
   * @param n the fibonacci index
   * @return The fibonacci number at the given index
   */
  public int recursiveFib(int n) {
    if (n <= 1) {
      return n;
    }
    return this.recursiveFib(n - 1) + this.recursiveFib(n - 2);
  }

  public void testFib() {
    // Compares the time taken to calculate fib using recursion and memoisation
    // Done at the start so there is no values apart from the default 2 fib numbers
    // in the array
    int n = 50;
    long start, end;

    start = System.currentTimeMillis();
    this.fib(n);
    end = System.currentTimeMillis();

    System.out.println("Completed in " + (end - start));

    start = System.currentTimeMillis();
    this.recursiveFib(n);
    end = System.currentTimeMillis();

    System.out.println("Completed in " + (end - start));

    // Tests the fib method on set fib numbers
    int[] nums = { 0, 1, 3, 5, 10, 15, 20, 25, 40 };
    int[] solutions = { 0, 1, 2, 5, 55, 610, 6765, 75025, 102334155 };
    int fib, solution;
    for (int i = 0; i < nums.length; i++) {
      solution = solutions[i];
      n = nums[i];

      fib = this.fib(n);
      System.out.println(fib);

      // If there is an error, then print out the error and stop
      if (fib != solution) {
        System.err.println(
            String.format("Error occured at %d, expected %d got %d", n, solution, fib));
        return;
      } else {
      }
    }

    System.out.println("No error when calculating fib");
  }

  public void findLargestFib() {
    System.out.println(this.fib(46));
    System.out.println(this.fib(47));

    int index = 0;
    while (true) {
      if (this.fib(index) < 0) {
        System.out.println("Maximum found at " + index);
        return;
      }

      index++;
    }
  }
}
