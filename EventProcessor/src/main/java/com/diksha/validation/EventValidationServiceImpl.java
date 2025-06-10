package com.diksha.validation;


import com.diksha.model.Event;

public class EventValidationServiceImpl implements EventValidationService{
    @Override
    public boolean isValidEvent(Event event) {
        char[] payloadCharacterArray = event.getPayload().toCharArray() ;
        int calculatedChecksum = 0 ;
        for(char charInPayload : payloadCharacterArray){
            calculatedChecksum += charInPayload - '0' ;
        }

        return calculatedChecksum%10 == event.getChecksum() ;
    }
}
