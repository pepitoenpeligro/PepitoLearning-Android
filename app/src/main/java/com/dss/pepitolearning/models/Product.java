package com.dss.pepitolearning.models;

import com.dss.pepitolearning.constants.Defaults;
import com.google.gson.Gson;

public class Product{
    /**
     * id is a identifier in system from Product
     */
    private Long id;

    /**
     * name is a name in system from Product
     */
    private String name;

    /**
     * description is a description in system from Product
     */
    private String description;


    /**
     * price is a price in system from Product
     */
    private double price;


    /**
     * Create object and initialize a Product. No parames required
     * @return Product
     */
    public Product(){
        id          = Defaults.PRODUCT_ID;
        name        = Defaults.PRODUCT_NAME;
        description = Defaults.PRODUCT_DESCRIPTION;
        price       = Defaults.PRODUCT_PRICE;
    }

    /**
     * Create object and initialize a Product from a copy.
     * @param p Product
     * @return Product
     */
    public Product(Product p){
        this.id = p.id;
        this.name = p.name;
        this.description = p.description;
        this.price = p.price;
    }


    /**
     * Create object and initialize a Product values.
     * @param nid Long
     * @param nname String
     * @param ndescription String
     * @param nprice Double
     * @return Product
     */
    public Product(Long nid, String nname, String ndescription, Double nprice){
        this.id = nid;
        this.name = nname;
        this.description= ndescription;
        this.price = nprice;
    }

    /**
     * @return the identifier of product
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the product
     *
     * @param id The id must be Long
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name of product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product
     *
     * @param name The name must be String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description of product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product
     *
     * @param description The description must be String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price of product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of a product
     *
     * @param price The price must be double
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the price of a product
     *
     * @param price The price must be float
     */
    public void setPrice(float price){
        this.price = price;
    }

    /**
     * @param p Object
     * @return if p is the same object as called
     */
    @Override
    public boolean equals(Object p){
        return (this == p || this.id == ((Product)p).id);
    }

    /**
     *
     * @return json object from Product
     */
    public String toJson(){
        Gson g = new Gson();
        String json = g.toJson(new Product(this));
        return json;
    }
}
