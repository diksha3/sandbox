package com.diksha;

import java.util.*;
import java.util.stream.Collectors;

public class HashMapPractice {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> inputMap = new HashMap<>() ;
        Map<Integer, Integer> inputMap2 = new HashMap<>() ;
        inputMap.put(5, Arrays.asList(2,1, 4));
        inputMap.put(3, Arrays.asList(2,34, 4));
        inputMap.put(4, Arrays.asList(35,34, 1));
        inputMap2.put(6,  5);
        inputMap2.put(5,  4);
        inputMap2.put(3,  5);
        inputMap2.put(4,  1);
      // Map<Integer, List<Integer>> mapSortedOnKey =   sortOnKey(inputMap) ;
       Map<Integer, Integer> mapSorted =   sortOnValue(inputMap2) ;

    }

    private static Map<Integer, Integer> sortOnValue(Map<Integer, Integer> inputMap2) {
           LinkedHashMap<Integer,Integer> linkedHashMap = new LinkedHashMap<>() ;
           List<Map.Entry<Integer,Integer>> list = new ArrayList<>(inputMap2.entrySet()) ;
           Collections.sort(list, (a,b) -> b.getValue() - a.getValue());
           for(Map.Entry<Integer,Integer> mapEntry : list){
                linkedHashMap.put(mapEntry.getKey(),mapEntry.getValue()) ;
           }

           return linkedHashMap ;



    }



    private static Map<Integer,List<Integer>> sortOnKey(Map<Integer, List<Integer>> inputMap) {

        Set<Integer> sortedKeySet = new TreeSet<>(inputMap.keySet()) ;
        Map<Integer,List<Integer>> sortedMap = new LinkedHashMap<>() ;
        for(Integer sortedKey : sortedKeySet){
            sortedMap.put(sortedKey,inputMap.get(sortedKey)) ;
        }
        return sortedMap ;

    }
}
