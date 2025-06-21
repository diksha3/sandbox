Great! Java 8 introduced some fundamental changes to the language, primarily with Lambda Expressions and the Stream API, along with enhancements to Interfaces and the new Date and Time API.

Here are some practice questions, categorized by topic, to help you solidify your understanding of Java 8 syntax:

I. Lambda Expressions & Functional Interfaces
Basic Lambda Syntax:

Create a Runnable using a lambda expression and execute it.
Define a Comparator using a lambda to sort a list of Strings by length.
Write a lambda for a Consumer<String> that prints the string in uppercase.
Custom Functional Interface:

Define your own functional interface called Calculator with a single abstract method operate(int a, int b).
Implement this interface using a lambda to perform addition, subtraction, multiplication, and division.
Method References:

Given a List<String>, use a method reference to print each string.
Given a List<Integer>, use a method reference to sort it in natural order.
Convert a lambda s -> System.out.println(s) into a method reference.
Lambda Scope:

Explain how this keyword behaves inside a lambda expression compared to an anonymous inner class. Provide a code example.
II. Stream API
Setup for Stream Questions:

Java

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Person {
private String name;
private int age;
private String city;

    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCity() { return city; }

    @Override
    public String toString() { return "Person{" + "name='" + name + '\'' + ", age=" + age + ", city='" + city + '\'' + '}'; }
}

List<Person> people = Arrays.asList(
new Person("Alice", 30, "New York"),
new Person("Bob", 25, "London"),
new Person("Charlie", 35, "New York"),
new Person("David", 25, "Paris"),
new Person("Eve", 40, "London"),
new Person("Frank", 30, "New York")
);

List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
List<String> words = Arrays.asList("apple", "banana", "apricot", "grape", "orange", "fig", "Avocado");
A. Stream Creation & Basic Operations:

From Collection: Filter numbers to keep only even numbers and collect them into a new List.
From Array: Given String[] names = {"Alice", "Bob", "Charlie"};, create a stream and print each name.
forEach: Print the name of each Person in the people list.
map: Transform the people list into a List<String> containing only their names.
filter: Get a List<Person> of all people older than 30.
distinct: Given List<Integer> duplicateNumbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5);, get a List of unique numbers.
limit and skip: Get the third, fourth, and fifth even numbers from the numbers list.
B. Intermediate & Terminal Operations:

flatMap: Given List<List<String>> nestedLists = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("c", "d"));, flatten it into a single List<String>.
sorted:
Sort the people list by age in ascending order.
Sort the people list by name in descending order.
allMatch, anyMatch, noneMatch:
Check if all people in the people list are older than 20.
Check if any person in the people list is from "Paris".
Check if no person in the people list has an age of 50.
findFirst & findAny:
Find the first person in people who is 25 years old.
Find any person in people whose name starts with 'B'.
count: Count how many people in the people list are from "London".
min & max: Find the youngest and oldest person in the people list (based on age).
C. Collectors:

toList, toSet, toMap:
Collect all names from the people list into a Set<String>.
Collect all people into a Map<String, Integer> where the key is the name and the value is the age (handle potential duplicate names if any by selecting first or throwing error).
groupingBy:
Group people by their city (Map<String, List<Person>>).
Group people by age and then by city within each age group (Map<Integer, Map<String, List<Person>>>).
Group people by city and count how many people are in each city (Map<String, Long>).
joining: Concatenate all names from the people list into a single comma-separated string.
averagingInt, summingInt:
Calculate the average age of all people.
Calculate the total sum of ages of all people.
reducing:
Use reduce to find the sum of all numbers in the numbers list.
Use reduce to concatenate all words in the words list into a single string.
III. New Interface Features (Default & Static Methods)
Default Method:

Create an interface Printable with a default method printDetails() that prints a generic message.
Create a class Document that implements Printable. Call printDetails().
Create another class Report that implements Printable and overrides printDetails().
Static Method:

Add a static method createDefaultDocument() to the Printable interface that returns a new Document object.
Call this static method directly on the interface.
Explain why you cannot override a static method from an interface in an implementing class.
IV. Optional
Creation:
Create an Optional<String> from a non-null string.
Create an empty Optional<String>.
Try to create an Optional<String> from a null string using Optional.of() and observe the behavior.
Presence Check:
Use isPresent() and ifPresent() to conditionally print a value from an Optional.
Default Values:
Use orElse() to provide a default string if an Optional<String> is empty.
Use orElseGet() to provide a default string (via a supplier) if an Optional<String> is empty. Explain the difference between orElse and orElseGet.
Error Handling:
Use orElseThrow() to throw a custom exception if an Optional<Person> is empty.
Transformation:
Given Optional<Person> maybePerson, use map() to get an Optional<String> of their name.
Given Optional<String> maybeEmail and a function String getDomain(String email), use flatMap() to get Optional<String> of the domain. Explain why flatMap is needed here instead of map.
V. Date and Time API (java.time)
Current Date/Time: Get and print the current date, current time, and current date-time.
Specific Date/Time: Create a LocalDate for your birthday in the current year. Create a LocalTime for 3:30 PM. Create a LocalDateTime for Christmas Day of next year at midnight.
Manipulation:
Add 5 days and 2 months to the current date.
Subtract 3 hours and 15 minutes from the current time.
Comparison: Check if your birthday is before or after today's date.
Formatting: Format the current LocalDateTime into a string like "DD-MM-YYYY HH:MM:SS".
Duration and Period:
Calculate the number of days between two LocalDate objects (e.g., today and your next birthday).
Calculate the duration in minutes between two LocalTime objects.
Instant: Explain Instant and when it's used. Convert a LocalDateTime to an Instant and vice-versa.