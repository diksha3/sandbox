package com.diksha.designPattern;

// 1. Product Interface
interface Vehicle {
    void assemble();
    void paint();
    void drive();
}

// 2. Concrete Products
class Car implements Vehicle {
    @Override
    public void assemble() {
        System.out.println("Assembling Car components.");
    }

    @Override
    public void paint() {
        System.out.println("Painting Car.");
    }

    @Override
    public void drive() {
        System.out.println("Driving Car.");
    }
}

class Truck implements Vehicle {
    @Override
    public void assemble() {
        System.out.println("Assembling Truck components.");
    }

    @Override
    public void paint() {
        System.out.println("Painting Truck.");
    }

    @Override
    public void drive() {
        System.out.println("Driving Truck.");
    }
}

class Motorcycle implements Vehicle {
    @Override
    public void assemble() {
        System.out.println("Assembling Motorcycle components.");
    }

    @Override
    public void paint() {
        System.out.println("Painting Motorcycle.");
    }

    @Override
    public void drive() {
        System.out.println("Riding Motorcycle.");
    }
}

// 3. Creator Abstract Class (or Interface)
// This is the "factory" that defines the method for creating vehicles.
abstract class VehicleFactory {
    // The Factory Method - returns a Vehicle
    protected abstract Vehicle createVehicle();

    // Other operations that use the created vehicle
    public void produceVehicle() {
        Vehicle vehicle = createVehicle(); // Call the factory method
        vehicle.assemble();
        vehicle.paint();
        vehicle.drive();
        System.out.println("Vehicle production complete.\n");
    }
}

// 4. Concrete Creators
class CarFactory extends VehicleFactory {
    @Override
    protected Vehicle createVehicle() {
        System.out.println("CarFactory: Creating a Car.");
        return new Car();
    }
}

class TruckFactory extends VehicleFactory {
    @Override
    protected Vehicle createVehicle() {
        System.out.println("TruckFactory: Creating a Truck.");
        return new Truck();
    }
}

class MotorcycleFactory extends VehicleFactory {
    @Override
    protected Vehicle createVehicle() {
        System.out.println("MotorcycleFactory: Creating a Motorcycle.");
        return new Motorcycle();
    }
}

// Client Code
public class FactoryMethodExample {
    public static void main(String[] args) {
        // Create a Car using CarFactory
        VehicleFactory carProducer = new CarFactory();
        carProducer.produceVehicle();

        // Create a Truck using TruckFactory
        VehicleFactory truckProducer = new TruckFactory();
        truckProducer.produceVehicle();

        // Create a Motorcycle using MotorcycleFactory
        VehicleFactory motorcycleProducer = new MotorcycleFactory();
        motorcycleProducer.produceVehicle();
    }
}