package com.dss.pepitolearning;

public class Utils {

   public static String getCurrentMethodName(){
       System.out.println(Thread.currentThread().getStackTrace().length);
       return Thread.currentThread().getStackTrace()[3].getMethodName();
   }
    public static String convertName(String origin){
        String fileName = "";
        fileName = origin.replace(" ", "_");
        fileName = fileName.toLowerCase();
        return fileName;
    }
}
