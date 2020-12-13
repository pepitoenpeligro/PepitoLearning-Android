package com.dss.pepitolearning.models;



import com.dss.pepitolearning.constants.Defaults;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductTest {

    @Test
    public void createProduct(){
        Product p = new Product();
        assertEquals(p.getId(), Defaults.PRODUCT_ID);
        assertEquals(p.getName(), Defaults.PRODUCT_NAME);
        assertEquals(p.getDescription(), Defaults.PRODUCT_DESCRIPTION);
        assertEquals(p.getPrice(), Defaults.PRODUCT_PRICE, Defaults.DELTA);
    }

    @Test
    public void toJson() {
        Product p = new Product();
        System.out.println(p.toJson());
    }


}
