package org.example.vehicle;

import lombok.Getter;

@Getter
public abstract class Vehicle {

    private String numberPlate ;
    private VehicleSize vehicleSize ;

    public Vehicle(String numberPlate, VehicleSize vehicleSize) {
        this.numberPlate = numberPlate ;
        this.vehicleSize = vehicleSize ;
    }
}
