package com.diksha;

/*

A robot whose battery capacity is w, wants to ascend a vertical 2D wall (NxM).
It can start from any cell in the bottom row and wants to reach any cell in the top row.
Each cell can be 'x', meaning the robot can hop on that cell or a '.', meaning the robot can't hop on that node. However, there are some conditions it has to follow -
1. It can only move in horizontal (along the same row) or up direction.
2. The robot currently at (x1, y1) can only jump to (x2, y2), if the euclidean distance between these two cells is less than or equal to w (robot battery capacity).
3. Due to some sensor constraints it can hop atmost 2 nodes horizontally (along the same row) or vertically.
The first line of input is the number of rows, number of columns and the battery capacity. The second line is a 2D wall cell graph.
find the minimum number of steps it would take to reach the top row.

input is below:
3 4 1
xx.x
.x..
.x.x
.x..
output is 2
 */
public class RobotClimbingWall {
    // i will do a dfs from each point in the bottom row. at each point it has 6 options , 1 left 2 left, 1 right , 2 right , 1 up 2 up.
    // It also needs to check if there is a x in between or on the spot it wants to jump to and if so , it can not jump .
    static int min = Integer.MAX_VALUE ;
    public static void main(String[] args) {
        int n = 3 ;
        int m  = 4 ;
        int w = 1 ;
        char[][] grid = {{'x','x','.','x'},{'.','x','.','.'},{'.','x','.','x'},{'.','x','.','.'}} ;

        minSteps(n,m,w,grid) ;
        System.out.println("min is "+ min) ;
    }

    private static int minSteps(int n, int m, int w, char[][] grid) {
        for(int i =0 ; i <m;i++){
            boolean[][] visited= new boolean[n][m] ;
            if(!visited[n-1][i] && grid[n-1][i]=='.'){
                dfs(grid,visited,n-1,i,0) ;
            }
        }
        return min ;
    }

    private static void dfs(char[][] grid, boolean[][] visited, int row,int column, int hopCount) {
        if(grid[row][column]=='.') return;
        visited[row][column] = true ;
        if(row==0) {
            min = Math.min(min,hopCount) ;
            return;
        }
        // 4 options
        int[] rowArr = {0,0,-1,-2,0,0} ;
        int[] colArr = {-1,-2,0,0,1,2} ;

        for(int i =0 ; i<6;i++){
            int newRow = row+ rowArr[i] ;
            int newCol = column+ colArr[i] ;
          //  if(isWithinBoundary(grid,newRow, newCol) && !isBlocked(grid,newRow,newCol) && )
        }



    }
}
