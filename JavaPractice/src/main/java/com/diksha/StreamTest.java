package com.diksha;

import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@RequiredArgsConstructor
class Person1 {
    private final String fName ;
    private final String lName ;
    private final String email ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person1 person = (Person1) o;
        return fName.equals(person.fName) && lName.equals(person.lName) && email.equals(person.email);
    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(fName, lName, email);
//
//    }
}
public class StreamTest {

    private void mapTest(){
        Map<Integer,Integer> numMap = new ConcurrentHashMap<>() ;
        numMap.put(1,1) ;
        numMap.put(2,2) ;
        numMap.put(3,3) ;
        numMap.put(4,4) ;
        numMap.put(5,5) ;
        numMap.put(6,6) ;
        numMap.put(7,7) ;
        numMap.put(8,8) ;
        numMap.put(9,9) ;
        numMap.put(10,10) ;

        /*Iterator<Map.Entry<Integer,Integer>> mapIterator = numMap.entrySet().iterator() ;
        while(mapIterator.hasNext()){
            Map.Entry<Integer,Integer> entry  = mapIterator.next() ;
            if(entry.getKey() ==5){
                numMap.remove(entry.getKey()) ;
            }
            else {
                System.out.println(entry.getKey() +" -> " + entry.getValue());
            }
        }*/

        Set<Integer> keySet = numMap.keySet() ;
        for(Integer key : keySet){
            if(key == 5){
               // numMap.remove(key) ;
                numMap.put(20, 10) ;
            }
            else {
                System.out.println(key +" -> " + numMap.get(key));
            }

        }


    }
    private void streamTest(){
        Stream<String> stringStream = Stream.of("naman", "and", "skyler" , "are","best","friends")  ;
        List<Integer> list = Arrays.asList(1, 2, 10, 20, 26);
        int sumStream = list.stream()
                        .map(x-> x * x)
                        .mapToInt(Integer::intValue).sum();

        Map<String, List<Integer>> map = new HashMap<>();
        map.put("abc", List.of(1,2,3));
        map.put("def", List.of(4,5,6));

        List<Integer> result = map.entrySet().stream().flatMap(x -> x.getValue().stream()).toList();
        System.out.println(result);


       //printModifiedList(str) ;
    }

    private void printModifiedList(List<Integer> modifiedStringList) {
        for(int s : modifiedStringList){
            System.out.println(s);
        }
        String i = "abc";
        String j = "abc";
        System.out.println(i == j);
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        StreamTest streamTestObject = new StreamTest() ;
        //streamTestObject.mapTest();
        streamTestObject.streamTest();
        Map<Person1, Integer> map = new HashMap<>();
        Person1 p1 = new Person1("naman","sinha","namansinha.1992@gmail.com") ;
        Person1 p2 = new Person1("naman","sinha","namansinha.1992@gmail.com") ;

        map.put(p1, 1) ;
        map.put(p2, 2);

        System.out.println("does map contain  p2  : "+ map.containsKey(p2));
        System.out.println(map.get(p1));
        System.out.println(p1.equals(p2));







    }
}