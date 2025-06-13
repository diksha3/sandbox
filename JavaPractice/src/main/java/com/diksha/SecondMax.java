package com.diksha;

public class SecondMax {

    public static void main(String[] args) {
        int[] input = {20,32,11,63,38} ;
        int max =0 ;
        for(int i : input){
            max = Math.max(i,max) ;
        }
        int secondMax = 0 ;
        for(int i : input){
            if(i<max && i > secondMax){
                secondMax = i ;
            }
        }
        System.out.println(secondMax);
    }
}
