package com.diksha;

/*

There are N bombs in a row, each bomb, say i (0<=i<N) has a detonation time of xi  and time to defuse it is yi

 . Find the minimum time to defuse all the bombs if possible, otherwise -1.
 */

import java.util.ArrayList;
import java.util.List;

class Bomb{
    int detonationTime ;
    int diffuseTime ;

    public Bomb(int dt, int df){
        this.detonationTime = dt ;
        this.diffuseTime = df ;
    }
    public int getDetonationTime(){
        return this.detonationTime ;
    }
    public int getDiffuseTime(){
        return this.diffuseTime ;
    }
}

public class BombDetonation {

    public static void main(String[] args) {
        int n = 4 ;
        int[] diffuseTime = {2, 1, 3, 1} ;
        int[] denotationTimes = {4,9,8,2} ;
        List<Bomb> bombList = new ArrayList<>();
        for(int i =0 ; i< diffuseTime.length;i++){
            Bomb newBomb = new Bomb(denotationTimes[i],diffuseTime[i]) ;
            bombList.add(newBomb) ;
        }

        bombList.sort((a,b)-> a.getDetonationTime() ==b.getDetonationTime() ? a.getDiffuseTime() - b.getDiffuseTime() : a.getDetonationTime() - b.getDetonationTime());
        int totalTimeTaken =0  ;
        for(int i =0 ; i<bombList.size();i++){
            totalTimeTaken+=bombList.get(i).getDiffuseTime();
            if(totalTimeTaken>=bombList.get(i).getDetonationTime()) {
                System.out.println("-1");
                return ;
            }
        }
        System.out.println(totalTimeTaken) ;
        }

    }

