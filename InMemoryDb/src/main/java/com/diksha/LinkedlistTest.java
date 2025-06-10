package com.diksha;

import java.util.LinkedList;

public class LinkedlistTest {


    class Node {

        char c  ;
        Node next ;
        Node prev ;
        
        public Node( char c ){
            this.c = c ;
            this.next = null ;
            this.prev = null ;
        }

        public void addToRight(int index, String str){

        }

        public void removeKChars(int index, int k){

        }

    }
    public static void main(String[] args) {
        int cursor =0 ;
        LinkedList<Character> ll = new LinkedList<>() ;

        addStringToCursor(ll,cursor, "test") ;

        removeAtCursor(ll,cursor, 5) ;

    }

    private static void removeAtCursor(LinkedList<Character> ll, int cursor, int k) {
        for(int i =0 ; i <k;i++){
            if(cursor>0) ll.remove(cursor--) ;
        }

    }

    private static void addStringToCursor(LinkedList<Character> ll, int cursor, String test) {
        for(char  thisChar :test.toCharArray()){
            ll.add(cursor++,thisChar);
        }
    }
}
