package com.dss.pepitolearning.ui.auth;


import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.Lottie;
import com.airbnb.lottie.LottieAnimationView;
import com.dss.pepitolearning.R;
import com.dss.pepitolearning.api.APIProductGet;
import com.dss.pepitolearning.api.APIUserLogin;

public class AuthenticationActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button btnSingIn;
    private AppCompatButton btnSingUp;

    private EditText editTextEmail;
    private static EditText editTextPassword;

    private ImageView gorrito;

    private LottieAnimationView check;



    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    // Funcion que inicia sesion
    public boolean signin(String email, String password){
        return false;
    }


    // Funcion que registra usuario
    public boolean signup(String email, String password){
        return false;
    }

    public static void clearEditTexts(){
        editTextPassword.setText("");
    }

    public void initUI(){
        hideActionBar();
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.button_color));



        this.btnSingIn = findViewById(R.id.btn_signin);
        this.btnSingUp = findViewById(R.id.btn_singup);


        this.editTextEmail = findViewById(R.id.et_email);
        this.editTextPassword = findViewById(R.id.et_pass);

        this.gorrito = findViewById(R.id.gorrito);

        this.check = findViewById(R.id.animationcheck);

        this.check.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

                check.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                check.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        //    gorrito.animate().
        Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        this.gorrito.startAnimation(slideDown);

        //Dialog mDialog = new Dialog(this);
        //mDialog.setContentView(R.layout.popup);

        this.btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("AuthenticationActivity", "You will init login process");

                String email = editTextEmail.getText().toString();
                String pass = editTextPassword.getText().toString();

                Log.d("AuthenticationActivity", email + pass);

                APIUserLogin login = new APIUserLogin(email, pass);

                login.setActivity((Activity) view.getContext());
                login.execute();


            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        initUI();
    }


}