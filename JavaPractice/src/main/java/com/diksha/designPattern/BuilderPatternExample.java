package com.diksha.designPattern;

// 1. Product: The Meal object itself
class Meal {
    private String mainItem;
    private String sideItem;
    private String drink;
    private String dessert;

    // Private constructor, forces use of the Builder
    private Meal(MealBuilder builder) {
        this.mainItem = builder.mainItem;
        this.sideItem = builder.sideItem;
        this.drink = builder.drink;
        this.dessert = builder.dessert;
    }

    // Getters for all fields
    public String getMainItem() { return mainItem; }
    public String getSideItem() { return sideItem; }
    public String getDrink() { return drink; }
    public String getDessert() { return dessert; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Meal components:\n");
        if (mainItem != null) sb.append("  Main: ").append(mainItem).append("\n");
        if (sideItem != null) sb.append("  Side: ").append(sideItem).append("\n");
        if (drink != null) sb.append("  Drink: ").append(drink).append("\n");
        if (dessert != null) sb.append("  Dessert: ").append(dessert).append("\n");
        return sb.toString();
    }

    // 2. Builder Class (often implemented as a static nested class within the Product)
    public static class MealBuilder {
        private String mainItem;
        private String sideItem;
        private String drink;
        private String dessert;

        // Builder methods - return `this` for chaining
        public MealBuilder withMainItem(String mainItem) {
            this.mainItem = mainItem;
            return this;
        }

        public MealBuilder withSideItem(String sideItem) {
            this.sideItem = sideItem;
            return this;
        }

        public MealBuilder withDrink(String drink) {
            this.drink = drink;
            return this;
        }

        public MealBuilder withDessert(String dessert) {
            this.dessert = dessert;
            return this;
        }

        // The final method that constructs and returns the Meal object
        public Meal build() {
            // Optional: Add validation here before building
            if (this.mainItem == null && this.sideItem == null && this.drink == null && this.dessert == null) {
                System.out.println("Warning: Building an empty meal!");
            }
            return new Meal(this); // Pass the builder itself to the Meal constructor
        }
    }
}

// Client Code
public class BuilderPatternExample {
    public static void main(String[] args) {
        // Build a classic combo meal
        Meal comboMeal = new Meal.MealBuilder()
                .withMainItem("Cheeseburger")
                .withSideItem("Fries")
                .withDrink("Coca-Cola")
                .build(); // Don't want a dessert
        System.out.println("Combo Meal:\n" + comboMeal);

        // Build a light meal
        Meal lightMeal = new Meal.MealBuilder()
                .withMainItem("Salad")
                .withDrink("Water")
                .build(); // No side, no dessert
        System.out.println("Light Meal:\n" + lightMeal);

        // Build a custom dessert order
        Meal dessertOrder = new Meal.MealBuilder()
                .withDessert("Ice Cream Sundae")
                .withDrink("Coffee")
                .build(); // Only dessert and drink
        System.out.println("Dessert Order:\n" + dessertOrder);

        // Build an empty meal (with warning)
        Meal emptyMeal = new Meal.MealBuilder().build();
        System.out.println("Empty Meal:\n" + emptyMeal);
    }
}