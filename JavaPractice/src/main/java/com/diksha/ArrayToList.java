package com.diksha;

import java.util.Arrays;
import java.util.List;

public class ArrayToList {

    public static void main(String[] args) {
        int[] input = {1,2,3} ;
        List<Integer> output = Arrays.stream(input).boxed().toList();
    }
}
