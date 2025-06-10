package com.diksha;

class Solution {

    private int atMost(int[] nums, int goal){
        int start=0, end=0 ;
        int oneCount=0 ;
        int noOfSubArrays=0;
        while(end<nums.length){
            if(nums[end]==1) oneCount++ ;
            while(start< end && oneCount>goal){
                if(nums[start]==1) oneCount-- ;
                start++ ;
//                printEverything(start, end ,noOfSubArrays,nums,oneCount) ;
            }

            if(oneCount<=goal) noOfSubArrays += end-start+1 ;
            end++ ;
           // printEverything(start, end ,noOfSubArrays,nums,oneCount) ;
        }

        return noOfSubArrays ;
    }


    private int atMostIf(int[] nums, int goal){
        int start=0, end=0 ;
        int oneCount=0 ;
        int noOfSubArrays=0;
        while(end<nums.length){
            if(oneCount>goal){

            }
        }

        return noOfSubArrays ;
    }

    private void printEverything(int start, int end, int noOfSubArrays, int[] nums, int oneCount) {
        System.out.println("");
        for(int i =start ; i <end;i++) System.out.print(nums[i]+" ");
        System.out.println("onecount  = "+oneCount +" noOfSubArrays = "+ noOfSubArrays);

    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        return atMostIf(nums, goal) - atMostIf(nums, goal-1) ;
    }

    public static void main(String[] args) {
        Solution solution = new Solution() ;
        int[] nums = {1,0,1,0,1} ;
        System.out.println(solution.numSubarraysWithSum(nums,2)) ;
    }
}