package org.example.vehicle;

import lombok.Getter;

@Getter
public enum VehicleSize {

    SMALL(1) ,
    MEDIUM(2) ,

    LARGE(3) ,

    XLARGE(4) ;

    private int size ;

    VehicleSize(int size){
        this.size = size ;
    }

}
