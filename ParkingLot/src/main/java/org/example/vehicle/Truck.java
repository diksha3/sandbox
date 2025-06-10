package org.example.vehicle;

import org.example.vehicle.Vehicle;
import org.example.vehicle.VehicleSize;

public class Truck extends Vehicle {

    public Truck(String numberPlate){
        super(numberPlate, VehicleSize.LARGE);
    }
}
