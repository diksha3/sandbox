package com.diksha;

import java.util.Map;

public class CodeWriting {

    public static void main(String[] args) {
        int[] width  = {1,3,1,3,3} ;
        int height  = 2  ;
        Map<Integer, Integer> inputMap = Map.of(0,1,1,2,2,4) ;
        inputMap
                .entrySet()
                        .stream()
                                .mapToInt(Map.Entry::getValue)
                                        .sum() ;



        System.out.println(getMinimumWidth(width,height));
    }

    private static int getMinimumWidth(int[] width, int height) {
        return 0 ;
    }
}
