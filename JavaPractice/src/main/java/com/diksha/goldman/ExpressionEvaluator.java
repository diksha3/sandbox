package com.diksha.goldman;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class to evaluate mathematical expressions following BODMAS precedence rules.
 * Supports addition (+), subtraction (-), multiplication (*), division (/), and parentheses ().
 * Handles integer and decimal numbers.
 */
public class ExpressionEvaluator {

    // Regular expression to tokenize the input string.
    // It captures:
    //   - Numbers: sequences of digits, optionally followed by a decimal point and more digits.
    //   - Operators: +, -, *, /
    //   - Parentheses: (, )
    // The '|' acts as an OR, matching any of these patterns.
    private static final Pattern TOKEN_PATTERN = Pattern.compile(
            "\\d+\\.?\\d*|\\+|\\-|\\*|\\/|\\(|\\)"
    );

    // Defines the precedence of operators. Higher integer value means higher precedence.
    // This map is crucial for the Shunting-yard algorithm to correctly order operations.
    private static final Map<String, Integer> PRECEDENCE = new HashMap<>();
    static {
        PRECEDENCE.put("+", 1); // Addition and Subtraction have lower precedence
        PRECEDENCE.put("-", 1);
        PRECEDENCE.put("*", 2); // Multiplication and Division have higher precedence
        PRECEDENCE.put("/", 2);
    }

    /**
     * Evaluates a mathematical expression string according to BODMAS rules.
     * This is the main public method to be called by a client.
     *
     * @param expression The mathematical expression string (e.g., "3 + 2 * (1 + 1)").
     * Spaces are allowed and will be ignored during tokenization.
     * @return The double result of the evaluation.
     * @throws IllegalArgumentException if the expression is invalid (e.g., malformed,
     * contains unrecognized characters, or results in division by zero).
     */
    public double evaluate(String expression) {
        // Basic validation for null or empty input expressions.
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty.");
        }

        // Step 1: Tokenize the infix expression.
        // This breaks the string into meaningful units (numbers, operators, parentheses).
        List<String> tokens = tokenize(expression);
        System.out.println("Tokens: " + tokens); // For debugging purposes

        // Step 2: Convert infix tokens to postfix (Reverse Polish Notation - RPN)
        // This reorders the tokens so that operators appear after their operands,
        // which simplifies evaluation by removing the need for explicit precedence rules.
        List<String> postfixTokens = infixToPostfix(tokens);
        System.out.println("Postfix (RPN): " + postfixTokens); // For debugging purposes

