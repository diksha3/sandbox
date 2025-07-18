package com.diksha.designPattern;

import java.util.ArrayList;
import java.util.List;

// Anti-pattern: tightly coupled, hard to extend
class ShoppingCartBad {
    // ... items ...
    public void checkout(String paymentMethod, double amount) {
        if (paymentMethod.equals("CreditCard")) {
            // Credit card specific logic
            System.out.println("Processing credit card payment of " + amount);
        } else if (paymentMethod.equals("PayPal")) {
            // PayPal specific logic
            System.out.println("Processing PayPal payment of " + amount);
        } else if (paymentMethod.equals("Crypto")) {
            // Crypto specific logic
            System.out.println("Processing Crypto payment of " + amount);
        } else {
            System.out.println("Invalid payment method.");
        }
    }
}


// Strategy Interface
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete Strategy 1
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String name;

    public CreditCardPayment(String cardNumber, String name) {
        this.cardNumber = cardNumber;
        this.name = name;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using Credit Card (Card No: " + cardNumber + ")");
        // Logic to process credit card payment
    }
}

// Concrete Strategy 2
class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using PayPal (Email: " + email + ")");
        // Logic to process PayPal payment
    }
}

// Concrete Strategy 3 (New, easily added)
class CryptoPayment implements PaymentStrategy {
    private String walletAddress;
    private String cryptoType;

    public CryptoPayment(String walletAddress, String cryptoType) {
        this.walletAddress = walletAddress;
        this.cryptoType = cryptoType;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using " + cryptoType + " (Wallet: " + walletAddress + ")");
        // Logic to process cryptocurrency payment
    }
}

// Context

class ShoppingCart {
    private List<String> items;
    private double totalAmount;
    private PaymentStrategy paymentStrategy; // Holds a reference to the strategy

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void addItem(String item, double price) {
        this.items.add(item);
        this.totalAmount += price;
        System.out.println("Added: " + item + " (Price: " + price + ")");
    }

    // Method to set the strategy at runtime
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Method that uses the strategy
    public void checkout() {
        if (paymentStrategy == null) {
            System.out.println("No payment strategy selected.");
            return;
        }
        System.out.println("\n--- Checkout ---");
        System.out.println("Total items: " + items);
        System.out.println("Total amount: " + totalAmount);
        paymentStrategy.pay(totalAmount); // Delegates the 'how to pay' to the selected strategy
        System.out.println("--- Checkout Complete ---\n");
    }
}

//client code
public class StrategyPatternExample {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Laptop", 1200.0);
        cart.addItem("Mouse", 25.0);

        // Pay with Credit Card
        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456", "John Doe"));
        cart.checkout();

        // Pay with PayPal
        cart.setPaymentStrategy(new PayPalPayment("john.doe@example.com"));
        cart.checkout();

        // Pay with Cryptocurrency (easily added without changing ShoppingCart)
        cart.setPaymentStrategy(new CryptoPayment("0x123abc...", "Ethereum"));
        cart.checkout();
    }
}
