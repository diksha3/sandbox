package com.diksha.goldman;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
@AllArgsConstructor
class Person{
    String name ;
    int age  ;

}
public class MaxElementGeneric {
    public static void main(String[] args) {
        String str =  "21+3*   " ;
        String[] tokens= str.trim().split("") ;
        System.out.println(tokens);
}}
