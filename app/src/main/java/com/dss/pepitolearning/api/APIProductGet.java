package com.dss.pepitolearning.api;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;


import androidx.fragment.app.FragmentActivity;

import com.dss.pepitolearning.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.dss.pepitolearning.Utils;
import com.dss.pepitolearning.constants.*;
import com.dss.pepitolearning.models.Course;
import com.dss.pepitolearning.models.Product;
import com.dss.pepitolearning.ui.homeUI.HomeFragment;
import com.google.gson.Gson;


public class APIProductGet extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    String response_body = "";
    String URL_WS = WS.BASE_API;


    TextView txtOutput;

    private OnTaskCompleted listener;

    List<Course> myCourses;

    public interface OnTaskCompleted {
        void onTaskCompleted(List<Course> c);
    }


    protected void onPreExecute(){
        super.onPreExecute();

        // por si quisieramos meter un progress dialog
    }

    public void setActivity(Activity activity){
        this.activity = activity;
    }

    public APIProductGet(){

    }

    public APIProductGet(HomeFragment c){
        this.listener = (APIProductGet.OnTaskCompleted) c;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        myCourses = new ArrayList<>();

        try{
            URL url = new URL(URL_WS);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            response_body = builder.toString();
            Log.i("Product", response_body);
            System.out.println("-->" + response_body);

            Gson parser = new Gson();
            Product[] listaParseada = parser.fromJson(response_body, Product[].class);
            System.out.println("EL parseo resulta en");
            for (Product product : listaParseada) {
                System.out.println(product.toJson());
                Course c = new Course();
                c.setCategoryName(product.getName());
                c.setCategoryId(String.valueOf(product.getId()));
                c.setPrice(String.valueOf(product.getPrice()));
                c.setImage("course_" + Utils.convertName(product.getName()) + ".json");
                this.myCourses.add(c);
            }
            System.out.println("___________________________");


        } catch (MalformedURLException e) {
            Log.d("Product Async Task", "Url mal formada");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("Product Async Task", "Error al leer de la conexión");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);


        System.out.println("[OnPostExecute] ha finalizado");
        listener.onTaskCompleted(myCourses);
    }

}
