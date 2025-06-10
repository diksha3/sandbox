package com.diksha;

import java.util.*;
import java.util.function.Function;

class PriorityQueueTest {

    PriorityQueue<int[]> priceToTimeStampMaxHeap ;
    PriorityQueue<int[]> priceToTimeStampMinHeap ;
    Set<Integer> timestampSet  ;
    int currPrice ;
    int currTimestamp ;

    public PriorityQueueTest() {

        priceToTimeStampMaxHeap = new PriorityQueue<>((a,b)->b[0]-a[0]) ;
        priceToTimeStampMinHeap = new PriorityQueue<>((a,b) -> a[0]-b[0]) ;
        timestampSet = new HashSet<>() ;

        currPrice =0  ;
        currTimestamp =0  ;

    }

    private <I,O> PriorityQueue<int[]> removeFromHeap(PriorityQueue<int[]> heap, int timestamp, String type){

        Iterator<int[]> heapIterator = heap.iterator() ;
        while(heapIterator.hasNext()){
            int[] heapElement  = heapIterator.next() ;
            if(heapElement[1]==timestamp){
                heapIterator.remove() ;
                break  ;
            }

        }

        Comparator<int[]> comparatorFn =  (a, b)->b[0]-a[0];
        PriorityQueue<int[]> copy = new PriorityQueue<>(comparatorFn) ;
        copy.addAll(heap) ;
        return copy ;


    }

    public void update(int timestamp, int price){

        int[] node = new int[2] ;
        node[0] = price ;
        node[1] = timestamp ;
        if(timestamp>=currTimestamp){
            currPrice = price ;
            currTimestamp = timestamp ;
        }


        // it could be a case that the max just got overwritten
        // we may need to remove from both minHeap and maxHeap
        if(timestampSet.contains(timestamp)){
           // priceToTimeStampMaxHeap = removeFromHeap(priceToTimeStampMaxHeap, timestamp, ((a,b)->)) ;
            //priceToTimeStampMinHeap = removeFromHeap(priceToTimeStampMinHeap, timestamp, ((a,b)->a[0]-b[0])) ;
            timestampSet.remove(timestamp) ;
        }

        timestampSet.add(timestamp) ;
        priceToTimeStampMaxHeap.offer(node) ;
        // it could be a case that the min just got overwritten
        priceToTimeStampMinHeap.offer(node) ;

    }

    public int current() {
        return this.currPrice ;
    }

    public int maximum() {
        return priceToTimeStampMaxHeap.peek()[0] ;
    }

    public int minimum() {
        return priceToTimeStampMinHeap.peek()[0] ;
    }
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */