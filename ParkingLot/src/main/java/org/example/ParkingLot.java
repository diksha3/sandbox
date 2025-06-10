package org.example;

import org.example.vehicle.Vehicle;

import java.util.*;

public class ParkingLot implements IParkingLot {

    private Map<String,String> occoupiedSpots ;
    private Map<SpotSize, List<Spot>> availableSpots ;

    public ParkingLot(){
        occoupiedSpots = new HashMap<>() ;
        availableSpots = new TreeMap<>(Enum::compareTo);


    }
    @Override
    public Map<SpotSize, List<Spot>> getAvailableSpots() {

        return availableSpots ;
    }

    @Override
    public Map<String, String> getOccupiedSpot() {
        return occoupiedSpots ;
    }

    @Override
    public void addAVehicleToParkingLot(Vehicle vehicle) {

       Optional<Spot> bestSpotForVehicle = getBestSpotForVehicle(vehicle);
        if(!bestSpotForVehicle.isPresent()) throw new IllegalArgumentException("no spot available for vehicle "+ vehicle.getNumberPlate()) ;



    }

    private Optional<Spot> getBestSpotForVehicle(Vehicle vehicle) {

               /*  availableSpots
                 .entrySet()
                         .stream()
                         .filter(spotSizeListEntry -> spotSizeListEntry.getKey().getSize()>=vehicle.getVehicleSize().getSize())
                         .findFirst();*/
        return Optional.empty() ; 

    }

    @Override
    public int releaseSpot(Vehicle vehicle) {
        return 0;
    }

    @Override
    public Spot getSpotForVehicle(Vehicle vehicle) {
        return null;
    }



}
