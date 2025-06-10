package com.diksha;



import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
class Interval {

    private final int from ;
    private  final int to ;

    public boolean isOverlapping(int f, int t){
        if(this.from <= f && this.to>=t) return true ;
        if(this.from <= t && this.to>=t) return true ;
        if(this.to >= f && this.to <=t) return true ;
        if(this.from >= f && this.to>=t) return true ;
        return false ;
    }

    public Interval mergewithInterval(int f, int t){
        int ff = this.from<=f ? this.from : f ;
        int tt = this.to>=t ? this.to : t ;
        Interval newInterval = new Interval(ff, tt) ;
        return newInterval ;
    }

}
public class AstronautByteSegments {


    public static void main(String[] args) {
        int[][] inputBytes  = {{7,9},{1,3},{8,15},{6,9},{2,4}} ;  //[[7,9][1,3],[8,15],[6,9], [2,4]]
        int[] result  = getBytesByNow(inputBytes);
        for(int bytesByNow  : result){
            System.out.println( bytesByNow + " ");
        }
    }

    private static int[] getBytesByNow(int[][] inputBytes) {
         int numberOfBytesSeen = 0 ;
         boolean isOverlapping = false ;
         List<Interval> intervalList = new ArrayList<>() ;
         for(int[] thisInterval : inputBytes){
             for( int i =0 ; i< intervalList.size();i++){
                 if(intervalList.get(i).isOverlapping(thisInterval[0],thisInterval[1])) {
                     // remove the existing interval
                     Interval removedInterval = intervalList.remove(i) ;
                     // create the new interval
                     // calculate the added count
                     //add the new interval to the list
                 }
             }
         }
         return new int[2] ;
    }
}
