package com.diksha;

public class SegmentTree {


    static int[] seg = new int[100005] ;

    private static void buildSegmentTree(int index,int low, int high, int[] input){
        if(low==high){
            seg[index] = input[low] ;
            return;
        }
        int mid = low + (high-low)/2 ;
        buildSegmentTree(2* index+1, low, mid,input);
        buildSegmentTree(2* index+2, mid+1,high, input);
        seg[index] = Math.max(seg[2*index+1],seg[2*index+2]) ;
    }

    private static int[] getInput(){
        return new int[100] ;
    }

    public static void main(String[] args) {
        // take input
        int[] input = getInput() ;

        // build segment tree
        buildSegmentTree(0,0, input.length-1, input);




    }
}
