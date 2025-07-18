package com.diksha.goldman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LoyalCustomerFinder {

    /**
     * Processes a single log file to identify customers who visited at least
     * two unique pages on that day.
     *
     * @param logFilePath The path to the log file (e.g., "day1.log").
     * @return A Set of Customer IDs who meet the unique page criteria for that day.
     * @throws IOException If there's an error reading the file.
     */
    private static Set<String> getCustomersWithTwoUniquePages(String logFilePath) throws IOException {
        // Map to store: CustomerId -> Set of unique PageIds visited by that customer
        Map<String, Set<String>> customerPageVisits = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Parse the log line: TimeStamp,PageId,CustomerId
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.err.println("Skipping malformed log line: " + line);
                    continue;
                }

                // Assuming format: TimeStamp,PageId,CustomerId
                // We only need PageId and CustomerId for this problem
                String pageId = parts[1].trim();
                String customerId = parts[2].trim();

                // Add the pageId to the set of pages for this customer
                // computeIfAbsent is a Java 8 feature: if the key doesn't exist,
                // it creates a new HashSet and puts it in the map, then adds the pageId.
                // If the key exists, it gets the existing Set and adds the pageId.
                customerPageVisits.computeIfAbsent(customerId, k -> new HashSet<>()).add(pageId);
            }
        }

        // Now, filter customers who visited at least two unique pages
        Set<String> loyalCustomersForDay = new HashSet<>();
        for (Map.Entry<String, Set<String>> entry : customerPageVisits.entrySet()) {
            if (entry.getValue().size() >= 2) {
                loyalCustomersForDay.add(entry.getKey());
            }
        }

        return loyalCustomersForDay;
    }

    public static void main(String[] args) {
        // Define your log file paths
        String day1LogFile = "day1.log";
        String day2LogFile = "day2.log";

        // Create dummy log files for testing
        // In a real scenario, these files would already exist.
        createDummyLogFiles(day1LogFile, day2LogFile);

        try {
            // Step 1: Get customers who visited at least two unique pages on Day 1
            Set<String> loyalCustomersDay1 = getCustomersWithTwoUniquePages(day1LogFile);
            System.out.println("Customers with >= 2 unique pages on Day 1: " + loyalCustomersDay1);

            // Step 2: Get customers who visited at least two unique pages on Day 2
            Set<String> loyalCustomersDay2 = getCustomersWithTwoUniquePages(day2LogFile);
            System.out.println("Customers with >= 2 unique pages on Day 2: " + loyalCustomersDay2);

            // Step 3: Find customers present in both sets (came on both days)
            List<String> finalLoyalCustomers = new ArrayList<>();
            // Iterate over the smaller set for efficiency
            if (loyalCustomersDay1.size() <= loyalCustomersDay2.size()) {
                for (String customerId : loyalCustomersDay1) {
                    if (loyalCustomersDay2.contains(customerId)) {
                        finalLoyalCustomers.add(customerId);
                    }
                }
            } else {
                for (String customerId : loyalCustomersDay2) {
                    if (loyalCustomersDay1.contains(customerId)) {
                        finalLoyalCustomers.add(customerId);
                    }
                }
            }

            System.out.println("\nList of Loyal Customers (came on both days AND visited >= 2 unique pages each day):");
            if (finalLoyalCustomers.isEmpty()) {
                System.out.println("No loyal customers found.");
            } else {
                for (String customerId : finalLoyalCustomers) {
                    System.out.println(customerId);
                }
            }

        } catch (IOException e) {
            System.err.println("An error occurred while reading log files: " + e.getMessage());
            e.printStackTrace();
        }
    }



    // Helper method to create dummy log files for testing purposes
    private static void createDummyLogFiles(String day1File, String day2File) {
        try (java.io.FileWriter writer1 = new java.io.FileWriter(day1File);
             java.io.FileWriter writer2 = new java.io.FileWriter(day2File)) {

            // Day 1 Logs
            writer1.write("2024-06-27 10:00:00,pageA,CUST001\n"); // CUST001: pageA
            writer1.write("2024-06-27 10:05:00,pageB,CUST001\n"); // CUST001: pageA, pageB (meets criteria)
            writer1.write("2024-06-27 10:10:00,pageA,CUST002\n"); // CUST002: pageA (does NOT meet criteria)
            writer1.write("2024-06-27 10:15:00,pageC,CUST003\n"); // CUST003: pageC
            writer1.write("2024-06-27 10:20:00,pageD,CUST003\n"); // CUST003: pageC, pageD (meets criteria)
            writer1.write("2024-06-27 10:25:00,pageE,CUST004\n"); // CUST004: pageE (does NOT meet criteria)
            writer1.write("2024-06-27 10:30:00,pageF,CUST005\n"); // CUST005: pageF
            writer1.write("2024-06-27 10:35:00,pageG,CUST005\n"); // CUST005: pageF, pageG (meets criteria)

            // Day 2 Logs
            writer2.write("2024-06-28 10:00:00,pageX,CUST001\n"); // CUST001: pageX
            writer2.write("2024-06-28 10:05:00,pageY,CUST001\n"); // CUST001: pageX, pageY (meets criteria)
            writer2.write("2024-06-28 10:10:00,pageA,CUST002\n"); // CUST002: pageA (does NOT meet criteria)
            writer2.write("2024-06-28 10:15:00,pageB,CUST002\n"); // CUST002: pageA, pageB (meets criteria)
            writer2.write("2024-06-28 10:20:00,pageZ,CUST003\n"); // CUST003: pageZ (does NOT meet criteria) - CUST003 from Day1 is NOT loyal
            writer2.write("2024-06-28 10:25:00,pageH,CUST004\n"); // CUST004: pageH
            writer2.write("2024-06-28 10:30:00,pageI,CUST004\n"); // CUST004: pageH, pageI (meets criteria)
            writer2.write("2024-06-28 10:35:00,pageJ,CUST005\n"); // CUST005: pageJ (does NOT meet criteria) - CUST005 from Day1 is NOT loyal

            System.out.println("Dummy log files created: " + day1File + ", " + day2File);

        } catch (IOException e) {
            System.err.println("Error creating dummy log files: " + e.getMessage());
        }
    }
}