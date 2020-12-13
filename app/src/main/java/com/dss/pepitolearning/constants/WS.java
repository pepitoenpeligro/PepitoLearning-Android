package com.dss.pepitolearning.constants;

public class WS {
    public static String PROTOCOL   = "http";
    public static String BASE_URL   = "pepecordoba.com";
    public static String PORT       = "8080";
    public static String APP        = "jpacruc";
    public static String WS         = "webservices";

    public static String PRODUCTS   = "products";



    public static String BASE_API =
            PROTOCOL + "://" + BASE_URL + ":" + PORT + "/"
                    + APP + "/" + WS + "/" + PRODUCTS;
}
