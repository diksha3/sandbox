package com.diksha;

// Logger.java
 class Logger {

    // 2. Static field to hold the single instance
    private static Logger instance;

    // 1. Private constructor to prevent direct instantiation
    private Logger() {
        // Initialization for the logger, e.g., setting up file writer
        System.out.println("Logger instance created.");
    }

    // 3. Public static method to provide global access to the instance
    // This is a thread-safe way to implement lazy initialization (Double-Checked Locking)
    public static Logger getInstance() {
        if (instance == null) { // First check: no need to synchronize if instance already exists
            synchronized (Logger.class) { // Synchronize on the class to prevent multiple threads from entering
                if (instance == null) { // Second check: instance might have been created by another thread while waiting
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String message) {
        // In a real application, this would write to a file, console, database, etc.
        System.out.println("[LOG] " + message);
    }
}

class BillPughSingleton {

    private BillPughSingleton() {}

    // Nested static class
    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE; // Instance created only when getInstance() is called for the first time
    }
}

// Application.java (Client Code)
public class LoggerApplication {
    public static void main(String[] args) {

        // Get the first instance of the Logger
        Logger logger1 = Logger.getInstance();
        logger1.log("Application started.");

        // Try to get another instance
        Logger logger2 = Logger.getInstance();
        logger2.log("User logged in.");

        // Verify that both references point to the same instance
        if (logger1 == logger2) {
            System.out.println("Both logger1 and logger2 refer to the same Logger instance.");
        } else {
            System.out.println("Error: Multiple Logger instances created.");
        }

        // Another part of the application uses the logger
        performSomeOperation();
    }

    public static void performSomeOperation() {
        Logger logger = Logger.getInstance(); // Get the single instance again
        logger.log("Performing some critical operation.");
    }
}