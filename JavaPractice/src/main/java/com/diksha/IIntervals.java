package com.diksha;

import java.util.*;
import java.util.stream.Collectors;

public class IIntervals {

    public static void main(String[] args) {
        List<IInterval> input = createInput() ;
        List<IInterval> output = manageIIntervals(input) ;
        printOutput(output) ;
    }

    private static void printOutput(List<IInterval> output) {
        for(IInterval IInterval: output){
            System.out.println(IInterval);
        }
    }

    private static List<IInterval> manageIIntervals(List<IInterval> input) {
        Map<Integer,List<String>> IIntervalMap = new HashMap<>() ;

        for(IInterval thisIInterval : input){
           String person = thisIInterval.getName().get(0) ;
           int from = thisIInterval.getFrom() ;
           int to  = thisIInterval.getTo();
           IIntervalMap.computeIfAbsent(from,k-> new ArrayList<>()).add(person+"+") ;
           IIntervalMap.computeIfAbsent(to,k-> new ArrayList<>()).add(person+"-") ;
        }

        Map<Integer,List<String>> sortedMap = IIntervalMap
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1,LinkedHashMap::new)) ;




        Set<String> nameSet = new HashSet<>() ;
        List<IInterval> result = new ArrayList<>() ;
        sortedMap.entrySet().stream().findFirst().get().getValue().forEach(n-> nameSet.add(n.substring(0,n.length()-1)));
        List<Integer> timestamps = sortedMap.keySet().stream().toList() ;
        for (int i =0 ; i<timestamps.size()-1;i++){
            int from  = timestamps.get(i)  ;
            int to = timestamps.get(i+1) ;
            List<String> names = sortedMap.get(from) ;
            for(String name : names){
                if(name.contains("+")) nameSet.add(name.substring(0,name.length()-1)) ;
                else nameSet.remove(name.substring(0,name.length()-1));
            }
            List<String> personNames = nameSet.stream().toList() ;
            if(!personNames.isEmpty()){
                IInterval newIInterval = new IInterval(personNames,from,to) ;
                result.add(newIInterval) ;
            }

        }
        return result ;

    }

    private static List<IInterval> createInput() {
        List<IInterval> inputList = new ArrayList<>() ;
        IInterval one = new IInterval(List.of("A"), 0,3) ;
        IInterval two = new IInterval(List.of("B"), 2,4) ;
        IInterval three = new IInterval(List.of("C"), 5,6) ;
        return List.of(one ,two, three) ;
    }


}
