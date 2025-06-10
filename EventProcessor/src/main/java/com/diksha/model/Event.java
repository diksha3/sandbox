package com.diksha.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Event {

    private final long identifier ;
    private final long timestamp ;
    private final String payload ;
    private final short checksum ;

    private double avg ;

    private boolean isInValid ;





}
