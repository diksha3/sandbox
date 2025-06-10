package com.diksha;

public class ReduceArrayToZero {

    /*
    Given an array of integers, where each integer >= 0, apply the following steps until all numbers are 0. Then return ans (explained below)
    step 1: find the first non-zero element in the array starting from the left. this is x.
    step 2: starting at your current location, subtract each number by x until you reach a number < x.
    step 3: add x to ans.
    step 4: jump to step 1
     */
    public static void main(String[] args) {
        int [] input = {0, 3, 3, 4, 3, 1} ;
        System.out.println(solve(input)) ;
    }

    private static int solve(int[] input) {
        int ans = 0 ;
        // step 1: find the first non-zero element in the array starting from the left. this is x.
        int i =0 ;
        while(true){
            int x = -1 ;
            boolean allZero = true ;
            for(i =0 ; i <input.length;i++){
                if(input[i]>0){
                    x = input[i] ;
                    allZero = false  ;
                    break ;
                }
                else if(input[i]<0) allZero = false ;
            }
            if(x == -1){
                if(allZero) return ans ;
            }

            //step 2: starting at your current location, subtract each number by x until you reach a number < x.
            for(int j = i;j<input.length;j++){
               if(input[j]>=x) input[j] -= x ;
            }
            ans+=x ;
        }








    }
}
