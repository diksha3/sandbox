package com.diksha;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TreeMapTest {

    public static void main(String[] args) {
        TreeMap<Integer,Integer> tm = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2) ;
            }
        }) ;
        tm.put(1,2) ;
        tm.put(3,2) ;
        tm.put(2,1) ;
        tm.put(6,6) ;


        tm.entrySet().stream().map(x-> {System.out.println(x.getKey() +" "+ x.getValue()); return x;}).collect(Collectors.toList());

        System.out.println(tm.lastKey());
        System.out.println(tm.lastEntry().getValue());



    }
}
