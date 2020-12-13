package com.dss.pepitolearning.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "practica4_db";

    // SQL DDL para crear la tabla products
    public static final String DDL_PRODUCTS = "CREATE TABLE products (" +
            "product_id" + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "product_description "+ "varchar(255) DEFAULT NULL," +
            "product_name " + "varchar(255) DEFAULT NULL," +
            "product_price " + "double DEFAULT NULL" +
            ")";



    // Crea un objeto que permite crear, abrir o gestionar una base de datos
    // En nuestro caso, la base de datos que crea, abre y gestiona se llama practica4_db
    public DatabaseHelper(Context context){
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    @Override
    // Función que se llama cuando la base de datos practica4_db se crea por primera vez.
    // Permite establecer sentencias sql de ejecución de inicialización de la base de datos o
    // de configuración
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DDL_PRODUCTS);
    }

    @Override
    // Función que se llama cuando la base de datos incrementa su versión
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
