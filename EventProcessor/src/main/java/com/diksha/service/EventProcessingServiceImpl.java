package com.diksha.service;



import com.diksha.model.Event;

import java.util.PriorityQueue;


public class EventProcessingServiceImpl implements EventProcessingService{

    private PriorityQueue<Event> eventQueue ;

    private int totalSum ;
    private long currentWindowEnd ;

    private EventProcessingServiceImpl(){
            totalSum =0 ;
             currentWindowEnd = -1 ;
            eventQueue = new PriorityQueue<>((a,b)-> Math.toIntExact(a.getTimestamp() - b.getTimestamp())) ;
    }

    @Override
    public void processEvent(Event event) {

        if(event.getTimestamp() < currentWindowEnd-60) throw  new IllegalArgumentException(" Ignored, too late recieved, window starts "+ String.valueOf(currentWindowEnd - 60)) ;
        if(event.getTimestamp()>currentWindowEnd) currentWindowEnd = event.getTimestamp() ;
        while(!eventQueue.isEmpty() && eventQueue.peek().getTimestamp()<currentWindowEnd-60) {

            Event polledEvent= eventQueue.poll() ;
            totalSum-=polledEvent.getPayload().length() ;

        }
        eventQueue.offer(event) ;
        totalSum+=event.getPayload().length() ;

        System.out.println("average " + totalSum/eventQueue.size() + " window ends "+ currentWindowEnd);

    }
}
