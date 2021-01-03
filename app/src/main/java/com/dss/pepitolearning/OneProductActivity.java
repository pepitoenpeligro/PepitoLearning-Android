package com.dss.pepitolearning;

import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieListener;
import com.dss.pepitolearning.models.Course;
import com.dss.pepitolearning.models.OneProduct;
import com.dss.pepitolearning.models.PlayList;
import com.dss.pepitolearning.ui.adapters.OneProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class OneProductActivity extends AppCompatActivity {

    RecyclerView courseRecyclerView;

    OneProductAdapter oneProductAdapter;

    TextView member, rating, name, price;

    LottieAnimationView addToCart;

    public void initWindowColor(){
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.button_color));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);
        initWindowColor();
        
        Course productRecovered = getIntent().getExtras().getParcelable("product_parcelable");

        String animationFileName = "41464-student-with-books.json";



        String newAnimationFileName = productRecovered.getImage();
        if(newAnimationFileName != null && !newAnimationFileName.isEmpty()){
            animationFileName = newAnimationFileName;
        }


        addToCart = findViewById(R.id.add_to_cart_animation);

        member = findViewById(R.id.members);
        rating = findViewById(R.id.rating);
        name = findViewById(R.id.name);
        name.setText(productRecovered.getCategoryName());



        ImageView goBack = findViewById(R.id.iv_go_back);
        goBack.setClickable(true);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)view.getContext()).onBackPressed();
            }
        });


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("[OneProductActivity] addToCart animation", "was clicked the animation of add to cart");

                Dialog mDialog = new Dialog(
                        OneProductActivity.this
                );

                mDialog.setContentView(R.layout.popup_decide_back_buy);
                mDialog.show();


                LottieAnimationView goToPay = mDialog.findViewById(R.id.to_pay_animation);
                LottieAnimationView goBackAndContinue = mDialog.findViewById(R.id.to_back_animation);

                goBackAndContinue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("[OneProductActivity] popup decide: goback", "use wants to continue");
                        // Antes era hide, pero he leido que mejor dismiss
                        mDialog.dismiss();
                        OneProductActivity.this.onBackPressed();
                        OneProductActivity.this.onBackPressed();
                    }
                });


                goToPay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("[OneProductActivity] popup decide: continue to pay", "use wants pay");
                        mDialog.dismiss();
                    }
                });



            }
        });




        LottieAnimationView header = findViewById(R.id.iv_one_product_back_animation);
        courseRecyclerView = findViewById(R.id.course_recycler);

        LottieCompositionFactory.fromAsset(getApplicationContext(), animationFileName).addListener(new LottieListener<LottieComposition>() {
            @Override
            public void onResult(LottieComposition result) {

                header.setComposition(result);
                header.setVisibility(View.VISIBLE);
                header.playAnimation();


                header.addAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                        header.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        header.setVisibility(View.GONE);
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


        List<OneProduct> oneProductList = new ArrayList<>();
        OneProduct c1 = new OneProduct();
        c1.setCourseName("AAAA");
        c1.setPrice("4.55");
        c1.setMember("Miembro");
        c1.setRating("1");
        OneProduct c2 = new OneProduct();
        c2.setCourseName("AAAA");
        c2.setPrice("4.55");
        c2.setMember("Miembro");
        c2.setRating("1");

        PlayList p1 = new PlayList();
        p1.setName("PlayList 1");
        p1.setTime("44:00");

        PlayList p2 = new PlayList();
        p2.setName("PlayList 2");
        p2.setTime("44:00");

        List<PlayList> playlist =  new ArrayList<>();
        playlist.add(p1);
        playlist.add(p2);

        getCourseContent(playlist);

    }

    private void getCourseContent(List<PlayList> playLists){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        courseRecyclerView.setLayoutManager(layoutManager);
        oneProductAdapter = new OneProductAdapter(this, playLists);
        courseRecyclerView.setAdapter(oneProductAdapter);
        oneProductAdapter.notifyDataSetChanged();

    }
}