        // Step 3: Evaluate the postfix expression.
        // This performs the actual calculations using a stack.
        return evaluatePostfix(postfixTokens);
    }

    /**
     * Tokenizes the input mathematical expression string into a list of individual tokens.
     * It uses a regular expression to find numbers, operators, and parentheses.
     *
     * @param expression The input expression string.
     * @return A list of tokens (e.g., "3", "+", "2", "*", "(", "1", "+", "1", ")").
     * @throws IllegalArgumentException if an unrecognized character or sequence of characters is found.
     */
    private List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        Matcher matcher = TOKEN_PATTERN.matcher(expression);
        int lastEnd = 0; // Tracks the end of the last matched token

        // Iterate through the expression, finding all matches for the TOKEN_PATTERN.
        while (matcher.find()) {
            // Check for any non-whitespace characters that were NOT matched by the pattern
            // between the end of the previous match and the start of the current match.
            if (matcher.start() > lastEnd) {
                String unrecognized = expression.substring(lastEnd, matcher.start()).trim();
                if (!unrecognized.isEmpty()) {
                    throw new IllegalArgumentException("Unrecognized character(s) in expression: '" + unrecognized + "'");
                }
            }
            tokens.add(matcher.group()); // Add the matched token to the list
            lastEnd = matcher.end();     // Update lastEnd to the end of the current match
        }

        // After the loop, check if there are any unrecognized characters at the very end of the expression.
        if (lastEnd < expression.length()) {
            String unrecognized = expression.substring(lastEnd).trim();
            if (!unrecognized.isEmpty()) {
                throw new IllegalArgumentException("Unrecognized character(s) at end of expression: '" + unrecognized + "'");
            }
        }
        return tokens;
    }

    /**
     * Converts a list of infix tokens to postfix (Reverse Polish Notation) tokens
     * using the Shunting-yard algorithm.
     * This algorithm uses an output queue and an operator stack to reorder the tokens.
     *
     * @param infixTokens The list of tokens in infix notation (from `tokenize` method).
     * @return A list of tokens in postfix notation.
     * @throws IllegalArgumentException if parentheses are mismatched or the expression structure is malformed.
     */
    private List<String> infixToPostfix(List<String> infixTokens) {
        Queue<String> outputQueue = new LinkedList<>(); // Stores the resulting postfix expression
        Stack<String> operatorStack = new Stack<>();   // Temporarily holds operators and parentheses

        for (String token : infixTokens) {
            if (isNumber(token)) {
                // If the token is a number, add it directly to the output queue.
                outputQueue.add(token);
            } else if (isOperator(token)) {
                // If the token is an operator (let's call it 'op1'):
                // Pop operators from the operator stack onto the output queue as long as:
                // 1. The stack is not empty.
                // 2. The top of the stack is an operator (let's call it 'op2').
                // 3. 'op2' has higher precedence than 'op1' OR ('op2' has equal precedence to 'op1' AND 'op1' is left-associative).
                // 4. 'op2' is NOT a left parenthesis.
                while (!operatorStack.isEmpty() && isOperator(operatorStack.peek()) &&
                        (PRECEDENCE.get(operatorStack.peek()) > PRECEDENCE.get(token) ||
                                (PRECEDENCE.get(operatorStack.peek()).equals(PRECEDENCE.get(token)) && isLeftAssociative(token))) &&
                        !"(".equals(operatorStack.peek())) {
                    outputQueue.add(operatorStack.pop());
                }
                // Push 'op1' onto the operator stack.
                operatorStack.push(token);
            } else if ("(".equals(token)) {
                // If the token is a left parenthesis, push it onto the operator stack.
                operatorStack.push(token);
            } else if (")".equals(token)) {
                // If the token is a right parenthesis:
                // Pop operators from the operator stack onto the output queue until a left parenthesis is encountered.
                try {
                    while (!"(".equals(operatorStack.peek())) {
                        outputQueue.add(operatorStack.pop());
                    }
                    // Discard the left parenthesis by popping it from the stack.
                    operatorStack.pop();
                } catch (EmptyStackException e) {
                    // If the stack becomes empty before finding a left parenthesis, it's a mismatch.
                    throw new IllegalArgumentException("Mismatched parentheses: Missing left parenthesis for closing ')'.");
                }
            } else {
                // This case should ideally not be reached if `tokenize` works correctly,
                // but it's a fallback for unexpected tokens.
                throw new IllegalArgumentException("Unknown token encountered during postfix conversion: " + token);
            }
        }

        // After processing all infix tokens, pop any remaining operators from the stack
        // and add them to the output queue.
        while (!operatorStack.isEmpty()) {
            String op = operatorStack.pop();
            // If any parenthesis remains on the stack, it indicates a mismatch.
            if ("(".equals(op) || ")".equals(op)) {
                throw new IllegalArgumentException("Mismatched parentheses: Missing right parenthesis for opening '('.");
            }
            outputQueue.add(op);
        }

        return new ArrayList<>(outputQueue); // Convert the queue to a list for return
    }

    /**
     * Evaluates a list of tokens in postfix (Reverse Polish Notation) notation.
     * This method uses a single operand stack to perform calculations.
     *
     * @param postfixTokens The list of tokens in postfix notation (from `infixToPostfix` method).
     * @return The double result of the evaluation.
     * @throws IllegalArgumentException if the expression is malformed (e.g., too many operators/operands)
     * or results in division by zero.
     */
    private double evaluatePostfix(List<String> postfixTokens) {
        Stack<Double> operandStack = new Stack<>(); // Stores numbers during evaluation

        for (String token : postfixTokens) {
            if (isNumber(token)) {
                // If the token is a number, parse it to a double and push it onto the operand stack.
                operandStack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                // If the token is an operator:
                // It requires two operands. Pop them from the stack.
                // The order is crucial: op2 is the most recently pushed (top), op1 is below it.
                try {
                    double op2 = operandStack.pop(); // Second operand
                    double op1 = operandStack.pop(); // First operand
                    // Apply the operation and push the result back onto the stack.
                    operandStack.push(applyOperation(op1, op2, token));
                } catch (EmptyStackException e) {
                    // If we can't pop two operands, the expression is malformed.
                    throw new IllegalArgumentException("Malformed expression: Not enough operands for operator '" + token + "'");
                }
            } else {
                // This should typically not happen if the `infixToPostfix` conversion is correct.
                throw new IllegalArgumentException("Unexpected token in postfix expression: '" + token + "'");
            }
        }

        // After processing all postfix tokens, the stack should contain exactly one element: the final result.
        if (operandStack.size() != 1) {
            throw new IllegalArgumentException("Malformed expression: Too many operands or operators remaining.");
        }
        return operandStack.pop(); // Return the final result
    }

    /**
     * Helper method to check if a string token represents a valid number (integer or decimal).
     *
     * @param token The string token to check.
     * @return true if the token can be parsed as a double, false otherwise.
     */
    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Helper method to check if a string token is a recognized arithmetic operator.
     *
     * @param token The string token to check.
     * @return true if the token is an operator defined in the PRECEDENCE map, false otherwise.
     */
    private boolean isOperator(String token) {
        return PRECEDENCE.containsKey(token);
    }

    /**
     * Helper method to determine if an operator is left-associative.
     * For standard arithmetic operators (+, -, *, /), they are all left-associative.
     * This method is included for extensibility if right-associative operators (like exponentiation)
     * were to be added in the future.
     *
     * @param operator The operator string.
     * @return true if the operator is left-associative, false otherwise.
     */
    private boolean isLeftAssociative(String operator) {
        // All standard arithmetic operators (+, -, *, /) are left-associative.
        return true;
    }

    /**
     * Performs the arithmetic operation specified by the operator on the two given operands.
     *
     * @param op1 The first operand.
     * @param op2 The second operand.
     * @param operator The operator symbol (+, -, *, /).
     * @return The result of the operation.
     * @throws IllegalArgumentException if an unknown operator is provided or if division by zero occurs.
     */
    private double applyOperation(double op1, double op2, String operator) {
        switch (operator) {
            case "+":
                return op1 + op2;
            case "-":
                return op1 - op2;
            case "*":
                return op1 * op2;
            case "/":
                if (op2 == 0) {
                    throw new IllegalArgumentException("Division by zero error.");
                }
                return op1 / op2;
            default:
                // This case should ideally not be reached if `isOperator` is used correctly.
                throw new IllegalArgumentException("Unknown operator: '" + operator + "'");
        }
    }

    // --- Main method for demonstrating and testing the ExpressionEvaluator ---
    public static void main(String[] args) {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();

        System.out.println("--- Expression Evaluation Test Cases ---");

        // Test Case 1: Simple addition
        testEvaluation(evaluator, "3 + 2", 5.0);

        // Test Case 2: Multiplication precedence
        testEvaluation(evaluator, "3 + 2 * 4", 11.0);

        // Test Case 3: Parentheses change precedence
        testEvaluation(evaluator, "(3 + 2) * 4", 20.0);

        // Test Case 4: Nested parentheses
        testEvaluation(evaluator, "3 + 2 * (1 + 1)", 7.0);

        // Test Case 5: Division
        testEvaluation(evaluator, "10 / 2 + 3", 8.0);

        // Test Case 6: Mixed operations with multiple precedence levels
        testEvaluation(evaluator, "10 - 2 * 3 + 6 / 2", 7.0); // Calculation: 10 - 6 + 3 = 4 + 3 = 7

        // Test Case 7: Decimal numbers
        testEvaluation(evaluator, "3.5 + 2.5 * 2", 8.5);

        // Test Case 8: Subtraction with parentheses
        testEvaluation(evaluator, "5 - (2 + 1)", 2.0);

        // Test Case 9: More complex expression
        testEvaluation(evaluator, "2 * (3 + 4 / 2) - 1", 9.0); // Calculation: 2 * (3 + 2) - 1 = 2 * 5 - 1 = 10 - 1 = 9

        // Test Case 10: Expression with varying whitespace
        testEvaluation(evaluator, "  1   +2* ( 3 - 1 ) / 2  ", 3.0); // Calculation: 1 + 2 * 2 / 2 = 1 + 4 / 2 = 1 + 2 = 3

        // Test Case 11: Only a number
        testEvaluation(evaluator, "123", 123.0);
        testEvaluation(evaluator, "42.7", 42.7);

        // --- Error Handling Test Cases ---
        System.out.println("\n--- Error Handling Test Cases ---");

        // Test Case 12: Division by zero
        testError(evaluator, "10 / 0", "Division by zero error.");

        // Test Case 13: Mismatched parentheses (missing right parenthesis)
        testError(evaluator, "(3 + 2 * 4", "Mismatched parentheses: Missing right parenthesis for opening '('.");

        // Test Case 14: Mismatched parentheses (missing left parenthesis)
        testError(evaluator, "3 + 2) * 4", "Mismatched parentheses: Missing left parenthesis for closing ')'.");

        // Test Case 15: Empty expression
        testError(evaluator, "", "Expression cannot be null or empty.");

        // Test Case 16: Null expression
        testError(evaluator, null, "Expression cannot be null or empty.");

        // Test Case 17: Malformed expression (operator without enough operands)
        testError(evaluator, "3 + * 2", "Malformed expression: Not enough operands for operator '*'.");

        // Test Case 18: Unrecognized character
        testError(evaluator, "3 + 2 $ 4", "Unrecognized character(s) in expression: '$'");

        // Test Case 19: Operator at the end
        testError(evaluator, "5 +", "Malformed expression: Too many operands or operators remaining.");
    }

    // --- Helper methods for testing ---

    /**
     * Helper method to run an evaluation test case and print the result.
     * @param evaluator The ExpressionEvaluator instance.
     * @param expression The expression string to test.
     * @param expected The expected double result.
     */
    private static void testEvaluation(ExpressionEvaluator evaluator, String expression, double expected) {
        try {
            double result = evaluator.evaluate(expression);
            System.out.printf("Expression: \"%s\" -> Result: %.2f (Expected: %.2f) -> %s%n",
                    expression, result, expected, (result == expected ? "PASS" : "FAIL"));
        } catch (IllegalArgumentException e) {
            System.out.printf("Expression: \"%s\" -> Error: %s (Unexpected error) -> FAIL%n", expression, e.getMessage());
        }
    }

    /**
     * Helper method to run an error test case and print the result.
     * @param evaluator The ExpressionEvaluator instance.
     * @param expression The expression string to test for an error.
     * @param expectedErrorMessage The expected error message substring.
     */
    private static void testError(ExpressionEvaluator evaluator, String expression, String expectedErrorMessage) {
        try {
            evaluator.evaluate(expression);
            System.out.printf("Expression: \"%s\" -> Result: No error (Expected error: %s) -> FAIL%n",
                    expression, expectedErrorMessage);
        } catch (IllegalArgumentException e) {
            System.out.printf("Expression: \"%s\" -> Error: %s (Expected error: %s) -> %s%n",
                    expression, e.getMessage(), expectedErrorMessage,
                    (e.getMessage().contains(expectedErrorMessage) ? "PASS" : "FAIL"));
        } catch (Exception e) { // Catch any other unexpected exceptions
            System.out.printf("Expression: \"%s\" -> Unexpected Exception: %s -> FAIL%n", expression, e.getMessage());
        }
    }
}
