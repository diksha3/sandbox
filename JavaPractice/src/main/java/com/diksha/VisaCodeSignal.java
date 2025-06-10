package com.diksha;

import java.util.HashMap;
import java.util.Map;

public class VisaCodeSignal {

    public static void main(String[] args) {
        String[] input = {"ls","cp","mv","!3", "mv","!1","!6"};

        int[] result = getTheCountOfCommandsExecuted(input) ;
        for(int resultItem : result){
            System.out.print(resultItem +" ");
        }
    }

    private static int[] getTheCountOfCommandsExecuted(String[] input) {

        int[] result = new int[3] ;  // cp ls mv

        Map<Integer,String> commandMap = new HashMap<>() ;
        fillCommandMap(input , commandMap) ;

        for(String thisCommand : input){
            if(isALinuxCommand(thisCommand)){
                populateResult(result, thisCommand);
            }
            else {
                // navigate till you get a linuxCommand
               String linuxCommand =  getLinuxCommand(thisCommand,commandMap);
               populateResult(result,linuxCommand);
            }
        }

        return result ;

    }

    private static void populateResult(int[] result, String thisCommand) {
        if(isCpCommand(thisCommand)){
            result[0]++ ;
        }
        else if(isMvCommand(thisCommand)){
            result[2]++ ;
        }
        else {
            result[1]++ ;
        }
    }

    private static String getLinuxCommand(String thisCommand,Map<Integer,String> commandMap) {
        int index =0 ;
        while(!isALinuxCommand(thisCommand)){
            char [] referringCommand = thisCommand.toCharArray() ;
            index = 0 ;
            for(int i =0 ; i <referringCommand.length;i++){
                if(Character.isDigit(referringCommand[i])){
                    index = index * 10 + (referringCommand[i] - '0') ;
                }
            }
            thisCommand = commandMap.get(index) ;
        }
        return thisCommand ;
    }

    private static void fillCommandMap(String[] input, Map<Integer, String> commandMap) {
        for(int i =0 ; i <input.length;i++){
            commandMap.put(i+1,input[i]) ;
        }
    }

    private static boolean isALinuxCommand(String thisCommand) {
        return isCpCommand(thisCommand) ||  isLsCommand(thisCommand) || isMvCommand(thisCommand) ;
    }

    private static boolean isMvCommand(String thisCommand) {
        return thisCommand.equalsIgnoreCase("mv") ;
    }

    private static boolean isLsCommand(String thisCommand) {
        return thisCommand.equalsIgnoreCase("ls");
    }

    private static boolean isCpCommand(String thisCommand) {
        return thisCommand.equalsIgnoreCase("cp");
    }
}
