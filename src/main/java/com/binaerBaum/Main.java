package com.binaerBaum;

import java.util.Scanner;

/** Main entry point for AVL Tree traversal program. */
public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    AVLTreeProgram program = new AVLTreeProgram(scanner);
    program.run();
    scanner.close();
  }
}
