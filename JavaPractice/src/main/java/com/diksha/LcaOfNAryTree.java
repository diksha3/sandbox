package com.diksha;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
class Node {
    private int num;
    private List<Node> children ;
    public void addChild(Node child){
        if(this.children ==null) children = new ArrayList<>() ;
        this.children.add(child) ;
    }
}

public class LcaOfNAryTree {
    public static void main(String[] args) {
        Node root = prepareInput() ;
        System.out.println("LCA is "+ getBoss(root,3,3)) ;
    }

    private static int getBoss(Node root, int first, int second) {

        List<List<Integer>> finalPaths  = new ArrayList<>() ;
        findPath(root,first,new ArrayList<>(), finalPaths) ;
        findPath(root,second,new ArrayList<>(), finalPaths) ;
        List<Integer> finalFirstPath = finalPaths.get(0) ;
        List<Integer> finalSecondPath = finalPaths.get(1) ;
        int i =0 ,j =0 ;
        int boss =-1 ;
        while(i<finalFirstPath.size() && j < finalSecondPath.size() && Objects.equals(finalFirstPath.get(i),finalSecondPath.get(j))){
            boss = finalFirstPath.get(i) ;
            i++ ;
            j++ ;
        }
        return boss ;
    }

    private static void findPath(Node node, int destination,List<Integer> path, List<List<Integer>> finalPaths) {
        if(node.getNum()==destination){
            path.add(destination) ;
            finalPaths.add(new ArrayList<>(path)) ;
            return;
        }
        path.add(node.getNum()) ;
        if(node.getChildren()!=null){
            for(Node childNode : node.getChildren()){
                 findPath(childNode,destination, path,finalPaths);
            }
        }
        path.remove(path.size()-1) ;
    }

    private static Node prepareInput() {
        Node root = new Node(0, null) ;
        Node one = new Node(1,null) ;
        Node two = new Node(2,null) ;
        Node three = new Node(3,null) ;
        Node four = new Node(4,null) ;
        Node five = new Node(5,null) ;
        Node six = new Node(6,null) ;
        Node seven = new Node(7,null) ;
        Node eight = new Node(8,null) ;
        root.addChild(one);
        root.addChild(two);
        one.addChild(three);
        one.addChild(four);
        one.addChild(five);
        two.addChild(six);
        six.addChild(seven);
        seven.addChild(eight);

        return root ;

    }

}
