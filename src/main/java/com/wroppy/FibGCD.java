package com.wroppy;

import java.util.Scanner;

public class FibGCD {
  private int max = 40;
  private int min = 0;
  private FibCalculator fibCalculator = new FibCalculator();
  private GCDCalculator gcdCalculator = new GCDCalculator();

  public void runProgram() {
    printMenu();

    // Gets the 2 numbers a and b
    int a = this.getNum("A");
    int b = this.getNum("B");

    // Calculates the sum of fib numbers up to a and b using
    // the fib(n + 2) = sum(fib(n) + ... + fib(0)) method
    int sumA = this.fibCalculator.fib(a + 2) - 1;
    int sumB = this.fibCalculator.fib(b + 2) - 1;

    int gcd = this.gcdCalculator.calculateGCD(sumA, sumB);
    System.out.println(sumA);
    System.out.println(sumB);

    String output =  String.format("GCD of fib number %d and %d is %d", a, b, gcd);
    System.out.println(output);

  }

  private int getNum(String a) {
    int num;

    while (true) {
      try {
        System.out.println(String.format("Please input a number between %d and %d for number %s:", min, max, a));
        Scanner scanner = new Scanner(System.in);

        num = scanner.nextInt();

        // Checks for bounds
        if (num < min && num > max) {
          throw new Exception();
        }

        return num;
      } catch (Exception e) {
      }
    }
  }

  private void printMenu() {
    System.out.println(
        "Welcome! This program that takes 2 numbers (a) and (b) and computes the greatest common\ndivisor of the fibonacci numbers at (a) and (b)");
  }

}
