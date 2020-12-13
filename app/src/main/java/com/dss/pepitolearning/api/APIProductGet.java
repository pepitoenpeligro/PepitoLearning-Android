package com.dss.pepitolearning.api;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.dss.pepitolearning.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.dss.pepitolearning.constants.*;



public class APIProductGet extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    String response_body = "";
    String URL_WS = WS.BASE_API;


    TextView txtOutput;

    protected void onPreExecute(){
        super.onPreExecute();
        txtOutput = activity.findViewById(R.id.txt_output);
        // por si quisieramos meter un progress dialog
    }

    public void setActivity(Activity activity){
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... voids) {
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
        txtOutput.setText(response_body);
        // por si hay que meter progress dialogs
        // o reaccionar en listeners o adapters
    }
}
