package com.dss.pepitolearning.api;

import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieListener;
import com.dss.pepitolearning.NavigationDrawerActivity;
import com.dss.pepitolearning.R;
import com.dss.pepitolearning.constants.WS;
import com.dss.pepitolearning.ui.auth.AuthenticationActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class APIUserLogin extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    String response_body = "";
    String URL_WS = WS.BASE_API_USERS_LOGIN;

    String email = "";
    String pass = "";

    boolean resultadoOperacionLogin = false;

    public void setActivity(Activity activity){
        this.activity = activity;
    }

    public APIUserLogin(String mail, String pass){
        super();
        this.email = mail;
        this.pass = pass;
    }


    protected void onPreExecute(){
        super.onPreExecute();
        // por si quisieramos meter un progress dialog
    }



    @Override
    protected Void doInBackground(Void... voids) {
        try{
            System.out.println("Llamada asincrona login");

            URL url = new URL(URL_WS);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Access-Control-Allow-Origin", "*");
            urlConnection.setDoOutput(true);

            //Gson inputJSON = new GsonBuilder().setPrettyPrinting().create();
            String inputJSON = "{\"email\": \"" + this.email + "\", \"password\": \"" + pass + "\" }";
            System.out.println("Le envio:");
            System.out.println(inputJSON);




            // Escribimos los datos en el POST
            try(OutputStream os = urlConnection.getOutputStream()) {
                byte[] input = inputJSON.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            System.out.println("Datos enviados");

            System.out.println("El servidor me responde");

            // Miro el estado de la respuesta
            // Si es 202 (STATUS.ACCEPT) es que fue bien
            // SI es otro (500 o 404), es que fue mal

            System.out.println(urlConnection.getResponseCode());

            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_ACCEPTED){
                System.out.println("El login ha ido bien");
                resultadoOperacionLogin = true;

                // @ TODO guardar el usuario en local


                return null;
            }else{
                return null;
            }



            /*InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }
            response_body = builder.toString();
            System.out.println(response_body);
            */

        } catch (MalformedURLException e) {
            Log.d("User Async Task", "Url mal formada");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("User Async Task", "Error al leer de la conexión");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        // por si hay que meter progress dialogs
        // o reaccionar en listeners o adapters

        //this.activity.setContentView(R.layout.popup);
        Dialog mDialog = new Dialog(this.activity);
        mDialog.setContentView(R.layout.popup);
        String animationFile = "24477-uncheck.json";

        if(resultadoOperacionLogin){
            animationFile ="24476-check.json";
        }

        LottieAnimationView animation_popup = (LottieAnimationView) mDialog.findViewById(R.id.animationcheck);
        LottieCompositionFactory.fromAsset(activity.getApplicationContext(), animationFile).addListener(new LottieListener<LottieComposition>() {
            @Override
            public void onResult(LottieComposition result) {

                mDialog.show();

                animation_popup.setComposition(result);
                animation_popup.setVisibility(View.VISIBLE);
                animation_popup.playAnimation();

                animation_popup.addAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                        animation_popup.setVisibility(View.VISIBLE);
                        mDialog.show();
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        animation_popup.setVisibility(View.GONE);
                        mDialog.hide();

                        if(resultadoOperacionLogin){
                            Intent goToHome = new Intent(activity, NavigationDrawerActivity.class);
                            goToHome.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            AuthenticationActivity.clearEditTexts();
                            goToHome.putExtra("email_user",email);
                            activity.startActivity(goToHome);

                        }


                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });


            }
        });


    }


}
