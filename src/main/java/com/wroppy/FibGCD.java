package com.wroppy;

import java.util.Scanner;

public class FibGCD {
  private int max = 2000;
  private int min = 0;

  public void runProgram() {
    printMenu();

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
    int a = this.getNum("A");
    int b = this.getNum("B");

  }

}
