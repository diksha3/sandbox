package com.diksha;

import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
class Item{
    private int x ;

}
public class DataChangeTest {


    public static void main(String[] args) {
        Map<Integer, PriorityQueue<Item>> input = new HashMap<>() ;
        PriorityQueue<Item> one = new PriorityQueue<>((a,b) -> a.getX() - b.getX()) ;
        one.add(new Item(1)) ;
        one.add(new Item(2)) ;
        input.put(1,one) ;

        // change something in the value list

        Item thisItem = input.get(1).peek() ;
        thisItem.setX(3);
        one.offer(one.poll()) ;

        // loop the map
        System.out.println(input.values());

    }
}
