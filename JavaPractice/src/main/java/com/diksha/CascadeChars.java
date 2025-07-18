package com.diksha;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CascadeChars {

    public static void main(String[] args) {
        List<Integer> myIntegerList =  IntStream.range(1,5).boxed().collect(Collectors.toList()) ;
        System.out.println(myIntegerList);
    }

}
