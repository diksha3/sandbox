package com.diksha.goldman;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@ToString
class Log{
        private int customerId ;
        private int timestamp ;
        private int pageId ;

}
public class WebsiteCustomers {

    static Random random = new Random() ;
    public static void main(String[] args) {
        List<Log> day1Log = getDayLog() ;
        List<Log> day2Log = getDayLog() ;
        Set<Integer> loyalCustomers = getLoyalCustomers(day1Log, day2Log) ;
        System.out.println(loyalCustomers);
    }

    private static Set<Integer> getLoyalCustomers(List<Log> day1Log, List<Log> day2Log) {
        Set<Integer> loyalCustomers = new HashSet<>() ;
       Map<Integer,List<Log>> mapOfCustomersOnDay1 = day1Log.stream().collect(Collectors.groupingBy(Log::getCustomerId)) ;
       for(Log  log: day2Log){
           if(mapOfCustomersOnDay1.containsKey(log.getCustomerId())){
               List<Log> day1LogForCustomer  = mapOfCustomersOnDay1.get(log.getCustomerId()) ;
               Set<Integer> pageIdSet = day1LogForCustomer.stream().map(Log::getPageId).collect(Collectors.toSet()) ;
               pageIdSet.add(log.getPageId()) ;
               if(pageIdSet.size()>=2){
                   loyalCustomers.add(log.getCustomerId()) ;
               }

           }
       }
       return loyalCustomers ;
    }

    private static Log getRandomLog(){
        int randomCustomerId = (int) random.nextInt(10) % 10;
        int randomtimestamp = (int) random.nextInt(10)  % 10;
        int randompageId = (int) random.nextInt(10)  % 10;
        return new Log(randomCustomerId,randomtimestamp,randompageId) ;
    }
    private static List<Log> getDayLog() {
        List<Log> logList = new ArrayList<>() ;
        for(int i =0 ; i< 10;i++) {
            logList.add(getRandomLog());
        }
        return logList ;
    }


}


