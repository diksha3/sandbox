package com.diksha.service;


import com.diksha.model.Snapshot;
import com.diksha.model.SnapshotStatus;

import java.util.ArrayDeque;
import java.util.Map;

public class InMemoryServiceImpl implements InMemoryDBService{

    private static ArrayDeque<Snapshot> inMemoryQueue ;

    private static InMemoryServiceImpl inMemoryService ;

    private InMemoryServiceImpl(){
        if(inMemoryQueue ==null) inMemoryQueue = new ArrayDeque<>() ;
    }

    public static synchronized InMemoryServiceImpl getInstance(){
        if(inMemoryService ==null) inMemoryService = new InMemoryServiceImpl() ;
        return inMemoryService ;
    }


    @Override
    public void set(String key, String value) {
        Snapshot currentSnapshot = null ;
         if(inMemoryQueue==null || inMemoryQueue.isEmpty()) currentSnapshot =  begin() ;
         else currentSnapshot = inMemoryQueue.peekLast() ;
        Map<String, String> keyValueMapForSnapshot  = currentSnapshot.getKeyValue() ;
        Map<String, Integer> valueToCountMapForSnapshot  = currentSnapshot.getValueToCountPair() ;
        if(keyValueMapForSnapshot.containsKey(key)){
            String oldValue = keyValueMapForSnapshot.get(key) ;
            int countForOldValue = valueToCountMapForSnapshot.get(oldValue) ;
            if(countForOldValue ==1) valueToCountMapForSnapshot.remove(oldValue) ;
            else valueToCountMapForSnapshot.put(oldValue,--countForOldValue);
        }

        keyValueMapForSnapshot.put(key, value) ;
        if(valueToCountMapForSnapshot.containsKey(value)) valueToCountMapForSnapshot.put(value,valueToCountMapForSnapshot.get(value)+1) ;
        else valueToCountMapForSnapshot.put(value,1);




    }

    @Override
    public String get(String key) {
        if(inMemoryQueue == null || inMemoryQueue.isEmpty()) throw  new IllegalArgumentException("data unavailable") ;
        Snapshot currentSnapshot = inMemoryQueue.peekLast() ;
        return currentSnapshot.getKeyValue().getOrDefault(key,"data unavailable") ;
    }

    @Override
    public void delete(String key) {
        if(inMemoryQueue == null || inMemoryQueue.isEmpty()) throw  new IllegalArgumentException("data unavailable") ;
        Snapshot currentSnapshot = inMemoryQueue.peekLast() ;
        Map<String, String> keyValueMapForSnapshot  = currentSnapshot.getKeyValue() ;
        Map<String, Integer> valueToCountMapForSnapshot  = currentSnapshot.getValueToCountPair() ;
        if(!keyValueMapForSnapshot.containsKey(key)) throw new IllegalArgumentException("bad operation") ;
        String valueForKey = keyValueMapForSnapshot.get(key) ;
        keyValueMapForSnapshot.remove(key) ;
        int countForValue = valueToCountMapForSnapshot.get(valueForKey) ;
        if(countForValue ==1 ) valueToCountMapForSnapshot.remove(valueForKey) ;
        else valueToCountMapForSnapshot.put(valueForKey,--countForValue) ;

    }

    @Override
    public int count(String key) {
        if(inMemoryQueue == null || inMemoryQueue.isEmpty()) throw  new IllegalArgumentException("data unavailable") ;
        Snapshot currentSnapshot = inMemoryQueue.peekLast() ;
        Map<String, Integer> valueToCountMapForSnapshot  = currentSnapshot.getValueToCountPair() ;
        return valueToCountMapForSnapshot.getOrDefault(key,0) ;
    }

    @Override
    public Snapshot begin() {
        Snapshot snapshot = null ;
        if(inMemoryQueue==null || inMemoryQueue.isEmpty()){
            snapshot = new Snapshot() ;
        }
        else {
            snapshot = new Snapshot(inMemoryQueue.peekLast());
        }
        if(inMemoryQueue ==null) inMemoryQueue = new ArrayDeque<>() ;
        inMemoryQueue.offerLast(snapshot) ;
        return snapshot ;
    }

    @Override
    public void commit() {
        if(inMemoryQueue==null || inMemoryQueue.isEmpty() || inMemoryQueue.peekLast().getSnapshotStatus()!= SnapshotStatus.OPEN) throw new IllegalArgumentException("no open blocks to commit") ;
        inMemoryQueue.stream().iterator().forEachRemaining(Snapshot::commitSnapshot);
    }

    @Override
    public void rollback() {
        if(inMemoryQueue==null || inMemoryQueue.isEmpty() || inMemoryQueue.peekLast().getSnapshotStatus()!=SnapshotStatus.OPEN) throw new IllegalArgumentException("no open blocks to rollback") ;
        inMemoryQueue.pollLast() ;
    }
}
