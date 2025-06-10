package com.diksha.model;



import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Snapshot {

    private Map<String,String> keyValue ;
    private Map<String,Integer> valueToCountPair ;

    private SnapshotStatus snapshotStatus ;

    public Snapshot(){
        keyValue = new HashMap<>() ;
        valueToCountPair = new HashMap<>() ;
        snapshotStatus = SnapshotStatus.OPEN ;
    }

    public Snapshot(Snapshot s){

        keyValue = new HashMap<>(s.getKeyValue()) ;
        valueToCountPair = new HashMap<>(s.getValueToCountPair()) ;
        snapshotStatus = SnapshotStatus.OPEN ;
    }


    public void commitSnapshot(){
        this.snapshotStatus = SnapshotStatus.COMMITTED ;
    }

}
