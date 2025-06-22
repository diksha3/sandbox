package com.diksha.designPattern;

import java.util.ArrayList;
import java.util.List;

// 1. Observer Interface
interface WeatherDisplay {
    void update(float temperature, float humidity, float pressure);
}

// 2. Subject Interface (or abstract class for common implementation)
interface WeatherStationObservable {
    void addObserver(WeatherDisplay display);
    void removeObserver(WeatherDisplay display);
    void notifyObservers();
}

// 3. Concrete Subject: The WeatherStation
class WeatherStation implements WeatherStationObservable {
    private List<WeatherDisplay> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(WeatherDisplay display) {
        observers.add(display);
        System.out.println("Observer added: " + display.getClass().getSimpleName());
    }

    @Override
    public void removeObserver(WeatherDisplay display) {
        observers.remove(display);
        System.out.println("Observer removed: " + display.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers() {
        System.out.println("\nNotifying observers with new weather data...");
        for (WeatherDisplay display : observers) {
            display.update(temperature, humidity, pressure);
        }
    }

    // Method to set new measurements and trigger notification
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged(); // Inform observers
    }

    // This method is called when internal state changes
    public void measurementsChanged() {
        notifyObservers();
    }
}

// 4. Concrete Observers: Different Display Devices
class CurrentConditionsDisplay implements WeatherDisplay {
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public void display() {
        System.out.println("Current Conditions: " +
                temperature + "C degrees, " +
                humidity + "% humidity, " +
                pressure + " hPa pressure.");
    }
}

class StatisticsDisplay implements WeatherDisplay {
    private float maxTemp = 0.0f;
    private float minTemp = 200.0f;
    private float tempSum = 0.0f;
    private int numReadings;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        tempSum += temperature;
        numReadings++;

        if (temperature > maxTemp) {
            maxTemp = temperature;
        }

        if (temperature < minTemp) {
            minTemp = temperature;
        }
        display();
    }

    public void display() {
        System.out.println("Avg/Max/Min temperature: " +
                (tempSum / numReadings) + "C/" +
                maxTemp + "C/" + minTemp + "C");
    }
}

class ForecastDisplay implements WeatherDisplay {
    private float currentPressure = 0.0f;
    private float lastPressure;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;
        display();
    }

    public void display() {
        System.out.print("Weather forecast: ");
        if (currentPressure > lastPressure) {
            System.out.println("Improving weather on the way!");
        } else if (currentPressure == lastPressure) {
            System.out.println("More of the same.");
        } else if (currentPressure < lastPressure) {
            System.out.println("Watch out for cooler, rainy weather.");
        }
    }
}


// Client Code
public class ObserverPatternExample {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();
        ForecastDisplay forecastDisplay = new ForecastDisplay();

        // Register observers
        station.addObserver(currentDisplay);
        station.addObserver(statisticsDisplay);
        station.addObserver(forecastDisplay);

        // Simulate new weather measurements
        station.setMeasurements(25.5f, 65.0f, 1012.5f);
        station.setMeasurements(26.0f, 60.0f, 1015.0f); // Pressure rising (improving)
        station.setMeasurements(24.0f, 70.0f, 1008.0f); // Pressure dropping (rainy)

        // Remove one observer
        station.removeObserver(statisticsDisplay);
        System.out.println("\n--- StatisticsDisplay removed ---");

        station.setMeasurements(23.0f, 75.0f, 1008.0f); // Same pressure (more of the same)
    }
}