/*************************************************
 File: ExpressionDriver.java
 By: Hugo Gomez
 Date: 2/19/2024
 Description: Program that takes 4 user inputs for
 a,b,c,d and calcualtes the infix and postfix
 expression
 *************************************************/
import java.util.Scanner;
import java.util.Stack;

public class ExpressionDriver {
    private static final String infixExpression = "(a+b)*(c+d)";
    private static final String postfixExpression = "ac-b^d+";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int check = 0;
        // While loop to check if input is yes or no
        while (check == 0) {
            System.out.println("Enter values for:");
            System.out.print("a: ");
            int a = scanner.nextInt();
            System.out.print("b: ");
            int b = scanner.nextInt();
            System.out.print("c: ");
            int c = scanner.nextInt();
            System.out.print("d: ");
            int d = scanner.nextInt();
            // Adds the value to the expression and adds a space
            String infixToString = infixExpression
                    .replace("a", Integer.toString(a) + " ")
                    .replace("b", Integer.toString(b) + " ")
                    .replace("c", Integer.toString(c) + " ")
                    .replace("d", Integer.toString(d) + " ");
            String postfixToString = postfixExpression
                    .replace("a", Integer.toString(a) + " ")
                    .replace("b", Integer.toString(b) + " ")
                    .replace("c", Integer.toString(c) + " ")
                    .replace("d", Integer.toString(d) + " ");
            // Calls the evaluate functions
            int infixResult = InfixEvaluator.evaluateInfix(infixToString);
            int postfixResult = PostfixEvaluator.evaluatePostfix(postfixToString);
            System.out.println("Value of infix string " + infixExpression +
                    " with a = " + a + ", b = " + b + ", c = " + c + ", d = " + d +
                    " is " + infixResult);
            System.out.println("Value of postfix string " + postfixExpression +
                    " with a = " + a + ", b = " + b + ", c = " + c + ", d = " + d +
                    " is " + postfixResult);
            System.out.println("Continue, yes or no?");
            String input = scanner.next();
            if (input.equalsIgnoreCase("no")) {
                check = 1;
            }
        }
        scanner.close();
    }
    class InfixEvaluator {
        public static int evaluateInfix(String str) {
            Stack<Character> operatorStack = new Stack<>();
            Stack<Integer> valueStack = new Stack<>();
            StringBuilder numBuilder = new StringBuilder();
            // Turns the string into a character array
            for (char ch : str.toCharArray()) {
                // Checks for space and pushes to stack
                if (ch == ' ') {
                    if (numBuilder.length() > 0) {
                        valueStack.push(Integer.parseInt(numBuilder.toString()));
                        numBuilder.setLength(0);
                    }
                    // Checks for if digit and appends
                } else if (Character.isDigit(ch)) {
                    numBuilder.append(ch);
                    // Checks for ( and pushes
                } else if (ch == '(') {
                    operatorStack.push(ch);
                    // Checks for ) and apply the operator
                } else if (ch == ')') {
                    while (operatorStack.peek() != '(') {
                        applyOperator(valueStack, operatorStack);
                    }
                    operatorStack.pop();
                } else {

                    while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                        applyOperator(valueStack, operatorStack);
                    }
                    operatorStack.push(ch);
                }
            }

            if (numBuilder.length() > 0) {
                valueStack.push(Integer.parseInt(numBuilder.toString()));
            }

            while (!operatorStack.isEmpty()) {
                applyOperator(valueStack, operatorStack);
            }
            return valueStack.pop();
        }
        private static void applyOperator(Stack<Integer> operandStack, Stack<Character> operatorStack) {
            // Gets the operator and operands into the stacks and gets the results
            char operator = operatorStack.pop();
            int operand2 = operandStack.pop();
            int operand1 = operandStack.pop();
            int result = evaluate(operand1, operand2, operator);
            operandStack.push(result);
        }

        private static int evaluate(int operand1, int operand2, char operator) {
            switch (operator) {
                case '+':
                    return operand1 + operand2;
                case '-':
                    return operand1 - operand2;
                case '*':
                    return operand1 * operand2;
                case '/':
                    if (operand2 == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    return operand1 / operand2;
                case '^':
                    return (int) Math.pow(operand1, operand2);
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }
    }



    class PostfixEvaluator {
        public static int evaluatePostfix(String str) {
            Stack<Integer> stack = new Stack<>();
            StringBuilder numBuilder = new StringBuilder();
            // Turns string to array
            for (char ch : str.toCharArray()) {
                // Checks if digit
                if (Character.isDigit(ch)) {
                    numBuilder.append(ch);
                } else if (ch == ' ') {
                    // If there is a space then pushes the number to the stack
                    if (numBuilder.length() > 0) {
                        stack.push(Integer.parseInt(numBuilder.toString()));
                        numBuilder.setLength(0);
                    }
                } else if (isOperator(ch)) {
                    // If it is operator push to the stack
                    if (numBuilder.length() > 0) {
                        stack.push(Integer.parseInt(numBuilder.toString()));
                        numBuilder.setLength(0);
                    }
                    int operand2 = stack.pop();
                    int operand1 = stack.pop();
                    // Does the operation for the operands
                    int result = evaluate(operand1, operand2, ch);
                    stack.push(result);
                }
            }
            if (numBuilder.length() > 0) {
                stack.push(Integer.parseInt(numBuilder.toString()));
            }
            return stack.pop();
        }
        private static boolean isOperator(char ch) {
            // Checks if it is an operator
            return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
        }

        private static int evaluate(int operand1, int operand2, char operator) {
            // Does the operators
            switch (operator) {
                case '+':
                    return operand1 + operand2;
                case '-':
                    return operand1 - operand2;
                case '*':
                    return operand1 * operand2;
                case '/':
                    if (operand2 == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    return operand1 / operand2;
                case '^':
                    return (int) Math.pow(operand1, operand2);
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }
    }
}