package org.example;

import org.example.vehicle.Vehicle;

import java.util.List;
import java.util.Map;

public interface IParkingLot {

    Map<SpotSize, List<Spot>> getAvailableSpots() ;

    Map<String,String> getOccupiedSpot() ;
    void addAVehicleToParkingLot(Vehicle vehicle) ;

    int releaseSpot(Vehicle vehicle) ;

    Spot getSpotForVehicle(Vehicle vehicle) ;


}
