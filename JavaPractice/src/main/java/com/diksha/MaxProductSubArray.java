/*
package org.example;

import java.util.;
import java.util.stream.Collectors;

public class MaxProductSubArray {
     public class TreeNode {
      public int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    class Node{
        TreeNode treeNode  ;
        int hd ;
        int height ;

        public Node(TreeNode treeNode,int hd,  int height){
            this.treeNode= treeNode ;
            this.hd = hd ;
            this.height = height ;
        }

        public int getHeight(){
            return this.height ;
        }
    }
    public int maxProduct(int[] nums) {
        //[2,3,-2,4]
        int[] leftProduct = new int[nums.length] ;
        int[] rightProduct = new int[nums.length] ;
        int left=1 ;
        int right=1 ;
        int max = Integer.MIN_VALUE ;
        for(int i =0 ; i <nums.length;i++){
            leftProduct[i] = nums[i]  left ;
            rightProduct[nums.length-i-1] = nums[nums.length-i-1]  right ;
            left = leftProduct[i] ;
            right = rightProduct[nums.length-i-1] ;
            if(left==0) left =1 ;
            if(right==0)right=1 ;
            max = Math.max(max,Math.max(left,right)) ;
        }

        Map<Integer, Integer> hdMap = new HashMap<>() ;
       // vtUtil(root,hdMap,0,0) ;
             hdMap.computeIfAbsent(1,k->0).s


//        for(int i =nums.length-1 ; i >=0;i--){
//            rightProduct[i] = nums[i]  right ;
//            right = rightProduct[i] ;
//        }

     return max ;
    }

    public static void main(String[] args) {
        MaxProductSubArray maxProductSubArray = new MaxProductSubArray() ;
        int[] nums = {2,3,-2,4,2,11,11} ;
        maxProductSubArray
                .maxProduct(nums) ;
    }
}
*/
