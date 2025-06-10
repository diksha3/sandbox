package com.diksha.service;


import com.diksha.model.Snapshot;

public interface InMemoryDBService {

    public void set(String key,String value) ;
    public String get(String key) ;
    public void delete(String key) ;
    public int count(String key) ;
    public Snapshot begin() ;
    public void commit() ;
    public void rollback() ;



}
