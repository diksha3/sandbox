package com.diksha.controller;


import com.diksha.service.InMemoryServiceImpl;

public class InMemoryDBController {

    public static void main(String[] args) {
        InMemoryServiceImpl inMemoryService = InMemoryServiceImpl.getInstance() ;
     /*   inMemoryService.set("a","10");
        System.out.println(inMemoryService.get("a"));
        inMemoryService.delete("a");
        System.out.println(inMemoryService.get("a"));*/

      /*  inMemoryService.set("a","10");
        inMemoryService.set("b","10");
        System.out.println(inMemoryService.count("10"));
        System.out.println(inMemoryService.count("20"));
        inMemoryService.delete("a");
        System.out.println(inMemoryService.count("10"));
        inMemoryService.set("b","30");
        System.out.println(inMemoryService.count("10"));*/

       /* inMemoryService.begin() ;
        inMemoryService.set("a","10");
        System.out.println(inMemoryService.get("a"));
        inMemoryService.begin() ;
        inMemoryService.set("a","20");
        System.out.println(inMemoryService.get("a"));
        inMemoryService.rollback();
        System.out.println(inMemoryService.get("a"));
        inMemoryService.rollback();
        System.out.println(inMemoryService.get("a"));*/

    /*    inMemoryService.begin() ;
        inMemoryService.set("a","30");
        inMemoryService.begin() ;
        inMemoryService.set("a","40");
        inMemoryService.commit();
        System.out.println(inMemoryService.get("a"));
        inMemoryService.rollback();*/

       /* inMemoryService.set("a","50");
        inMemoryService.begin() ;
        System.out.println(inMemoryService.get("a")) ;
        inMemoryService.set("a","60");
        inMemoryService.begin() ;
        inMemoryService.delete("a");
        System.out.println(inMemoryService.get("a"));
        inMemoryService.rollback();
        System.out.println(inMemoryService.get("a"));
        inMemoryService.commit();
        System.out.println(inMemoryService.get("a"));*/


    /*    inMemoryService.set("a","10");
        inMemoryService.begin() ;
        System.out.println(inMemoryService.count("10")) ;
        inMemoryService.begin() ;
        inMemoryService.delete("a");
        System.out.println(inMemoryService.count("10")) ;
        inMemoryService.rollback();
        System.out.println(inMemoryService.count("10")) ;
*/





    }
}
