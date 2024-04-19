package com.wroppy;

import java.util.Scanner;

public class FibGCD {
  private int max = 40;
  private int min = 0;
  private FibCalculator fibCalculator;
  private GCDCalculator gcdCalculator;

  public FibGCD() {
    this.fibCalculator = new FibCalculator();
    this.gcdCalculator = new GCDCalculator();
  }

  public void runProgram() {
    while (true) {
      printMenu();

      // Gets the 2 numbers a and b
      int a = this.getNum("A");
      int b = this.getNum("B");

      // Calculates the GCD of the sum of fibonacci numbers up to a and b
      this.calculateFibGCD(a, b);

      // Asks the user if they want to run the program again
      if (getString("Would you like to run the program again? (y/n): ").equals("n")) {
        return;
      }
    }
  }

  /***
   * Calculates the GCD of the sum of fibonacci numbers up to a and b
   * 
   * @param a
   * @param b
   */
  private void calculateFibGCD(int a, int b) {
    int sumA = this.fibCalculator.fib(a + 1) - 1;
    int sumB = this.fibCalculator.fib(b + 1) - 1;

    int gcd = this.gcdCalculator.calculateGCD(sumA, sumB);
    System.out.printf("GCD of the sum of fibonacci numbers up to %d and %d is %d\n", a, b, gcd);
  }

  /***
   * This method takes every possible a, b pair between 0 and 40 and calculates
   * the GCD of the sum of fibonacci numbers up to a and b to see whether GCD runs
   * into a stack overflow error
   * 
   */
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

  /***
   * Gets a string from the user
   * 
   * @param prompt The prompt to display to the user
   * @return The string inputted by the user
   */
  private String getString(String prompt) {
    System.out.print(prompt);
    Scanner scanner = new Scanner(System.in);
    String userInput = scanner.nextLine();
    return userInput;
  }

  /***
   * Gets a number from the user between min and max
   * 
   * @param numLabel The label of the number
   * @return The number inputted by the user
   */
  private int getNum(String numLabel) {
    int num;

    while (true) {
      try {
        System.out.printf("Please input a number between %d and %d for number %s: ", min, max, numLabel);
        Scanner scanner = new Scanner(System.in);

        num = scanner.nextInt(); // Scans the next token of the input as an int.
      } catch (Exception e) {
        continue; // If the input is invalid, then ask the user again
      }

      // Checks for bounds
      if (num < min || num > max) {
        System.out.println("Number out of bounds");
        continue;
      }
      return num;
    }
  }

  private void printMenu() {
    System.out.println(
        "Welcome! This program that takes 2 numbers (a) and (b) and computes the greatest common\ndivisor of the fibonacci numbers at (a) and (b)");
  }

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
}
