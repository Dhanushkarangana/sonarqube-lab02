package com.example;

public class Calculator {

    public int calculate(int a, int b, String op) {
        switch (op) {
            case "add":
                return a + b;
            case "sub":
                return a - b;
            case "mul":
                return a * b;
            case "div":
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            case "mod":

                return a % b;
            default:
                throw new IllegalArgumentException("Unknown operation: " + op);
        }
    }
}