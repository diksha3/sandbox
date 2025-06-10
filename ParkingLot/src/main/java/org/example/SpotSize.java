package org.example;


import lombok.Getter;

@Getter
public enum SpotSize {
    SMALL(1) ,
    MEDIUM(2) ,

    LARGE(3) ,

    XLARGE(4) ;

    private int size ;

    SpotSize(int size){
        this.size = size ;
    }
}
