package com.dss.pepitolearning;

public class Utils {

   public static String getCurrentMethodName(){
       System.out.println(Thread.currentThread().getStackTrace().length);
       return Thread.currentThread().getStackTrace()[3].getMethodName();
   }
}
