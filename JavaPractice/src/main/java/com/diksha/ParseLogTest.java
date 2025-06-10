package com.diksha;

import java.util.HashMap;
import java.util.Map;

public class ParseLogTest {

    /*
    10:00 < John > Hi, How is everyone?
10:05 < Amy > Hello John
10:06 < Maria > Great, Having my morning coffee
13:00 < John > Let's meet this weekend
13:30  < Amy > Woahoo
     */


    public static void main(String[] args) {
        ParseLogTest mainObject = new ParseLogTest() ;
        mainObject.parseLogMethod() ;
    }
    public Map<String,Integer> parseLogMethod(){
        String log = "    10:00 < John > Hi, How is everyone?\n" +
                "10:05 < Amy > Hello John\n" +
                "10:06 < Maria > Great, Having my morning coffee\n" +
                "13:00 < John > Let's meet this weekend\n" +
                "13:30 < Amy > Woahoo" ;

        //1 split the lines by new line char

        //3. ...

        //1 split the lines by new line char
        String[] logLines = log.split("\\n") ;

        //2 . for eaACH LINE .. DO THIS ..
        Map<String,Integer> wordCountMap = new HashMap<>() ;
        for(String logLine  : logLines){
            String user  = logLine.substring(logLine.indexOf('<')+1,logLine.indexOf('>')).trim() ;
            int wordCount = logLine.substring(logLine.indexOf('>')+2).split(" ").length ;
            wordCountMap.put(user, wordCountMap.getOrDefault(user,0)+ wordCount) ;

            //3. ...

        }
        return wordCountMap ;
    }
}
