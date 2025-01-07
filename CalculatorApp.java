// Java program for a calculator demonstrating OOP concepts, threads, exception handling, and `this` pointer.
import java.util.Scanner;

// Base Calculator class demonstrating OOP principles
class Calculator {
    private double result;

    public Calculator() { //constructor
        
        this.result = 0; // `this` pointer used to refer to instance variable
    }

    // Addition
    public double add(double a, double b) {
        this.result = a + b;
        return this.result;
    }

    // Subtraction
    public double subtract(double a, double b) {
        this.result = a - b;
        return this.result;
    }

    // Multiplication
    public double multiply(double a, double b) {
        this.result = a * b;
        return this.result;
    }

    // Division with exception handling
    public double divide(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        this.result = a / b;
        return this.result;
    }
}

// Thread class to handle calculation in a separate thread
class CalculationThread extends Thread {
    private final Calculator calculator;
    private final String operation;
    private final double a, b;

    public CalculationThread(Calculator calculator, String operation, double a, double b) {
        this.calculator = calculator;
        this.operation = operation;
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        try {
            double result;
            switch (operation) {
                case "add":
                    result = calculator.add(a, b);
                    System.out.println("Addition Result: " + result);
                    break;
                case "subtract":
                    result = calculator.subtract(a, b);
                    System.out.println("Subtraction Result: " + result);
                    break;
                case "multiply":
                    result = calculator.multiply(a, b);
                    System.out.println("Multiplication Result: " + result);
                    break;
                case "divide":
                    result = calculator.divide(a, b);
                    System.out.println("Division Result: " + result);
                    break;
                default:
                    System.out.println("Invalid operation.");
            }
        } catch (ArithmeticException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

// Main class
public class CalculatorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println("Welcome to the Calculator App!");
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();

        System.out.print("Enter operation (add, subtract, multiply, divide): ");
        String operation = scanner.next().toLowerCase();

        // Creating and starting a thread for calculation
        CalculationThread calcThread = new CalculationThread(calculator, operation, num1, num2);
        calcThread.start();

        try {
            // Wait for the thread to finish
            calcThread.join();
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted: " + e.getMessage());
        }

        System.out.println("Thank you for using the Calculator App!");
        scanner.close();
    }
}
