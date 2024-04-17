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
    int n;
    // Tests the fib method on set fib numbers
    int[] nums = { 0, 1, 3, 5, 10, 15, 20, 25, 40, 42 };
    int[] solutions = { 0, 1, 2, 5, 55, 610, 6765, 75025, 102334155, 267914296 };
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

  public void timeFib() {
    int n = 42;
    long start, end;

    start = System.nanoTime();
    this.fib(n);
    end = System.nanoTime();
    System.out.println("Time taken for fib: " + (end - start) + " ns");

    start = System.nanoTime();
    this.recursiveFib(n);
    end = System.nanoTime();
    System.out.println("Time taken for recursive fib: " + (end - start) + " ns");

    // Redid test
    start = System.nanoTime();
    this.fib(n);
    end = System.nanoTime();
    System.out.println("Time taken for fib: " + (end - start) + " ns");

    start = System.nanoTime();
    this.recursiveFib(n);
    end = System.nanoTime();
    System.out.println("Time taken for recursive fib: " + (end - start) + " ns");

  }
}
