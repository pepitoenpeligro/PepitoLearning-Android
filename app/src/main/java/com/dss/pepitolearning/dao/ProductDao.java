package com.dss.pepitolearning.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dss.pepitolearning.Utils;
import com.dss.pepitolearning.constants.Defaults;
import com.dss.pepitolearning.models.Product;
import com.dss.pepitolearning.sqlite.DatabaseHelper;
import com.dss.pepitolearning.sqlite.mappings.ProductMapping;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    private static final String LOG_TAG = ProductDao.class.getName();

    public ProductDao(Context context){
        this.helper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDb(){
        // Singleton
        if(this.db == null)
            this.db = this.helper.getWritableDatabase();
        return this.db;
    }

    public void close(){
        this.helper.close();
        this.db = null;
    }


    public long save(Product p){
        this.getDb();
        String methodName = "[" + Utils.getCurrentMethodName() + "]\t";

        System.out.println(this.getDb().toString());
        ContentValues values = new ContentValues();
        //values.put(ProductMapping.ID,p.getId());
        values.put(ProductMapping.NAME, p.getName());
        values.put(ProductMapping.DESCRIPTION, p.getDescription());
        values.put(ProductMapping.PRICE, p.getPrice());


        Log.d(LOG_TAG,  methodName + p.toJson() + "into DB");
        long rowInsertedIndex = db.insert(Defaults.TABLE_PRODUCT_NAME, null, values);
        Log.d(LOG_TAG, methodName + "Result of Saving: " + rowInsertedIndex);
        return rowInsertedIndex; // returns -1 if error
    }

    /*public boolean remove(Product p){

    }*/

    public List<Product> findAll(){
        this.getDb();
        String methodName = "[" + Utils.getCurrentMethodName() + "]\t";
        List<Product> products = new ArrayList<>();
        Cursor cursor = db.query(
                true, Defaults.TABLE_PRODUCT_NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(ProductMapping.ID));
            Long   id           = Long.parseLong(String.valueOf(cursor.getString(cursor.getColumnIndex(ProductMapping.ID))));
            String name         = String.valueOf(cursor.getString(cursor.getColumnIndex(ProductMapping.NAME)));
            String description  = String.valueOf(cursor.getString(cursor.getColumnIndex(ProductMapping.DESCRIPTION)));
            double price        = Double.parseDouble(String.valueOf(cursor.getString(cursor.getColumnIndex(ProductMapping.PRICE))));

            Log.d(LOG_TAG,  methodName + "recovered data: " + id + ", " + name + ", " + description + ", " + price +  "from DB");
            Product p = new Product(id, name, description, price);
            Log.d(LOG_TAG,  methodName + "the recovered data is mapped to " + p.toJson() + "and will be inserted to array");
            products.add(p);
        }
        cursor.close();
        return products;
    }


    /*public Product findById(){

    }*/
}
