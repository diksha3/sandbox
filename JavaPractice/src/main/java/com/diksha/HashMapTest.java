package com.diksha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapTest {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> thisMap = new HashMap<>() ;
        for(int i =0 ; i <10;i++){
            thisMap.computeIfAbsent(i,x->new ArrayList<Integer>()).add(i) ;
        }
        for(int i =0 ; i <10;i++){
            thisMap.computeIfAbsent(i,x->new ArrayList<Integer>()).add(i) ;
        }


        for(Integer key : thisMap.keySet()){
            System.out.println(key +"->"+ thisMap.get(key)) ;
        }
    }
}
